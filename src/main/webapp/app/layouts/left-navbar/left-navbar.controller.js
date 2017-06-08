(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('LeftNavbarController', LeftNavbarController);

    LeftNavbarController.$inject = ['$state', 'Auth', 'Principal', 'entity', 'ProfileService', 'LoginService', 'UserExt'];

    function LeftNavbarController($state, Auth, Principal, ProfileService, LoginService, entity,UserExt) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerEnabled = response.swaggerEnabled;
        });
        vm.userExt = entity;
        console.log(vm.userExt);
        vm.login = login;
        vm.logout = logout;
        vm.profile = profile;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.$state = $state;

        function login() {
            collapseNavbar();
            LoginService.open();
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $state.go('home');
        }

        function profile(){
            collapseNavbar();
            $state.go('user-ext-detail');
        }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }

    }
});
