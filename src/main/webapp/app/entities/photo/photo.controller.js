(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('PhotoController', PhotoController);

    PhotoController.$inject = ['$scope', '$state', 'DataUtils', 'Photo'];

    function PhotoController ($scope, $state, DataUtils, Photo) {
        var vm = this;

        vm.photos = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Photo.query(function(result) {
                vm.photos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
