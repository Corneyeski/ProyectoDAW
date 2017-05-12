(function () {
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
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        })


            .state('nada', {
                    parent: 'app',
                    url: '/inicio-usuario',
                    data: {
                        authorities: ['ROLE_USER']
                    },
                    views: {
                        'navbar@': {
                            templateUrl: 'app/layouts/left-navbar/left-navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'vm'
                        },
                        'content@': {
                            templateUrl: 'app/inicio/usuario/inicio-usuario.html',
                            controller: 'InicioController',
                            controllerAs: 'vm'
                        }
                    },
                    resolve: {
                        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                            //$translatePartialLoader.addPart('video');
                            return $translate.refresh();
                        }]
                    }
                }
            )
            .state('photoFilter', {
                parent: 'app',
                url: '/photoFilter',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/filter/photoFilter/photoFilter.html',
                        controller: 'PhotoFilterController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        //$translatePartialLoader.addPart('video');
                        return $translate.refresh();
                    }]
                }
            });
    }
})();
