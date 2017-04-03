(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(html5ModeConfig);

    html5ModeConfig.$inject = ['$locationProvider'];

    function html5ModeConfig($locationProvider) {
        $locationProvider.html5Mode({ enabled: true, requireBase: true });
    }
})();
