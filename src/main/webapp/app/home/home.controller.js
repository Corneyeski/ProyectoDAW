(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$rootScope', '$scope', 'Principal', 'LoginService', '$state', 'Auth'];

    function HomeController ($rootScope, $scope, Principal, LoginService, $state, Auth) {
        var vm = this;
        vm.account = null;
        vm.isAuthenticated = null;
        //vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }


        vm.login = function($event){
            event.preventDefault();
             Auth.login({
                 username: vm.username,
                 password: vm.password,
                 rememberMe: vm.rememberMe
             }).then(function () {
                 vm.authenticationError = false;
                 vm.isAuthenticated = Principal.isAuthenticated;
                 $rootScope.$broadcast('authenticationSuccess');
                 if (Auth.getPreviousState()) {
                 var previousState = Auth.getPreviousState();
                 Auth.resetPreviousState();
                // $state.go(previousState.name, previousState.params);
                     //$state.go('nada',null);
                     $state.go("nada");

                 }

             }).catch(function () {
                vm.authenticationError = true;
             });
        }
    }
})();
