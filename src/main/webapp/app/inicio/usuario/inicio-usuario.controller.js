(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController);


    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt'];

    function InicioController($translate, $timeout, Auth, LoginService, UserExt, entity) {
        var vm = this;

        vm.photosDTO = entity;
    }
})();
