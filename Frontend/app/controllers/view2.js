(function() {
	'use strict';

	angular.module('eBankingApp.view2', ['ngRoute'])

	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/view2', {
	    templateUrl: 'views/view2.html',
	    controller: 'View2Ctrl'
	  });
	}])

	.controller('View2Ctrl', [function() {

	}]);
})();