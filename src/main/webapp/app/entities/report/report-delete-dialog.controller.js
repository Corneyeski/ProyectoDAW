(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('ReportDeleteController',ReportDeleteController);

    ReportDeleteController.$inject = ['$uibModalInstance', 'entity', 'Report'];

    function ReportDeleteController($uibModalInstance, entity, Report) {
        var vm = this;

        vm.report = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Report.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
