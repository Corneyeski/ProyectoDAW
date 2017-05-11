(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserProfileController',UserProfileController);

    UserProfileController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'AlertService'];

    function UserProfileController($scope, Principal, LoginService, $state, AlertService){
        var vm = this;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.modify  = modificar();

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
        console.log($scope.correo);

        function modificar(){
            console.log("hoaskdlakshdasd");
        }
    }

})();
