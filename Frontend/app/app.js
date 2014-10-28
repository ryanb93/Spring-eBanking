'use strict';

// Declare app level module which depends on views, and components
angular.module('eBanking', [
	'ngRoute',
    'eBanking.accountControllers',
    'eBanking.accountServices'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/accounts'});
}]);
