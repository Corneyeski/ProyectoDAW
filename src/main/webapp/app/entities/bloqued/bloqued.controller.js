(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('BloquedController', BloquedController);

    BloquedController.$inject = ['$scope', '$state', 'Bloqued'];

    function BloquedController ($scope, $state, Bloqued) {
        var vm = this;

        vm.bloqueds = [];

        loadAll();

        function loadAll() {
            Bloqued.query(function(result) {
                vm.bloqueds = result;
                vm.searchQuery = null;
            });
        }
    }
})();
