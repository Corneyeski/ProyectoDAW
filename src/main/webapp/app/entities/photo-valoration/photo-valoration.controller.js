(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('PhotoValorationController', PhotoValorationController);

    PhotoValorationController.$inject = ['$scope', '$state', 'PhotoValoration'];

    function PhotoValorationController ($scope, $state, PhotoValoration) {
        var vm = this;

        vm.photoValorations = [];

        loadAll();

        function loadAll() {
            PhotoValoration.query(function(result) {
                vm.photoValorations = result;
                vm.searchQuery = null;
            });
        }
    }
})();
