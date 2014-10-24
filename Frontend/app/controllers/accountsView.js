'use strict';

angular.module('eBankingApp.accountsView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/viewAccounts', {
    templateUrl: 'views/accountsView.html',
    controller: 'AccountsViewCtrl'
  });
}])

.controller('AccountsListCtrl', ['$scope', 'Account', function($scope, Account) {
	$scope.account = Account.query();
}])

.controller('AccountsViewCtrl', ['$scope', '$routeParams', 'Account', function($scope, $routeParams, Account) {
	$scope.account = Account.get(function(account) {
    $scope.dateOfBirth = account.dateOfBirth;
    console.log($scope.dateOfBirth);
  });
}]);