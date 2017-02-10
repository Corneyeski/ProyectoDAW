(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserProfileValorationController', UserProfileValorationController);

    UserProfileValorationController.$inject = ['$scope', '$state', 'UserProfileValoration'];

    function UserProfileValorationController ($scope, $state, UserProfileValoration) {
        var vm = this;

        vm.userProfileValorations = [];

        loadAll();

        function loadAll() {
            UserProfileValoration.query(function(result) {
                vm.userProfileValorations = result;
                vm.searchQuery = null;
            });
        }
    }
})();
