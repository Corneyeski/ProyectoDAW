(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('PhotoDetailController', PhotoDetailController);

    PhotoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Photo', 'User', 'Album'];

    function PhotoDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Photo, User, Album) {
        var vm = this;

        vm.photo = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('proyectoApp:photoUpdate', function(event, result) {
            vm.photo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
