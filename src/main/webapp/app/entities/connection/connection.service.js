(function() {
    'use strict';
    angular
        .module('proyectoApp')
        .factory('Connection', Connection);

    Connection.$inject = ['$resource', 'DateUtils'];

    function Connection ($resource, DateUtils) {
        var resourceUrl =  'api/connections/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.time = DateUtils.convertDateTimeFromServer(data.time);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
