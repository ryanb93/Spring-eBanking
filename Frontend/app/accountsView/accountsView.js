'use strict';

angular.module('eBankingApp.accountsView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/accountsView', {
    templateUrl: 'accountsView/accountsView.html',
    controller: 'AccountsViewCtrl'
  });
}])

.controller('AccountsViewCtrl', [function() {

}]);