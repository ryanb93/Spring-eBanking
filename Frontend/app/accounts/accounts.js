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
                        
            $scope.accountsList = ids.accounts;

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


        eBankingAPIservice.getTransactions(customerId, accountId).get(function(transactions) {
            $scope.transactions = transactions;
        });

        $scope.getIcon = function(accountType) {

            var icon = "fa-question";

            switch(accountType) {
                case "CASH": icon = "fa-money"; break;
                case "DEBIT_CARD": icon = "fa-cc-visa"; break;
                case "CREDIT_CARD": icon = "fa-cc-mastercard"; break;
                case "BACS": icon = "fa-bank"; break;
                case "DIRECT_DEBIT": icon = "fa-sign-out"; break;
                case "STANDING_ORDER": icon = "fa-external-link"; break;
                case "PAYPAL": icon = "fa-cc-paypal"; break;
            }

            return icon;
        }

        $scope.transactionPage = 0;
        $scope.loading = false;

        $scope.loadNext = function() {
            $scope.transactionPage++;
            $scope.loading = true;
            //TODO: Set up paging to get the next page of results.
            eBankingAPIservice.getTransactions(customerId, accountId).get(function(loaded) {
                var transactionList = $scope.transactions.transactions;
                //Add them to the $scope.transactions model.
                transactionList.push.apply(transactionList, loaded.transactions);
                $scope.loading = false;
            });

            
        }

    }
]);