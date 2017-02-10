(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('following', {
            parent: 'entity',
            url: '/following',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.following.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/following/followings.html',
                    controller: 'FollowingController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('following');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('following-detail', {
            parent: 'following',
            url: '/following/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.following.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/following/following-detail.html',
                    controller: 'FollowingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('following');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Following', function($stateParams, Following) {
                    return Following.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'following',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('following-detail.edit', {
            parent: 'following-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/following/following-dialog.html',
                    controller: 'FollowingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Following', function(Following) {
                            return Following.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('following.new', {
            parent: 'following',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/following/following-dialog.html',
                    controller: 'FollowingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                time: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('following', null, { reload: 'following' });
                }, function() {
                    $state.go('following');
                });
            }]
        })
        .state('following.edit', {
            parent: 'following',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/following/following-dialog.html',
                    controller: 'FollowingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Following', function(Following) {
                            return Following.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('following', null, { reload: 'following' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('following.delete', {
            parent: 'following',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/following/following-delete-dialog.html',
                    controller: 'FollowingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Following', function(Following) {
                            return Following.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('following', null, { reload: 'following' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
