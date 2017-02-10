(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('ConnectionDeleteController',ConnectionDeleteController);

    ConnectionDeleteController.$inject = ['$uibModalInstance', 'entity', 'Connection'];

    function ConnectionDeleteController($uibModalInstance, entity, Connection) {
        var vm = this;

        vm.connection = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Connection.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
