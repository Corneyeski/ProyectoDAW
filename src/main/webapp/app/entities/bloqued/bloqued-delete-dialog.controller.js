(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('BloquedDeleteController',BloquedDeleteController);

    BloquedDeleteController.$inject = ['$uibModalInstance', 'entity', 'Bloqued'];

    function BloquedDeleteController($uibModalInstance, entity, Bloqued) {
        var vm = this;

        vm.bloqued = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Bloqued.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
