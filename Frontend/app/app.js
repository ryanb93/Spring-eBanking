'use strict';

// Declare app level module which depends on views, and components
angular.module('eBanking', [
	'ngRoute',
	'ngMockE2E',
    'eBanking.accountControllers',
    'eBanking.accountServices'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/accounts'});
}]);
