/**
 * Created by 46989075Y on 23/03/2017.
 */
(function() {
    'use strict';

    angular
        .module('<YourAppName>')
        .config(html5ModeConfig);

    html5ModeConfig.$inject = ['$locationProvider'];

    function html5ModeConfig($locationProvider) {
        $locationProvider.html5Mode({ enabled: true, requireBase: true });
    }
})();
