(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserProfileValorationDetailController', UserProfileValorationDetailController);

    UserProfileValorationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserProfileValoration', 'User'];

    function UserProfileValorationDetailController($scope, $rootScope, $stateParams, previousState, entity, UserProfileValoration, User) {
        var vm = this;

        vm.userProfileValoration = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyectoApp:userProfileValorationUpdate', function(event, result) {
            vm.userProfileValoration = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
