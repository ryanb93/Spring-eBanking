(function() {
	'use strict';

	// Declare app level module which depends on views, and components
	angular.module('eBankingApp', [
	  'ngRoute',
	  'eBankingApp.view1',
	  'eBankingApp.view2',
	  'eBankingApp.customerDetailsController',
	  'eBankingApp.version',
	  'eBankingApp.customerDetailsServices'
	]).
	config(['$routeProvider', function($routeProvider) {
	  $routeProvider.otherwise({redirectTo: '/view1'});
	}]);
})();