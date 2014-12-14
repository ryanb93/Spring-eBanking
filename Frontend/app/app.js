'use strict';

// Declare app level module which depends on views, and components
angular.module('eBanking', [
	'ngRoute',
  'oauth',
  'ngStorage',
  'uiGmapgoogle-maps',
  'eBanking.oauthControllers',
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
});



