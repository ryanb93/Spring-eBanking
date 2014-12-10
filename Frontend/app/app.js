'use strict';

// Declare app level module which depends on views, and components
angular.module('eBanking', [
	'ngRoute',
  'oauth',
  'ngStorage',
  'eBanking.accountControllers',
  'eBanking.detailsControllers',
  'eBanking.transferControllers',
  'eBanking.APIService'
])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/access_token=:accessToken', {
      template: '',
      controller: function ($location, AccessToken) {
        var hash = $location.path().substr(1);
        AccessToken.setTokenFromString(hash);
        $location.path('/');
        $location.replace();
      }
    }).otherwise({redirectTo: '/accounts'});
}])
.config(function($httpProvider){
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8'
    })
.controller('indexController', ['$scope', 'Profile',
    function($scope, Profile) {

        $scope.me = Profile.get();
        console.log($scope.me)

    }
])
.controller('indexController', function ($location, $http, $scope, $timeout, AccessToken) {
    
    $scope.$on('oauth:authorized', function(event, token) {
      $http.defaults.headers.common['Authorization'] = 'Bearer ' + token.access_token;
      $scope.hasToken = true;
    });

    $scope.$on('oauth:logout', function(event) {
      $scope.reloadRoute = function() {
        console.log("in here");
        $state.reload();
      };
    });

    $scope.$on('oauth:loggedOut', function(event) {
      console.log('The user is not signed in');
    });

})
.filter('capitalize', function() {
  return function(input, scope) {
    if (input) {
    	input = input.toLowerCase();
    	return input.substring(0,1).toUpperCase()+input.substring(1);
    }
  }
});



