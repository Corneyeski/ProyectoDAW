(function() {
    'use strict';

    //TODO Esto es tuyo Alex, da errores en otras paginas, arreglalo

    $("#navbar").attr("class","");
    $("#content").attr("class","col-lg-12");
    $("#content").css('padding', '0');

    angular
        .module('proyectoApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$rootScope', '$scope', 'Principal', 'LoginService', '$state', 'Auth','$translate'];

    function HomeController ($rootScope, $scope, Principal, LoginService, $state, Auth,$translate) {
        var vm = this;
        vm.account = null;
        vm.isAuthenticated = null;
        //vm.login = LoginService.open;
        vm.register = register;
        vm.registerAccount = {};
        vm.registerAccount.useSanitizeValueStrategy;
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
            if (vm.registerAccount.password !== vm.confirmPassword) {
                vm.doNotMatch = 'ERROR';
            } else {
                vm.registerAccount.langKey = $translate.use();
                vm.doNotMatch = null;
                vm.error = null;
                vm.errorUserExists = null;
                vm.errorEmailExists = null;

                Auth.createAccount(vm.registerAccount).then(function () {
                    vm.success = 'OK';
                    state.go('home',null);
                }).catch(function (response) {
                    vm.success = null;
                    if (response.status === 400 && response.data === 'login already in use') {
                        vm.errorUserExists = 'ERROR';
                    } else if (response.status === 400 && response.data === 'e-mail address already in use') {
                        vm.errorEmailExists = 'ERROR';
                    } else {
                        vm.error = 'ERROR';
                    }
                });
            }
        }

        vm.login = function($event){
            event.preventDefault();
             Auth.login({
                 username: vm.username,
                 password: vm.password,
                 rememberMe: vm.rememberMe
             }).then(function () {
                 vm.authenticationError = false;
                 document.cookie="username=vm.username";
                 vm.isAuthenticated = Principal.isAuthenticated;
                 $rootScope.$broadcast('authenticationSuccess');
                 document.cookie = "username="+vm.username;
                // if (Auth.getPreviousState()==null) {
                 // var previousState = $state('home'); // Auth.getPreviousState();
                 //
                 // Auth.resetPreviousState();
                 // $state.go(previousState.name, previousState.params);
                 //     $state.go('nada',null);
                  //   $state.go('nada',null);

                   //                   }else{


                // }

                 var isAuthenticated = Principal.isAuthenticated();
                 if(isAuthenticated){
                     $state.go('nada',null);
                 }else{
                     $state.go('accessdenied').then(function() {
                         LoginService.open();
                     });
                 }

             }).catch(function () {
                vm.authenticationError = true;
             });
        }



    }
})();
