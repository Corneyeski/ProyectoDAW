(function() {
    'use strict';
    angular
        .module('proyectoApp')
        .factory('Following', Following);

    Following.$inject = ['$resource', 'DateUtils'];

    function Following ($resource, DateUtils) {
        var resourceUrl =  'api/followings/:id';

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
