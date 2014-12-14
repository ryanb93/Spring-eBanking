'use strict';

angular.module('eBanking.oauthControllers', []).

controller('oauthController', function ($location, $http, $scope, $timeout, AccessToken) {
    
    $scope.$on('oauth:authorized', function(event, token) {
      $http.defaults.headers.common['Authorization'] = 'Bearer ' + token.access_token;
      $scope.hasToken = true;
    });

    $scope.$on('oauth:logout', function(event) {
      $scope.hasToken = false;
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