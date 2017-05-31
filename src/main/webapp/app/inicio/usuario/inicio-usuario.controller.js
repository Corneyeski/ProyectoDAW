(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController);

    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt',  'DataUtils','$uibModal', '$rootScope','ParseLinks','paginationConstants','AlertService'];

    function InicioController($translate, $timeout, Auth, LoginService, UserExt, DataUtils,$uibModal, $rootScope,ParseLinks,paginationConstants,AlertService) {
        var vm = this;

        vm.openModal = openModal;
        vm.closeModal = closeModal;
        vm.enterImg=enterImg;
        vm.leaveImg=leaveImg;

        vm.votacion=votacion;
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


        loadAll();

        function votacion(data){
            console.log("entra");
            // this.removeClass('glyphicon-star-empty');
            // this.addClass('glyphicon-star');
        }

        function loadAll () {
            UserExt.home({
                page: vm.page,
                size: vm.itemsPerPage
                // sort: sort()
            }, onSuccess, onError);
            // function sort() {
            //     var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
            //     if (vm.predicate !== 'id') {
            //         result.push('id');
            //     }
            //     return result;
            // }

            function onSuccess(data, headers) {

                vm.totalItems = headers('X-Total-Count');
               // vm.links = ParseLinks.parse(headers('link'));


vm.disabled=data;
                console.log(data);

                // for (var i = 0; i < data.length; i++) {
                //     vm.scrolls.push(data[i]);
                // }

                for (var i = 0; i < data.length; i++) {
                    if(data[i].photo.points % 1 ===0){
                        vm.scrolls.push(data[i]);


                    }else{
                         var points = data[i].photo.points;
                       points =  points.toFixed(2);
                        data[i].photo.points= points;
                        vm.scrolls.push(data[i]);

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
                    '<button data-ng-click="vm.closeModal()" type="submit" ui-sref="user-ext-detail({id:'+data.userExt.id+'})">Perfil Usuario</button> ' +
            ' <ul class="rating"> <li ng-repeat="star in stars"  ng-class="star" ng-click="toggle($index)"><i class="fa fa-star-o"></i> </li> </ul>'




            });
            closeModal();
        }

        function closeModal(){

            //$uibModal.close('my-custom-modal');
            $rootScope.modalInstance.close('mal');
        }






    }
})();
