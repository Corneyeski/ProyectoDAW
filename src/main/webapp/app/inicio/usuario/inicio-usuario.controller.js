(function () {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('InicioController', InicioController);

    InicioController.$inject = ['$translate', '$timeout', 'Auth', 'LoginService', 'UserExt', 'DataUtils', '$uibModal', '$rootScope', 'ParseLinks', 'paginationConstants', 'AlertService', 'entity', 'Principal'];

    function InicioController($translate, $timeout, Auth, LoginService, UserExt, DataUtils, $uibModal, $rootScope, ParseLinks, paginationConstants, AlertService, entity, Principal) {
        var vm = this;

        vm.openModal = openModal;
        vm.closeModal = closeModal;
        vm.enterImg = enterImg;
        vm.leaveImg = leaveImg;

        vm.ratingStarEnter = ratingStarEnter;
        vm.ratingStarLeave = ratingStarLeave;
        vm.ratingStarSend = ratingStarSend;
        //  initController();
        vm.abrirVentanaPhoto = abrirVentanaPhoto;
        vm.abrirVentanaUser = abrirVentanaUser;
        vm.abrirVentanaOffer = abrirVentanaOffer;
        vm.filtrosPhotos = filtrosPhotos;
        vm.filtrosOffer = filtrosOffer;
        vm.filtrosUser = filtrosUser;
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
        vm.fotos = {};
        vm.fotos.useSanitizeValueStrategy;
        loadAll();

        function loadAll() {

            UserExt.home({
                page: vm.page,
                size: vm.itemsPerPage
                // sort: sort()
            }, onSuccess, onError);

            function onSuccess(data, headers) {

                vm.totalItems = headers('X-Total-Count');

                vm.disabled = data;

                if(vm.filtrakosPhoto!=null){
                    for (var i = 0; i < vm.filtrakosPhoto.length; i++) {
                        if (vm.filtrakosPhoto[i].points % 1 === 0) {
                        vm.scrolls.push(vm.filtrakosPhoto[i]);
                            }else{
                            var points = vm.filtrakosPhoto[i].points;
                            points = points.toFixed(2);
                            vm.filtrakosPhoto[i].points = points;
                            vm.scrolls.push(vm.filtrakosPhoto[i]);
                        }
                    }
                    vm.filtrakosPhoto=null;

                }else{
                    if(vm.filtrakosOffer!=null){
                        for (var i = 0; i < vm.filtrakosOffer.length; i++) {
                            if (filtrakosOffer[i].photo.points % 1 === 0) {
                                vm.scrolls.push(vm.filtrakosOffer[i]);
                            }else{
                                var points = filtrakosOffer[i].photo.points;
                                points = points.toFixed(2);
                                filtrakosOffer[i].photo.points = points;
                                vm.scrolls.push(filtrakosOffer[i]);
                            }
                        }

                    }else{
                        if(vm.filtrakosUser!=null){
                            for (var i = 0; i < vm.filtrakosUser.length; i++) {
                                if (filtrakosUser[i].photo.points % 1 === 0) {
                                    vm.scrolls.push(vm.filtrakosUser[i]);
                                }else{
                                    var points = filtrakosUser[i].photo.points;
                                    points = points.toFixed(2);
                                    filtrakosUser[i].photo.points = points;
                                    vm.scrolls.push(filtrakosUser[i]);
                                }
                            }

                    }else{
                            for ( i = 0; i < data.length; i++) {



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

                    }
                }

            }

            function onError(error) {
                AlertService.error(error.data.message);
            }

            UserExt.current({}, onSuccess2, onError2);

            function onSuccess2(data, headers) {

                vm.idUsu = data.id;
            }

            function onError2(error) {
                AlertService.error(error.data.message);
            }
        }

        function filtrosPhotos() {
            console.log(vm.fotos.nombre);
            console.log(vm.fotos.maxPoints);
            console.log(vm.fotos.minPoints);
            console.log(vm.fotos.tags);
            console.log(vm.fotos.date);

            UserExt.filtroPhotos({
                name: vm.fotos.nombre,
                time: vm.fotos.date,
                tags : vm.fotos.tags,
                maxPoints: vm.fotos.maxPoints,
                minPoints: vm.fotos.minPoints
            }, onSuccess4, onError4);

            function onSuccess4(data, headers) {
                console.log(data);
                vm.filtrakosPhoto = data;

                loadAll();

            }

            function onError4(error) {
                AlertService.error(error.data.message);
            }
        }
        function filtrosUser() {
            console.log(vm.user.city);
            console.log(vm.user.tags);
            console.log(vm.user.maxPoints);
            console.log(vm.user.minPoints);
            console.log(vm.user.maxAge);
            console.log(vm.user.minAge);

            UserExt.filtroUser({

                city: vm.user.city,
                tags: vm.user.tags,
                maxPoints: vm.user.maxPoints,
                minPoints: vm.user.minPoints,
                maxAge: vm.user.maxAge,
                minAge: vm.user.minAge

            }, onSuccess5, onError5);

            function onSuccess5(data, headers) {

                vm.filtrakosUser = data;
console.log(data);

            }

            function onError5(error) {
                AlertService.error(error.data.message);
            }
        }
        function filtrosOffer() {
            console.log(vm.offer.nombre);
            console.log(vm.offer.tags);
            console.log(vm.offer.city);
            console.log(vm.offer.maxSalary);
            console.log(vm.offer.minSalary);
            console.log(vm.offer.date);

            UserExt.filtroOffer({
                name: vm.offer.nombre,
                tags: vm.offer.tags,
                city: vm.offer.city,
                maxSalary: vm.offer.maxSalary,
                minSalary: vm.offer.minSalary,
                data: vm.offer.date
            }, onSuccess6, onError6);

            function onSuccess6(data, headers) {

                vm.filtrakosOffer = data;


            }

            function onError6(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset() {
            vm.page = 0;
            vm.scrolls = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }

        function enterImg(data) {

            data.currentTarget.children.info.style.opacity = '0.8';
            data.currentTarget.children.info.style.display = 'inline-block';
            data.currentTarget.children.info.style.zIndex = '700';
            data.target.style.zIndex = '-700';
            data.currentTarget.children.info.style.transition = '.7s ease';

        }

        function abrirVentanaPhoto(data) {

            $('.filtrosPhoto').toggle();
            $('.filtrosUser').hide();
            $('.filtrosOffer').hide();

        }
        function abrirVentanaUser(data) {

            $('.filtrosUser').toggle();
            $('.filtrosPhoto').hide();
            $('.filtrosOffer').hide();
        }
        function abrirVentanaOffer(data) {

            $('.filtrosOffer').toggle();
            $('.filtrosPhoto').hide();
            $('.filtrosUser').hide();
        }

        function ratingStarEnter(data2) {

            data2.currentTarget.classList.remove('glyphicon-star-empty');
            data2.currentTarget.classList.add('glyphicon-star');

            var ojala = $(data2.currentTarget).prevAll();

            for (var i = 0; i < ojala.length; i++) {
                ojala[i].classList.remove('glyphicon-star-empty');
                ojala[i].classList.add('glyphicon-star');
            }

        }

        function ratingStarLeave(data) {

            data.currentTarget.classList.remove('glyphicon-star');
            data.currentTarget.classList.add('glyphicon-star-empty');

            var ojala = $(data.currentTarget).prevAll();

            for (var i = 0; i < ojala.length; i++) {
                ojala[i].classList.remove('glyphicon-star');
                ojala[i].classList.add('glyphicon-star-empty');
            }

        }

        function ratingStarSend(data, photo) {

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
            console.log(parseInt(vm.photo));


            UserExt.sendValoration({

                vote: vm.idUsu,
                voted: vm.photo,
                value: vm.value


            }, onSuccess3, onError3);

            function onSuccess3(data, headers) {

                console.log("nice");
            }

            function onError3(error) {
                AlertService.error(error.data.message);
            }


            // UserExt.sendValoration({vote: vm.photo,voted:vm.idUsu,value:vm.value}).then(function () {
            //     vm.success = 'OK';
            //
            // }).catch(function (response) {
            //     vm.success = null;
            //
            // });
        }


        function leaveImg(data) {
            data.currentTarget.children.info.style.opacity = '1';
            data.currentTarget.children.info.style.display = 'none';
            data.currentTarget.children.info.style.zIndex = '-700';
            data.target.style.zIndex = '700';
        }

        function openModal(photo, data) {

            console.log(photo);
            console.log(data);

            $rootScope.modalInstance = $uibModal.open({
                template: '<img style="z-index: 9999;" class="imgmodal" data-ng-src="' + 'data:' + data.photo.imageContentType + ';base64,' + data.photo.image + '"</img> ' +
                '<button style="z-index: 9999;" data-ng-click="vm.closeModal()" type="submit" ui-sref="user-ext-detail({id:' + data.userExt.id + '})">Perfil Usuario</button> '

            });
            closeModal();
        }

        function closeModal() {

            //$uibModal.close('my-custom-modal');
            $rootScope.modalInstance.close('mal');
        }

    }
})();
