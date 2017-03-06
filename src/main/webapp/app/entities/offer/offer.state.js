(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('offer', {
            parent: 'entity',
            url: '/offer',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.offer.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/offer/offers.html',
                    controller: 'OfferController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('offer');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('offer-detail', {
            parent: 'offer',
            url: '/offer/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoApp.offer.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/offer/offer-detail.html',
                    controller: 'OfferDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('offer');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Offer', function($stateParams, Offer) {
                    return Offer.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'offer',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('offer-detail.edit', {
            parent: 'offer-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/offer/offer-dialog.html',
                    controller: 'OfferDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Offer', function(Offer) {
                            return Offer.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('offer.new', {
            parent: 'offer',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/offer/offer-dialog.html',
                    controller: 'OfferDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                time: null,
                                salary: null,
                                timeJob: null,
                                closed: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('offer', null, { reload: 'offer' });
                }, function() {
                    $state.go('offer');
                });
            }]
        })
        .state('offer.edit', {
            parent: 'offer',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/offer/offer-dialog.html',
                    controller: 'OfferDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Offer', function(Offer) {
                            return Offer.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('offer', null, { reload: 'offer' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('offer.delete', {
            parent: 'offer',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/offer/offer-delete-dialog.html',
                    controller: 'OfferDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Offer', function(Offer) {
                            return Offer.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('offer', null, { reload: 'offer' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
