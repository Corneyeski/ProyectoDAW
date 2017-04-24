(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            parent: 'app',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        })


        // .state('nada', {
        //         parent: 'app',
        //         url: '/nada',
        //         data: {
        //             authorities: []
        //         },
        //         views: {
        //             'content@': {
        //                 templateUrl: 'app/inicio/usuario/inicio-usuario.html',
        //                 controller: 'InicioController',
        //                 controllerAs: 'vm'
        //             }
        //         },
        //         resolve: {
        //             translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
        //                 //$translatePartialLoader.addPart('video');
        //                 return $translate.refresh();
        //             }]
        //         }
        //     }
        //
        // );
    }
})();
