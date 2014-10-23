'use strict';

// Declare app level module which depends on views, and components
angular.module('eBankingApp', [
  'ngRoute',
  'ngResource',
  'eBankingApp.view1',
  'eBankingApp.view2',
  'eBankingApp.version'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/view1'});
}]);
