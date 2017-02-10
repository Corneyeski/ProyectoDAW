(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('ConversationDialogController', ConversationDialogController);

    ConversationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Conversation', 'User'];

    function ConversationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Conversation, User) {
        var vm = this;

        vm.conversation = entity;
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
            if (vm.conversation.id !== null) {
                Conversation.update(vm.conversation, onSaveSuccess, onSaveError);
            } else {
                Conversation.save(vm.conversation, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoApp:conversationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
