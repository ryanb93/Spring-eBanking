var customerDetailsServices = angular.module('customerDetailsServices', ['ngResource']);

customerDetailsServices.factory('Details', ['$resource',
    function($resource) {
        return $resource('http://localhost:8080/api/customers/:customerId/',
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