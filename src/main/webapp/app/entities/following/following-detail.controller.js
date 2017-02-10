(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('FollowingDetailController', FollowingDetailController);

    FollowingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Following', 'User'];

    function FollowingDetailController($scope, $rootScope, $stateParams, previousState, entity, Following, User) {
        var vm = this;

        vm.following = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyectoApp:followingUpdate', function(event, result) {
            vm.following = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
