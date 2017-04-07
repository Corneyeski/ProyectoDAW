(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController);


    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt', 'entity'];

    function InicioController($translate, $timeout, Auth, LoginService, UserExt, entity) {
        var vm = this;
        var array2=[];


        vm.photosDTO = entity[0];

        console.log(array2);
        console.log(entity);


        vm.loadMore = function() {
            for(var i=0;i<entity.length;i++){
                array2.push(entity[i]);
            }
            vm.photos=array2;
        };


    }
})();
