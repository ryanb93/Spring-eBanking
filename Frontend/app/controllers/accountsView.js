'use strict';

angular.module('eBankingApp.accountsView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/viewAccounts', {
    templateUrl: 'views/accountsView.html',
    controller: 'AccountsViewCtrl'
  });
}])

.controller('AccountsViewCtrl', [function() {

}]);