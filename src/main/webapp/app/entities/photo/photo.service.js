(function() {
    'use strict';
    angular
        .module('proyectoApp')
        .factory('Photo', Photo);

    Photo.$inject = ['$resource', 'DateUtils'];

    function Photo ($resource, DateUtils) {
        var resourceUrl =  'api/photos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},

            'photoSave':{ method:'POST', isArray:false, url:'api/newPhoto'},
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
            'getImages':{
            method:'GET',
                isArray:true,
                url:'api/photosOfUser/:id',
        },

        'update': { method:'PUT' }
        });
    }
})();
