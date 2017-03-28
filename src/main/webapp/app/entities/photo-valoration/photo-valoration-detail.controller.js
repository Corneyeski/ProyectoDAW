(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('PhotoValorationDetailController', PhotoValorationDetailController);

    PhotoValorationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PhotoValoration', 'Photo', 'User'];

    function PhotoValorationDetailController($scope, $rootScope, $stateParams, previousState, entity, PhotoValoration, Photo, User) {
        var vm = this;

        vm.photoValoration = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyectoApp:photoValorationUpdate', function(event, result) {
            vm.photoValoration = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
