(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('FollowingController', FollowingController);

    FollowingController.$inject = ['$scope', '$state', 'Following'];

    function FollowingController ($scope, $state, Following) {
        var vm = this;

        vm.followings = [];

        loadAll();

        function loadAll() {
            Following.query(function(result) {
                vm.followings = result;
                vm.searchQuery = null;
            });
        }
        console.log(vm.followings);
    }
})();
