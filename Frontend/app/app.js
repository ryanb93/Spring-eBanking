'use strict';

// Declare app level module which depends on views, and components
angular.module('eBanking', [
	'ngRoute',
	'ngMockE2E',
    'eBanking.accountControllers',
    'eBanking.detailsControllers',
    'eBanking.APIService'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/accounts'});
}])
.config(function($httpProvider){
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8'
    })
.filter('capitalize', function() {
  return function(input, scope) {
    if (input) {
    	input = input.toLowerCase();
    	return input.substring(0,1).toUpperCase()+input.substring(1);
    }
  }
});



