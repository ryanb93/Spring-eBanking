
var accountServices = angular.module('accountServices', ['ngResource']);

accountServices.factory('Accounts', ['$resource',
  function($resource){
    return $resource('/:customerId/accounts/', {}, {
  		query: {method:'GET', params:{customerId:'123'}, isArray:false}
    });
  }]);

