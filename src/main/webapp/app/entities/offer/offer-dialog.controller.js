(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('OfferDialogController', OfferDialogController);

    OfferDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Offer', 'User'];

    function OfferDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Offer, User) {
        var vm = this;

        vm.offer = entity;
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
            if (vm.offer.id !== null) {
                Offer.update(vm.offer, onSaveSuccess, onSaveError);
            } else {
                Offer.offertSave(vm.offer, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoApp:offerUpdate', result);
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
