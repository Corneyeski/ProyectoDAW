(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('ConversationDeleteController',ConversationDeleteController);

    ConversationDeleteController.$inject = ['$uibModalInstance', 'entity', 'Conversation'];

    function ConversationDeleteController($uibModalInstance, entity, Conversation) {
        var vm = this;

        vm.conversation = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Conversation.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
