(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('PhotoFilterController', PhotoFilterController);

    PhotoFilterController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService'];

    function PhotoFilterController($state, Auth, Principal, ProfileService, LoginService) {
        var vm = this;
        var count = 0;
        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function (response) {
            vm.inProduction = response.inProduction;
            vm.swaggerEnabled = response.swaggerEnabled;
        });

        vm.login = login;
        vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.$state = $state;
        vm.hide = hide;
        vm.show = show;

        function login() {
            collapseNavbar();
            LoginService.open();
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $state.go('home');
        }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }

        function hide() {
            $("#style-1").animate({left: "-120px"}, 100);
        }

        function show() {
            $("#style-1").animate({left: "0px"}, 100);
        }
    }
})();

