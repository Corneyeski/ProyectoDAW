(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('photo-valoration', {
            parent: 'entity',
            url: '/photo-valoration',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.photoValoration.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/photo-valoration/photo-valorations.html',
                    controller: 'PhotoValorationController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('photoValoration');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('photo-valoration-detail', {
            parent: 'photo-valoration',
            url: '/photo-valoration/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.photoValoration.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/photo-valoration/photo-valoration-detail.html',
                    controller: 'PhotoValorationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('photoValoration');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PhotoValoration', function($stateParams, PhotoValoration) {
                    return PhotoValoration.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'photo-valoration',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('photo-valoration-detail.edit', {
            parent: 'photo-valoration-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/photo-valoration/photo-valoration-dialog.html',
                    controller: 'PhotoValorationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PhotoValoration', function(PhotoValoration) {
                            return PhotoValoration.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('photo-valoration.new', {
            parent: 'photo-valoration',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/photo-valoration/photo-valoration-dialog.html',
                    controller: 'PhotoValorationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                mark: null,
                                time: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('photo-valoration', null, { reload: 'photo-valoration' });
                }, function() {
                    $state.go('photo-valoration');
                });
            }]
        })
        .state('photo-valoration.edit', {
            parent: 'photo-valoration',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/photo-valoration/photo-valoration-dialog.html',
                    controller: 'PhotoValorationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PhotoValoration', function(PhotoValoration) {
                            return PhotoValoration.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('photo-valoration', null, { reload: 'photo-valoration' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('photo-valoration.delete', {
            parent: 'photo-valoration',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/photo-valoration/photo-valoration-delete-dialog.html',
                    controller: 'PhotoValorationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PhotoValoration', function(PhotoValoration) {
                            return PhotoValoration.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('photo-valoration', null, { reload: 'photo-valoration' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
