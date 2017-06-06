(function() {
    'use strict';
    angular
        .module('proyectoApp')
        .factory('Offer', Offer);

    Offer.$inject = ['$resource', 'DateUtils'];

    function Offer ($resource, DateUtils) {
        var resourceUrl =  'api/offers/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'offertSave':{ method:'POST', isArray:false, url:'api/newOffert'},
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
