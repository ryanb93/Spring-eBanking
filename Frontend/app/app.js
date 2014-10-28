// Declare app level module which depends on views, and components
angular
	.module('eBankingApp', [
	    'ngRoute',
	    'ngMockE2E',
	    'accountsViewController',
	    'customerDetailsController',
	    'customerDetailsServices',
	    'accountServices'
	])
	.constant("serverConfig", {
        "url": "http://localhost",
        "port": "8080"
    })
	.config(['$routeProvider', function($routeProvider) {
		$routeProvider
	        .otherwise({
	            redirectTo: '/accounts'
	        });
    	}
	]);

