(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('PhotoDialogController', PhotoDialogController);

    PhotoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Photo', 'User', 'Album'];

    function PhotoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Photo, User, Album) {
        var vm = this;

        vm.photo = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.users = User.query();
        vm.albums = Album.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.photo.id !== null) {
                Photo.update(vm.photo, onSaveSuccess, onSaveError);
            } else {
                Photo.save(vm.photo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoApp:photoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setImage = function ($file, photo) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        photo.image = base64Data;
                        photo.imageContentType = $file.type;
                    });
                });
            }
        };
        vm.datePickerOpenStatus.time = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
