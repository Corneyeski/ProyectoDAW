(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('LeftNavbarController', LeftNavbarController);

    LeftNavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService'];

    function LeftNavbarController($state, Auth, Principal, ProfileService, LoginService) {


    }
});
