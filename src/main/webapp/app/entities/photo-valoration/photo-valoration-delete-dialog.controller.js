(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('PhotoValorationDeleteController',PhotoValorationDeleteController);

    PhotoValorationDeleteController.$inject = ['$uibModalInstance', 'entity', 'PhotoValoration'];

    function PhotoValorationDeleteController($uibModalInstance, entity, PhotoValoration) {
        var vm = this;

        vm.photoValoration = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PhotoValoration.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
