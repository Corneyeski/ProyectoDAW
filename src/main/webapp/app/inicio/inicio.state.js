(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('inicio', {
            abstract: true,
            parent: 'app'
        });
    }
})();
