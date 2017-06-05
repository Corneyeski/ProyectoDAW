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
            'iscreateFollowing':{method:'PUT', isArray:false, url:'api/NewFollowing/:id'},
            'get': {
                method: 'PUT',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.time = DateUtils.convertDateTimeFromServer(data.time);
                    }
                    return data;
                }
            },
            'getFollowers': {
              method: 'GET', isArray:true, url:'api/followers/:id'
            },
            'estaFollowing':{
                method: 'GET', isArray:false, url:'api/isFollowing/:id'
            },
            'iseliminarFollowing':{
                method:'DELETE', isArray:false, url:'api/followings/:id'
            },
            'update': { method:'PUT' }
        });
    }
})();
