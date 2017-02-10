(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('FollowingDialogController', FollowingDialogController);

    FollowingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Following', 'User'];

    function FollowingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Following, User) {
        var vm = this;

        vm.following = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
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
            if (vm.following.id !== null) {
                Following.update(vm.following, onSaveSuccess, onSaveError);
            } else {
                Following.save(vm.following, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoApp:followingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.time = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
