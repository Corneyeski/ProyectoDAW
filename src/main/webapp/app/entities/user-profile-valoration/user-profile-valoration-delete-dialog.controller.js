(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserProfileValorationDeleteController',UserProfileValorationDeleteController);

    UserProfileValorationDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserProfileValoration'];

    function UserProfileValorationDeleteController($uibModalInstance, entity, UserProfileValoration) {
        var vm = this;

        vm.userProfileValoration = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserProfileValoration.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
