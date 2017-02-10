(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('PhotoValorationDialogController', PhotoValorationDialogController);

    PhotoValorationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PhotoValoration', 'Photo', 'User'];

    function PhotoValorationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PhotoValoration, Photo, User) {
        var vm = this;

        vm.photoValoration = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.photos = Photo.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.photoValoration.id !== null) {
                PhotoValoration.update(vm.photoValoration, onSaveSuccess, onSaveError);
            } else {
                PhotoValoration.save(vm.photoValoration, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoApp:photoValorationUpdate', result);
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
