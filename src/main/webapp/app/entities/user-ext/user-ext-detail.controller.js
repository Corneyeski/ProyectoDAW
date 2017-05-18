(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserExtDetailController', UserExtDetailController);

    UserExtDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserExt', 'User','Photo', 'Principal'];

    function UserExtDetailController($scope, $rootScope, $stateParams, previousState, entity, UserExt, User, Photo, Principal) {
        var vm = this;

        vm.currentAccount;
        vm.userExt = entity;
        vm.previousState = previousState.name;
        //console.log(vm.userExt);
        loadAll();
        console.log(Photo.photosUser);
        function loadAll() {
            Photo.query(function(result){

                vm.photosUser = result;
                console.log(vm.photosUser);
                vm.searchQuery = null;
            });
        }

        Principal.identity().then(function (account){
            vm.currentAccount = account;
        });
        //var listImage = [entity[0], entity[1], entity[2], entity[3], entity[4], entity[5], entity[6], entity[7]];

        //vm.photosUserProfile = listImage;
        var unsubscribe = $rootScope.$on('proyectoApp:userExtUpdate', function(event, result) {
            vm.userExt = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
