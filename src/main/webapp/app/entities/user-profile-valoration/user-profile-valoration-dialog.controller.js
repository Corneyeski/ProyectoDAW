(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserProfileValorationDialogController', UserProfileValorationDialogController);

    UserProfileValorationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserProfileValoration', 'User'];

    function UserProfileValorationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserProfileValoration, User) {
        var vm = this;

        vm.userProfileValoration = entity;
        vm.clear = clear;
        vm.save = save;
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.userProfileValoration.id !== null) {
                UserProfileValoration.update(vm.userProfileValoration, onSaveSuccess, onSaveError);
            } else {
                UserProfileValoration.save(vm.userProfileValoration, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoApp:userProfileValorationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
