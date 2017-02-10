(function() {
    'use strict';
    angular
        .module('proyectoApp')
        .factory('UserProfileValoration', UserProfileValoration);

    UserProfileValoration.$inject = ['$resource'];

    function UserProfileValoration ($resource) {
        var resourceUrl =  'api/user-profile-valorations/:id';

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
            'update': { method:'PUT' }
        });
    }
})();
