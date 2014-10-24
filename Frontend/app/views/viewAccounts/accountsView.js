'use strict';

angular.module('eBankingApp.accountsView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/viewAccounts', {
    templateUrl: 'views/viewAccounts/accountsView.html',
    controller: 'AccountsViewCtrl'
  });
}])

.controller('AccountsViewCtrl', [function() {

}]);