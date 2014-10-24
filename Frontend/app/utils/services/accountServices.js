var accountServices = angular.module('eBankingApp.accountServices', ['ngResource']);

accountServices.factory('Account', ['$resource',
  function($resource){
    return $resource('http://localhost:8080/api/customers/1/accounts/jsonAccount', {}, {
      query: { get: { method:'JSONP' }, isArray:false}
    });
  }]);