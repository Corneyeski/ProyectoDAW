(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserProfileController',UserProfileController);

    UserProfileController.$inject = ['$rootScope','$translate','$timeout','$Auth'];

    function UserProfileController($rootScope,$translate,$timeout,Auth){
        var vm = this;




    }
})();
