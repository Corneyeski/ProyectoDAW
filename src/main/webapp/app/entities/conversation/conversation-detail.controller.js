(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('ConversationDetailController', ConversationDetailController);

    ConversationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Conversation', 'User'];

    function ConversationDetailController($scope, $rootScope, $stateParams, previousState, entity, Conversation, User) {
        var vm = this;

        vm.conversation = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyectoApp:conversationUpdate', function(event, result) {
            vm.conversation = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
