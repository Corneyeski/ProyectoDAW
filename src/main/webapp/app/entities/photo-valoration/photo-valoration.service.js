(function() {
    'use strict';
    angular
        .module('proyectoApp')
        .factory('PhotoValoration', PhotoValoration);

    PhotoValoration.$inject = ['$resource', 'DateUtils'];

    function PhotoValoration ($resource, DateUtils) {
        var resourceUrl =  'api/photo-valorations/:id';

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
