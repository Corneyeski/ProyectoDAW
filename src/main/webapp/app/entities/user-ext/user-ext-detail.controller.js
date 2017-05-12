(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserExtDetailController', UserExtDetailController);

    UserExtDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserExt', 'User'];

    function UserExtDetailController($scope, $rootScope, $stateParams, previousState, entity, UserExt, User) {
        var vm = this;

        vm.userExt = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyectoApp:userExtUpdate', function(event, result) {
            vm.userExt = result;
        });
        $scope.$on('$destroy', unsubscribe);
        console.log(vm.userExt);
    }
})();
