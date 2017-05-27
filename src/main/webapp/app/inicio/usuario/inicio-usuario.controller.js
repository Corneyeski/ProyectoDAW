(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController);

    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt', 'entity', 'DataUtils','$uibModal', '$rootScope','ParseLinks','paginationConstants','AlertService'];

    function InicioController($translate, $timeout, Auth, LoginService, UserExt, entity, DataUtils,$uibModal, $rootScope,ParseLinks,paginationConstants,AlertService) {
        var vm = this;

        vm.openModal = openModal;
        vm.closeModal = closeModal;
        vm.enterImg=enterImg;
        vm.leaveImg=leaveImg;
      //  initController();

        vm.scrolls = [];
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;
console.log(entity);
        loadAll();


        //
        // function initController() {
        //     vm.bodyText = 'This text can be updated in modal 1';
        // }

        function loadAll () {
            UserExt.home({
                page: vm.page,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {
                vm.totalItems = headers('X-Total-Count');
               // vm.links = ParseLinks.parse(headers('link'));


                console.log(vm.totalItems);

                // for (var i = 0; i < data.length; i++) {
                //     vm.scrolls.push(data[i]);
                // }

                for (var i = 0; i < entity.length; i++) {
                    if(entity[i].photo.points % 1 ===0){
                        vm.scrolls.push(entity[i]);

                    }else{
                         var points = entity[i].photo.points;
                       points =  points.toFixed(2);
                        entity[i].photo.points= points;
                        vm.scrolls.push(entity[i]);

                    }



                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset () {
            vm.page = 0;
            vm.scrolls = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
function enterImg(data){


    data.target.style.opacity="0.2";


    data.target.style.transition=".7s ease";

    data.currentTarget.children.info.style.display="inline-block";

}
function leaveImg(data){

    data.target.style.opacity="1";
    data.currentTarget.children.info.style.display="none";
}
        function openModal(data){
          $rootScope.modalInstance = $uibModal.open({
            template: '<img class="imgmodal" data-ng-src="'+'data:'+data.photo.imageContentType+';base64,'+data.photo.image+'"</img> ' +
                    '<button ng-click="vm.closeModal()" type="submit" ui-sref="user-ext-detail({id:'+data.photo.user.id+'})">Perfil Usuario</button> '


            });
            closeModal();
        }

        function closeModal(){

            //$uibModal.close('my-custom-modal');
            $rootScope.modalInstance.close('mal');
        }


        // var listImage = [entity[0], entity[1], entity[2], entity[3], entity[4], entity[5]];
        // var aux = 6;
        // vm.photos2 = listImage;
        //
        // vm.loadMore = function () {
        //     var last = listImage[listImage.length - 1];
        //     var j = 6;
        //     for (var i = 0; i < 4; i++) {
        //         if (aux < entity.length) {
        //             listImage.push(entity[j]);
        //             j++;
        //             aux++;
        //         } else {
        //
        //
        //             break;
        //         }
        //     }
        // };




    }
})();
