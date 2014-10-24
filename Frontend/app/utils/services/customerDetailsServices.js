(function() {
	'use strict';
	var customerDetailsServices = angular.module('eBankingApp.customerDetailsServices', ['ngResource']);

	customerDetailsServices.factory('Details', ['$resource',
	  function($resource){
	    return $resource('http://localhost:8080/api/customers/1/accounts/jsonAccount', {}, {
	      query: { get: { method:'JSONP' }, isArray:false}
	    });
	  }]);
})();