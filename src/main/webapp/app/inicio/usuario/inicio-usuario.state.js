(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(stateConfig);


    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider


            .state('inicio-usuario', {
            parent: 'inicio',
            url: '/inicio-usuario',
            data: {
                authorities: [],
                pageTitle: 'inicio-usuario.title'
            },
            views: {
                'navbar@':{
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
                    //$translatePartialLoader.addPart('inicio-usuario');
                    return $translate.refresh();
                }]
                ,entity: ['$stateParams', 'UserExt', function($stateParams, UserExt) {
                    return UserExt.home().$promise;

                }]
            }
        })
    .state('user-ext-detail2', {
            parent: 'user-ext',
            url: '/user-ext/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.userExt.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-ext/user-ext-detail.html',
                    controller: 'UserExtDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userExt');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UserExt', function($stateParams, UserExt) {
                    return UserExt.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-ext',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })




    }
})();

