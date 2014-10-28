(function() {
    'use strict';

    angular.module('accountsViewController', ['ngRoute'])

    .config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.when('/accounts', {
                templateUrl: 'views/accountsView.html',
                controller: 'AccountsViewController'
            });
        }
    ])

    .controller('AccountsViewController', ['$scope', '$routeParams', 'Accounts',
        function($scope, $routeParams, Accounts) {
            $scope.accounts = Accounts.get(function(accounts) {
                $scope.accounts = accounts;
                return $scope.accounts;
            });
        }
    ]);

})();