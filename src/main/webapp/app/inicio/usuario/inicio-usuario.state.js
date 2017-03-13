(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {




        $stateProvider.state('inicio-usuario', {
            parent: 'inicio',
            url: '/inicio-usuario',
            data: {
                authorities: [],
                pageTitle: 'inicio-usuario.title'
            },
            views: {
                'navbar@': {
                    templateUrl: 'app/layouts/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                },
                'content@': {
                    templateUrl: 'app/inicio/usuario/inicio-usuario.html',
                    controller: 'Inicio-usuarioController',
                    controllerAs: 'vm'
                }

            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('inicio-usuario');
                    return $translate.refresh();
                }]
            }
        });

    }
})();
