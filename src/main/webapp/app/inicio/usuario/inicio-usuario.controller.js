(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController)
    //     .directive('modal', function () {
    //     return {
    //         restrict: 'EA',
    //         scope: {
    //             title: '=modalTitle',
    //             header: '=modalHeader',
    //             body: '=modalBody',
    //             footer: '=modalFooter',
    //             callbackbuttonleft: '&ngClickLeftButton',
    //             callbackbuttonright: '&ngClick',
    //             handler: '=lolo'
    //         },
    //         templateUrl: 'app/inicio/usuario/partialModal.html',
    //         transclude: true,
    //         controller: function ($scope) {
    //             vm.handler = 'pop';
    //
    //             vm.myRightButton = function (bool) {
    //                 alert('!!! first function call!');
    //             };
    //         },
    //     };
    // });

    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt', 'entity', 'DataUtils'];

    function InicioController($translate, $timeout, Auth, LoginService, UserExt, entity, DataUtils) {
 var vm = this;
        // vm.header = 'Put here your header';
        // vm.body = 'Put here your body';
        // vm.footer = 'Put here your footer';


        var listImage = [entity[0], entity[1], entity[2], entity[3], entity[4], entity[5], entity[6], entity[7]];
        var aux = 8;
        vm.photos2 = listImage;
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
