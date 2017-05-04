(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('LeftNavbarController', LeftNavbarController);

    LeftNavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService', 'ngMaterial', 'ngMessages', 'material.svgAssetsCache'];

    function LeftNavbarController($state, Auth, Principal, ProfileService, LoginService, $scope, $timeout, $mdSidenav) {

        $scope.toggleLeft = buildToggler('left');
        $scope.toggleRight = buildToggler('right');

        function buildToggler(componentId) {
            return function () {
                $mdSidenav(componentId).toggle();
            };
        }
    }
});
