(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('VideoController', VideoController);

    VideoController.$inject = ['$scope', '$state', 'Video'];

    function VideoController ($scope, $state, Video) {
        var vm = this;

        vm.videos = [];

        loadAll();

        function loadAll() {
            Video.query(function(result) {
                vm.videos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
