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
                'content@': {
                    templateUrl: 'app/inicio/usuario/inicio-usuario.html',
                    controller: 'InicioController',
                    controllerAs: 'vm'
                }

            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    //$translatePartialLoader.addPart('inicio-usuario');
                    return $translate.refresh();
                }]
                ,entity: ['$stateParams', 'UserExt', function($stateParams, UserExt) {
                    return UserExt.home().$promise;
                }]
            }
        });

    }
})();
// (function() {
//     'use strict';
//
//     angular
//         .module('proyectoApp')
//         .config(stateConfig);
//
//     stateConfig.$inject = ['$stateProvider'];
//
//     function stateConfig($stateProvider) {
//         $stateProvider.state('inicio-usuario', {
//             parent: 'inicio',
//             url: '/inicio-usuario',
//             data: {
//                 authorities: []
//             },
//             views: {
//                 'content@': {
//                     templateUrl: 'app/inicio/usuario/inicio-usuario.html',
//                     controller: 'InicioController',
//                     controllerAs: 'vm'
//                 }
//             },
//             resolve: {
//                 translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
//                     $translatePartialLoader.addPart('inicio-usuario');
//                     return $translate.refresh();
//                 }]
//             }
//         });
//     }
// })();
