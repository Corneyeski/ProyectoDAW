(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserExtDetailController', UserExtDetailController);

    UserExtDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserExt', 'User', 'Photo', 'Principal', 'Following', 'Bloqued', 'AlertService'];

    function UserExtDetailController($scope, $rootScope, $stateParams, previousState, entity, UserExt, User, Photo, Principal, Following, Bloqued, AlertService) {
        var vm = this;

        vm.Photos = [];
        vm.Following = [];
        vm.userExt = entity;
        vm.previousState = previousState.name;

        vm.siguiendo = "seguir";

        console.log(vm.userExt);
        loadAll();

        function loadAll() {

            Photo.getImages({
                id: vm.userExt.user.id

                // sort: sort()
            }, onSuccess, onError);

            Following.getFollowers({
                id: vm.userExt.user.id
            }, onSuccess2, onError2);


            Following.estaFollowing({
                id: vm.userExt.user.id
            }, onSuccess3, onError3);

            function onSuccess(data, headers) {
                vm.Photos = data;
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }

            function onSuccess2(data, headers) {
                vm.Following = data;
            }

            function onError2(error) {
                AlertService.error(error.data.message);
            }

            function onSuccess3(data, headers) {
                vm.PatataFollowing = data;
                console.log(data);
                if (data.id == null) {
                    vm.siguiendo = "seguir";

                } else {
                    vm.siguiendo = "siguiendo";

                }

            }

            function onError3(error) {
                AlertService.error(error.data.message);
            }
        }
        loadAll();

        vm.createFollowing = function (id) {
            Following.iscreateFollowing({'id': id}, {});
            // console.log("Ahora estas siguiendo a este usuario " + id);

        }

        vm.createBloqued = function (id) {
            Bloqued.iscreateBloqued({'id': id}, {});
            console.log("Usuario bloqueado" + id);
        }

        function save() {
            vm.isSaving = true;
            console.log("save");
            if (vm.userExt.id !== null) {
                UserExt.update(vm.userExt, onSaveSuccess, onSaveError);
            } else {
                UserExt.save(vm.userExt, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('proyectoApp:userExtUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }

        Principal.identity().then(function (account) {
            vm.currentAccount = account;
        });
        //var listImage = [entity[0], entity[1], entity[2], entity[3], entity[4], entity[5], entity[6], entity[7]];

        //vm.photosUserProfile = listImage;
        var unsubscribe = $rootScope.$on('proyectoApp:userExtUpdate', function (event, result) {
            vm.userExt = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
