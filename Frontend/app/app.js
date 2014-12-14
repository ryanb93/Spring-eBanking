'use strict';

// Declare app level module which depends on views, and components
angular.module('eBanking', [
	'ngRoute',
  'oauth',
  'ngStorage',
  'uiGmapgoogle-maps',
  'eBanking.accountControllers',
  'eBanking.detailsControllers',
  'eBanking.transferControllers',
  'eBanking.branchControllers',
  'eBanking.APIService'
]).

/*
 * Routes for the AngularJS Application 
 */
config(['$routeProvider', function($routeProvider) {
  $routeProvider.

  // OAuth 2.0 Route for handling tokens
  when('/access_token=:accessToken', {
      template: '',
      controller: function ($location, AccessToken) {
        var hash = $location.path().substr(1);
        AccessToken.setTokenFromString(hash);
        $location.path('/');
        $location.replace();
      }
  }).

  // Route displays all accounts
  when('/accounts', {
    templateUrl: 'accounts/accounts.html',
    controller: 'accountsController'
  }).

  // Route to display a single account
  when('/accounts/:accountNumber', {
    templateUrl: 'accounts/singleAccount.html',
    controller: 'singleAccountController'
  }).

  // Route for initiating a Transfer
  when('/transfer', {
    templateUrl: 'transfer/transfer.html',
    controller: 'transferController'
  }).

  // Route to display customer details
  when('/details', {
    templateUrl: 'details/details.html',
    controller: 'detailsController'
  }).

  //  Route for Branch location with GMaps
  when('/branch', {
    templateUrl: 'branch/branch.html',
    controller: 'branchController'
  }).

  // When a Route does is not found auto re-direct to accounts
  otherwise({redirectTo: '/accounts'});
}]).

config(function($httpProvider){
  $httpProvider.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8'
}).

controller('indexController', ['$scope', 'Profile',
  function($scope, Profile) {
    $scope.me = Profile.get();
    console.log($scope.me)
  }
]).

controller('indexController', function ($location, $http, $scope, $timeout, AccessToken) {
    
    $scope.$on('oauth:authorized', function(event, token) {
      $http.defaults.headers.common['Authorization'] = 'Bearer ' + token.access_token;
      $scope.hasToken = true;
    });

    $scope.$on('oauth:logout', function(event) {
      $scope.hasToken = false;
    });

    $scope.$on('oauth:loggedOut', function(event) {
      console.log('The user is not signed in');
    });

}).

filter('capitalize', function() {
  return function(input, scope) {
    if (input) {
    	input = input.toLowerCase();
    	return input.substring(0,1).toUpperCase()+input.substring(1);
    }
  }
});



