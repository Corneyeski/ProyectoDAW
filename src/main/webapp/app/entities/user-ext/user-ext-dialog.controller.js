(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserExtDialogController', UserExtDialogController);

    UserExtDialogController.$inject = ['$timeout', '$scope','$state', '$stateParams', '$q', 'entity', 'UserExt', 'User'];

    function UserExtDialogController ($timeout, $scope,$state, $stateParams, $q, entity, UserExt, User) {
        var vm = this;

        vm.userExt = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();
        console.log("dialog0");

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            // $uibModalInstance.dismiss('cancel');
            $state.go('^', {}, { reload: false });
        }

        function save () {
            vm.isSaving = true;

            if (vm.userExt.id !== null) {
                UserExt.update(vm.userExt, onSaveSuccess, onSaveError);
            } else {
                UserExt.save(vm.userExt, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoApp:userExtUpdate', result);
            console.log("save");
            // $uibModalInstance.close(result);
            $state.go('^', {}, { reload: false });
            // $state.reload();
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.birthdate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
