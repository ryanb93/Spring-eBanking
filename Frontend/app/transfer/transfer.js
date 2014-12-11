'use strict';

angular.module('eBanking.transferControllers', [])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/transfer', {
            templateUrl: 'transfer/transfer.html',
        });
    }
])

.controller('transferController', ['$scope', 'eBankingAPIservice',
    function($scope, eBankingAPIservice) {

        eBankingAPIservice.getAccounts().query(function(ids) {
            $scope.accountsList = ids;
        });

        $scope.messages = {};
        $scope.messages.transfer = {};
        $scope.messages.transfer.err = {};
        $scope.messages.transfer.success = {};
        $scope.messages.transfer.err.err = false;
        $scope.messages.transfer.success.success = false;

        $scope.processTransfer = function() {
            var test = eBankingAPIservice.postTransfer($scope.transfer.senderAccountNumber).save($scope.transfer, function(result) {
                
                $scope.messages.transfer.success.success = true;
                $scope.messages.transfer.err.err = false;
                $scope.messages.transfer.success.title = "Transfer Successful";
                $scope.messages.transfer.success.message = "£" + result.value + "  has been sent to " + result.recipientAccountNumber + " from " + result.senderAccountNumber; 

                eBankingAPIservice.getAccounts().query(function(ids) {
                    $scope.accountsList = ids;
                });
            }, 
            function(data, status, headers, config) {
                $scope.messages.transfer.err.err = true;
                $scope.messages.transfer.success.success = false;
                $scope.messages.transfer.err.stat = data.status;
                $scope.messages.transfer.err.message = data.statusText;
            });

        }

        $scope.processPayment = function() {
            var test = eBankingAPIservice.postTransfer($scope.payment.senderAccountNumber).save($scope.payment, function(result) {
                console.log(result);
                eBankingAPIservice.getAccounts().query(function(ids) {
                    $scope.accountsList = ids;
                });
            });
        }

    },
]);