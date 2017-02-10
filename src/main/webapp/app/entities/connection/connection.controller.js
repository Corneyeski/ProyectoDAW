(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('ConnectionController', ConnectionController);

    ConnectionController.$inject = ['$scope', '$state', 'Connection'];

    function ConnectionController ($scope, $state, Connection) {
        var vm = this;

        vm.connections = [];

        loadAll();

        function loadAll() {
            Connection.query(function(result) {
                vm.connections = result;
                vm.searchQuery = null;
            });
        }
    }
})();
