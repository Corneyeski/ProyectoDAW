(function() {
    'use strict';

    angular
        .module('proyectoApp')
        .controller('MessageController', MessageController);

    MessageController.$inject = ['$scope', '$state', 'DataUtils', 'Message'];

    function MessageController ($scope, $state, DataUtils, Message) {
        var vm = this;

        vm.messages = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Message.query(function(result) {
                vm.messages = result;
                vm.searchQuery = null;
            });
        }
    }
})();
