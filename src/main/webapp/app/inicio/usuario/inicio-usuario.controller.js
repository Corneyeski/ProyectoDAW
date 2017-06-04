(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController);

    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt', 'DataUtils', '$uibModal', '$rootScope', 'ParseLinks', 'paginationConstants', 'AlertService','entity','Principal'];

    function InicioController ($translate, $timeout, Auth, LoginService, UserExt, DataUtils, $uibModal, $rootScope, ParseLinks, paginationConstants, AlertService,entity,Principal) {
        var vm = this;

        vm.openModal = openModal;
        vm.closeModal = closeModal;
        vm.enterImg = enterImg;
        vm.leaveImg = leaveImg;

        vm.ratingStarEnter = ratingStarEnter;
        vm.ratingStarLeave = ratingStarLeave;
        vm.ratingStarSend=ratingStarSend;
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

            function onSuccess (data, headers) {

                vm.totalItems = headers('X-Total-Count');

                vm.disabled = data;


                for (var i = 0; i < data.length; i++) {
                    if (data[i].photo.points % 1 === 0) {
                        vm.scrolls.push(data[i]);

                    } else {
                        var points = data[i].photo.points;
                        points = points.toFixed(2);
                        data[i].photo.points = points;
                        vm.scrolls.push(data[i]);

                    }

                }
            }

            function onError (error) {
                AlertService.error(error.data.message);
            }

            UserExt.current({

            }, onSuccess2, onError2);

            function onSuccess2 (data, headers) {

             vm.idUsu=data.id;
            }

            function onError2 (error) {
                AlertService.error(error.data.message);
            }



        }

        function reset () {
            vm.page = 0;
            vm.scrolls = [];
            loadAll();
        }

        function loadPage (page) {
            vm.page = page;
            loadAll();
        }

        function enterImg (data) {


            data.currentTarget.children.info.style.opacity = '0.8';
            data.currentTarget.children.info.style.display = 'inline-block';
            data.currentTarget.children.info.style.zIndex = '700';
            data.target.style.zIndex = '-700';
            data.currentTarget.children.info.style.transition = '.7s ease';

        }

        function ratingStarEnter (data2) {


            data2.currentTarget.classList.remove('glyphicon-star-empty');
            data2.currentTarget.classList.add('glyphicon-star');

            var ojala = $(data2.currentTarget).prevAll();



            for (var i = 0; i < ojala.length; i++) {
                ojala[i].classList.remove('glyphicon-star-empty');
                ojala[i].classList.add('glyphicon-star');
            }

        }

        function ratingStarLeave (data) {

            data.currentTarget.classList.remove('glyphicon-star');
            data.currentTarget.classList.add('glyphicon-star-empty');

            var ojala = $(data.currentTarget).prevAll();



            for (var i = 0; i < ojala.length; i++) {
                ojala[i].classList.remove('glyphicon-star');
                ojala[i].classList.add('glyphicon-star-empty');
            }

        }

        function ratingStarSend(data,photo) {





                //-----------PUNTOS--------------
                var points = data.currentTarget.classList[0].substr(4, 1);
                vm.value = points;
                //---------------------------------
                //------------ID PHOTO----------------

                vm.photo = photo.photo.id;

//------------------------------------

                console.log(vm.photo);
                console.log(vm.value);
                console.log(vm.idUsu);



                UserExt.sendValoration({

                    vote: vm.photo,
                    voted: vm.idUsu,
                    value: vm.value


                },onSuccess3, onError3);




            function onSuccess3 (data, headers) {

                console.log("nice");
            }

            function onError3 (error) {
                AlertService.error(error.data.message);
            }









        }



        function leaveImg (data) {
            data.currentTarget.children.info.style.opacity = '1';
            data.currentTarget.children.info.style.display = 'none';
            data.currentTarget.children.info.style.zIndex = '-700';
            data.target.style.zIndex = '700';

        }

        function openModal (photo, data) {

            console.log(photo);
            console.log(data);

            $rootScope.modalInstance = $uibModal.open({
                template: '<img style="z-index: 9999;" class="imgmodal" data-ng-src="' + 'data:' + data.photo.imageContentType + ';base64,' + data.photo.image + '"</img> ' +
                '<button style="z-index: 9999;" data-ng-click="vm.closeModal()" type="submit" ui-sref="user-ext-detail({id:' + data.userExt.id + '})">Perfil Usuario</button> '

            });
            closeModal();
        }

        function closeModal () {

            //$uibModal.close('my-custom-modal');
            $rootScope.modalInstance.close('mal');
        }

    }
})();
