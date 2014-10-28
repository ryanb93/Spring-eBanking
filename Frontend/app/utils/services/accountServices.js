var accountServices = angular.module('accountServices', ['ngResource']);

accountServices.factory('Accounts', ['$resource',
    function($resource) {
        return $resource('http://localhost:8080/api/customers/:customerId/accounts',
        {
            customerId: '544be631036458271642f6bb'
        },
        {
            query: {
                method: 'GET',
            }
        });
    }
]);