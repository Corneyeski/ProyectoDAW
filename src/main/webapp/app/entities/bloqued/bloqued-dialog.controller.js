(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('BloquedDialogController', BloquedDialogController);

    BloquedDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Bloqued', 'User'];

    function BloquedDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Bloqued, User) {
        var vm = this;

        vm.bloqued = entity;
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
            if (vm.bloqued.id !== null) {
                Bloqued.update(vm.bloqued, onSaveSuccess, onSaveError);
            } else {
                Bloqued.save(vm.bloqued, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoApp:bloquedUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
