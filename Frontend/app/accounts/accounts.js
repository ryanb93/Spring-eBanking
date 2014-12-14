'use strict';

angular.module('eBanking.accountControllers', [])

.controller('accountsController', ['$scope', 'eBankingAPIservice',
    function($scope, eBankingAPIservice) {

        if($scope.hasToken) {
            eBankingAPIservice.getAccounts().query(function(ids) {
                $scope.accountsList = ids;
            });
        }

    }
])

.controller('singleAccountController', ['$scope', '$routeParams', 'eBankingAPIservice',
    function($scope, $routeParams, eBankingAPIservice) {

        $scope.account = null;
        $scope.transactionPage = 0;
        $scope.loading = false;
        $scope.finished = false;

        var accountId = $routeParams.accountNumber;

        eBankingAPIservice.getAccount(accountId).get(function(account) {
            $scope.account = account;
        });


        eBankingAPIservice.getTransactions(accountId, $scope.transactionPage).query(function(transactions) {
            $scope.transactions = transactions;
            if(transactions.length != 10) {
                //There are less than 10 transactions, remove the + button.
                $scope.finished = true;
            }
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

        $scope.getTitle = function(accountType) {

            var title = "Transaction";

            switch(accountType) {
                case "CASH": title = "Cash Payment"; break;
                case "DEBIT_CARD": title = "Debit Card Payment"; break;
                case "CREDIT_CARD": title = "Credit Card Payment"; break;
                case "BACS": title = "BAC Transfer"; break;
                case "DIRECT_DEBIT": title = "Direct Debit"; break;
                case "STANDING_ORDER": title = "Standing Order"; break;
                case "PAYPAL": title = "Paypal Transaction"; break;
            }

            return title;
        }

        $scope.getBadgeColour = function(accountType) {

            var colour = "";

            switch(accountType) {
                case "CASH": colour = "success"; break;
                case "DEBIT_CARD": colour = "danger"; break;
                case "CREDIT_CARD": colour = "warning"; break;
                case "PAYPAL": colour = "info"; break;
            }

            return colour;
        }

        $scope.loadNext = function() {
            
            $scope.loading = true;
            $scope.transactionPage++;

            eBankingAPIservice.getTransactions(accountId, $scope.transactionPage).query(function(loaded) {
                
                var existing = $scope.transactions;
                var next = loaded;

                $scope.transactions.push.apply(existing, next);
                if(next.length != 10 ) {
                    $scope.finished = true;
                }

                $scope.loading = false;
            });

            
        }

    }
]);