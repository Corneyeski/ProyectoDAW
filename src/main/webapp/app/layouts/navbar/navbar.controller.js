$("#uploadModal").click(change);
function change() {
    $("#uploadModal").toggle();
}
(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService', '$uibModal', '$rootScope'];

    function NavbarController($state, Auth, Principal, ProfileService, LoginService, $uibModal, $rootScope) {
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
        vm.upload = upload;

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

        function upload() {
            $("#uploadModal").toggle();
        }
    }
})();
