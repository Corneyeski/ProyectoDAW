(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('OfferController', OfferController);

    OfferController.$inject = ['$scope', '$state', 'Offer'];

    function OfferController ($scope, $state, Offer) {
        var vm = this;

        vm.offers = [];

        loadAll();

        function loadAll() {
            Offer.query(function(result) {
                vm.offers = result;
                vm.searchQuery = null;
            });
        }
    }
})();
