(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('FollowingDeleteController',FollowingDeleteController);

    FollowingDeleteController.$inject = ['$uibModalInstance', 'entity', 'Following'];

    function FollowingDeleteController($uibModalInstance, entity, Following) {
        var vm = this;

        vm.following = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Following.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
