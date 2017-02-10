(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('ConnectionDetailController', ConnectionDetailController);

    ConnectionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Connection', 'User'];

    function ConnectionDetailController($scope, $rootScope, $stateParams, previousState, entity, Connection, User) {
        var vm = this;

        vm.connection = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyectoApp:connectionUpdate', function(event, result) {
            vm.connection = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
