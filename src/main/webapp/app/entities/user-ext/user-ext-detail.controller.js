(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('UserExtDetailController', UserExtDetailController);

    UserExtDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserExt', 'User','Photo'];

    function UserExtDetailController($scope, $rootScope, $stateParams, previousState, entity, UserExt, User, Photo) {
        var vm = this;

        vm.userExt = entity;
        vm.previousState = previousState.name;
        //console.log(vm.userExt);
        loadAll();
        console.log(Photo.photosUser);
        function loadAll() {
            Photo.query(function(result){
                console.log(vm.photosUser);
                vm.photosUser = result;
                vm.searchQuery = null;
            });
        }


        //

        var unsubscribe = $rootScope.$on('proyectoApp:userExtUpdate', function(event, result) {
            vm.userExt = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
