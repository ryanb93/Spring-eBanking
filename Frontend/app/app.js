'use strict';

// Declare app level module which depends on views, and components
angular.module('eBankingApp', [
  'ngRoute',
  'eBankingApp.view1',
  'eBankingApp.view2',
  'eBankingApp.accountsView',
  'eBankingApp.version',
  'eBankingApp.accountServices'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/view1'});
}]);
