'use strict';

angular.module('eBanking.transferControllers', [])

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

        $scope.messages.payment = {};
        $scope.messages.payment.err = {};
        $scope.messages.payment.success = {};
        $scope.messages.payment.err.err = false;
        $scope.messages.payment.success.success = false;

        $scope.processTransfer = function() {
            var test = eBankingAPIservice.postTransfer($scope.transfer.senderAccountNumber).save($scope.transfer, function(result) {
                
                $scope.messages.transfer.success.success = true;
                $scope.messages.transfer.err.err = false;
                $scope.messages.transfer.success.title = "Transfer Successful";
                $scope.messages.transfer.success.message = "£" + result.value + "  has been sent from " + result.senderAccountNumber + " to " + result.recipientAccountNumber; 

                eBankingAPIservice.getAccounts().query(function(ids) {
                   for (var i = 0; i < ids.length; i++) {
                        $scope.accountsList[i].balance = ids[i].balance;
                    };
                });
            }, 
            function(data, status, headers, config) {
                console.log(data);
                $scope.messages.transfer.err.err = true;
                $scope.messages.transfer.success.success = false;
                $scope.messages.transfer.err.stat = "Transfer Failed";
                $scope.messages.transfer.err.message = data.data.message;
            });

        };

        $scope.processPayment = function() {
            var test = eBankingAPIservice.postTransfer($scope.payment.senderAccountNumber).save($scope.payment, function(result) {

                $scope.messages.payment.success.success = true;
                $scope.messages.payment.err.err = false;
                $scope.messages.payment.success.title = "Transfer Successful";
                $scope.messages.payment.success.message = "£" + result.value + "  has been sent from " + result.senderAccountNumber + " to " + result.recipientAccountNumber; 

                eBankingAPIservice.getAccounts().query(function(ids) {
                    
                    for (var i = 0; i < ids.length; i++) {
                        $scope.accountsList[i].balance = ids[i].balance;
                    };

                });
            }, 
            function(data, status, headers, config) {
                $scope.messages.payment.err.err = true;
                $scope.messages.payment.success.success = false;
                $scope.messages.payment.err.stat = "Transfer Failed";
                $scope.messages.payment.err.message = data.data.message;
            });

        };
    }
]);