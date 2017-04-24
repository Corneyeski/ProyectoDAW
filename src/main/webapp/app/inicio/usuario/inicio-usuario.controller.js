(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController);


    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt', 'entity', 'DataUtils'];

    function InicioController($translate, $timeout, Auth, LoginService, UserExt, entity, DataUtils) {






        var vm = this;
        var listImage = [entity[0], entity[1], entity[2], entity[3], entity[4], entity[5], entity[6], entity[7]];
        var aux = 8;
        vm.photos = listImage;
        vm.loadMore = function () {
            var last = listImage[listImage.length - 1];
            var j = 8;
            for (var i = 0; i < 4; i++) {
                if (aux < entity.length) {
                    listImage.push(entity[j]);
                    j++;
                    aux++;
                } else {
                    console.log("no hay mas imagenes");
                    break;
                }
            }
        };


    }
})();
