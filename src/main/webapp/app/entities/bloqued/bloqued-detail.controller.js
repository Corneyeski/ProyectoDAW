(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('BloquedDetailController', BloquedDetailController);

    BloquedDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Bloqued', 'User'];

    function BloquedDetailController($scope, $rootScope, $stateParams, previousState, entity, Bloqued, User) {
        var vm = this;

        vm.bloqued = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyectoApp:bloquedUpdate', function(event, result) {
            vm.bloqued = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
