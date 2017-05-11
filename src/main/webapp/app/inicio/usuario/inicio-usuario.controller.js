(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController);

    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt', 'entity', 'DataUtils','$uibModal', '$rootScope'];

    function InicioController($translate, $timeout, Auth, LoginService, UserExt, entity, DataUtils,$uibModal, $rootScope) {
        var vm = this;

        vm.openModal = openModal;
        vm.closeModal = closeModal;

        initController();

        function initController() {
            vm.bodyText = 'This text can be updated in modal 1';
        }

        function openModal(data){

            var widthScreen= screen.width;
            var heightScreen=screen.height;


            console.log(data);

            // var modalInstance
            $rootScope.modalInstance = $uibModal.open({
            template: '<img style="width: auto;height: auto;left:50%;top: 50%;" data-ng-src="'+'data:'+data.photo.imageContentType+';base64,'+data.photo.image+'"</img> ' +
                    '<button ng-click="vm.closeModal()" type="submit" ui-sref="user-ext-detail2({id:'+data.photo.user.id+'})">jooola</button> '


            });
            closeModal();
        }

        function closeModal(){
            console.log("CERRAR");
            //$uibModal.close('my-custom-modal');
            $rootScope.modalInstance.close('mal');
        }


        var listImage = [entity[0], entity[1], entity[2], entity[3], entity[4], entity[5], entity[6], entity[7]];
        var aux = 8;
        vm.photos2 = listImage;
        console.log(entity[0]);
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
