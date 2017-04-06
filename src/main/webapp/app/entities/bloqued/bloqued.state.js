(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('bloqued', {
            parent: 'entity',
            url: '/bloqued',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.bloqued.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bloqued/bloqueds.html',
                    controller: 'BloquedController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('bloqued');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('bloqued-detail', {
            parent: 'bloqued',
            url: '/bloqued/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.bloqued.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bloqued/bloqued-detail.html',
                    controller: 'BloquedDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('bloqued');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Bloqued', function($stateParams, Bloqued) {
                    return Bloqued.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'bloqued',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('bloqued-detail.edit', {
            parent: 'bloqued-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bloqued/bloqued-dialog.html',
                    controller: 'BloquedDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Bloqued', function(Bloqued) {
                            return Bloqued.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bloqued.new', {
            parent: 'bloqued',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bloqued/bloqued-dialog.html',
                    controller: 'BloquedDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('bloqued', null, { reload: 'bloqued' });
                }, function() {
                    $state.go('bloqued');
                });
            }]
        })
        .state('bloqued.edit', {
            parent: 'bloqued',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bloqued/bloqued-dialog.html',
                    controller: 'BloquedDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Bloqued', function(Bloqued) {
                            return Bloqued.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bloqued', null, { reload: 'bloqued' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bloqued.delete', {
            parent: 'bloqued',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bloqued/bloqued-delete-dialog.html',
                    controller: 'BloquedDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Bloqued', function(Bloqued) {
                            return Bloqued.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bloqued', null, { reload: 'bloqued' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
