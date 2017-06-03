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

        vm.ratingStarEnter=ratingStarEnter;
        vm.ratingStarLeave=ratingStarLeave;
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

console.log("vale");
    data.currentTarget.children.info.style.opacity="0.8";
    data.currentTarget.children.info.style.display="inline-block";
    data.currentTarget.children.info.style.zIndex="9999";
    data.target.style.zIndex="-9999";
    data.currentTarget.children.info.style.transition=".7s ease";

    //data.currentTarget.children.info.style.display="inline-block";
     // data.target.style.zIndex="1";
     // data.currentTarget.children.info.style.zIndex="2";

}
        function ratingStarEnter(data2){
            console.log(data2);


            data2.currentTarget.classList.remove('glyphicon-star-empty');
            data2.currentTarget.classList.add('glyphicon-star');

         var ojala = $(data2.currentTarget).prevAll();

        console.log(ojala);

        for(var i=0;i<ojala.length;i++){
            ojala[i].classList.remove('glyphicon-star-empty');
            ojala[i].classList.add('glyphicon-star');
        }




        }



        function ratingStarLeave(data){

            data.currentTarget.classList.remove('glyphicon-star');
            data.currentTarget.classList.add('glyphicon-star-empty');


            var ojala = $(data.currentTarget).prevAll();

            console.log(ojala);

            for(var i=0;i<ojala.length;i++){
                ojala[i].classList.remove('glyphicon-star');
                ojala[i].classList.add('glyphicon-star-empty');
            }


        }




function leaveImg(data){
    data.currentTarget.children.info.style.opacity="1";
    data.currentTarget.children.info.style.display="none";
    data.currentTarget.children.info.style.zIndex="-9999";
    data.target.style.zIndex="9999";
     // data.target.style.zIndex="9999";
    // data.currentTarget.children.info.style.zIndex="-1";
    // data.currentTarget.children.info.style.display="none";

     // data.currentTarget.children.info.style.zIndex="-9999";


}
        function openModal(data){
          $rootScope.modalInstance = $uibModal.open({
            template: '<img class="imgmodal" data-ng-src="'+'data:'+data.photo.imageContentType+';base64,'+data.photo.image+'"</img> ' +
                    '<button data-ng-click="vm.closeModal()" type="submit" ui-sref="user-ext-detail({id:'+data.userExt.id+'})">Perfil Usuario</button> '





            });
            closeModal();
        }

        function closeModal(){

            //$uibModal.close('my-custom-modal');
            $rootScope.modalInstance.close('mal');
        }






    }
})();
