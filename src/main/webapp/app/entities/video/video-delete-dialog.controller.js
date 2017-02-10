(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('VideoDeleteController',VideoDeleteController);

    VideoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Video'];

    function VideoDeleteController($uibModalInstance, entity, Video) {
        var vm = this;

        vm.video = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Video.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
