'use strict';

angular.module('eBanking.accountControllers', [])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/accounts', {
            templateUrl: 'accounts/accounts.html',
        });

        $routeProvider.when('/accounts/:accountNumber', {
            templateUrl: 'accounts/singleAccount.html',
        });
    }
])

.controller('accountsController', ['$scope', 'eBankingAPIservice',
    function($scope, eBankingAPIservice) {

        var customerId = "544be631036458271642f6bb";

        eBankingAPIservice.getAccounts(customerId).get(function(ids) {
            
            var accountIds = ids.accountIds;
            
            $scope.accountsList = [];

            //We have a bug here because results callback at different times the list order is random.
            angular.forEach(accountIds, function(accountId) {
                eBankingAPIservice.getAccount(customerId, accountId).get(function(details) {
                    $scope.accountsList.push(details);
                });
            });

        });

    }
])

.controller('singleAccountController', ['$scope', '$routeParams', 'eBankingAPIservice',
    function($scope, $routeParams, eBankingAPIservice) {

        $scope.account = null;

        var accountId = $routeParams.accountNumber;
        var customerId = "544be631036458271642f6bb";

        eBankingAPIservice.getAccount(customerId, accountId).get(function(account) {
            $scope.account = account;
        });

    }
]);