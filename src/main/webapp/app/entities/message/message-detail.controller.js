(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('MessageDetailController', MessageDetailController);

    MessageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Message', 'User', 'Conversation'];

    function MessageDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Message, User, Conversation) {
        var vm = this;

        vm.message = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('proyectoApp:messageUpdate', function(event, result) {
            vm.message = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
