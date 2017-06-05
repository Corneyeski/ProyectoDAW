(function() {
    'use strict';
    angular
        .module('proyectoApp')
        .factory('Bloqued', Bloqued);

    Bloqued.$inject = ['$resource'];

    function Bloqued ($resource) {
        var resourceUrl =  'api/bloqueds/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'createBloqued':{method: 'POST', isArray: false, url:'/api/newBloqued'},
            'update': { method:'PUT' }
        });
    }
})();
