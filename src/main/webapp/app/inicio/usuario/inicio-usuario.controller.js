(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController);

    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt', 'entity', 'DataUtils','$uibModal'];

    function InicioController($translate, $timeout, Auth, LoginService, UserExt, entity, DataUtils,$uibModal) {
        var vm = this;
        // vm.header = 'Put here your header';
        // vm.body = 'Put here your body';
        // vm.footer = 'Put here your footer';
        vm.openModal = openModal;
        vm.closeModal = closeModal;

        initController();

        function initController() {
            vm.bodyText = 'This text can be updated in modal 1';
        }

        function openModal(data){
            console.log(data);
            var modalInstance = $uibModal.open({
                //template: '<img  data-ng-src="{{'data:' + data.photo.imageContentType + ';base64,' + data.photo.image }}"'

    template: '<div class="imgModal"><img data-ng-src="'+'data:'+data.photo.imageContentType+';base64,'+data.photo.image+'"</img></div>'

            });
        }

        function closeModal(id){
            $uibModal.close('my-custom-modal');
        }


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
