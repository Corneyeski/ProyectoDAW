(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-profile-valoration', {
            parent: 'entity',
            url: '/user-profile-valoration',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.userProfileValoration.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-profile-valoration/user-profile-valorations.html',
                    controller: 'UserProfileValorationController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userProfileValoration');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('user-profile-valoration-detail', {
            parent: 'user-profile-valoration',
            url: '/user-profile-valoration/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.userProfileValoration.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-profile-valoration/user-profile-valoration-detail.html',
                    controller: 'UserProfileValorationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userProfileValoration');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UserProfileValoration', function($stateParams, UserProfileValoration) {
                    return UserProfileValoration.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-profile-valoration',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-profile-valoration-detail.edit', {
            parent: 'user-profile-valoration-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-profile-valoration/user-profile-valoration-dialog.html',
                    controller: 'UserProfileValorationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserProfileValoration', function(UserProfileValoration) {
                            return UserProfileValoration.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-profile-valoration.new', {
            parent: 'user-profile-valoration',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-profile-valoration/user-profile-valoration-dialog.html',
                    controller: 'UserProfileValorationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                value: null,
                                comments: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-profile-valoration', null, { reload: 'user-profile-valoration' });
                }, function() {
                    $state.go('user-profile-valoration');
                });
            }]
        })
        .state('user-profile-valoration.edit', {
            parent: 'user-profile-valoration',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-profile-valoration/user-profile-valoration-dialog.html',
                    controller: 'UserProfileValorationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserProfileValoration', function(UserProfileValoration) {
                            return UserProfileValoration.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-profile-valoration', null, { reload: 'user-profile-valoration' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-profile-valoration.delete', {
            parent: 'user-profile-valoration',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-profile-valoration/user-profile-valoration-delete-dialog.html',
                    controller: 'UserProfileValorationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserProfileValoration', function(UserProfileValoration) {
                            return UserProfileValoration.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-profile-valoration', null, { reload: 'user-profile-valoration' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
