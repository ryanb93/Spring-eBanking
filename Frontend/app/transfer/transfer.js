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


        $scope.displayTransferError = function(message) {
                $scope.messages.transfer.err.err = true;
                $scope.messages.transfer.success.success = false;
                $scope.messages.transfer.err.stat = "Transfer failed";
                $scope.messages.transfer.err.message = message;
                return;
        }

        $scope.displayPaymentError = function(message) {
                $scope.messages.payment.err.err = true;
                $scope.messages.payment.success.success = false;
                $scope.messages.payment.err.stat = "Transfer failed";
                $scope.messages.payment.err.message = message;
                return;
        }

        $scope.processTransfer = function() {

            //Front end validation on the sender account number existing.
            if(!$scope.transfer.accountNumber) {
                return $scope.displayTransferError("You must chose a sender account.");
            }

            //Front end validation on the receiver account number existing.
            if(!$scope.transfer.otherAccountNumber) {
                return $scope.displayTransferError("You must chose a recipient account.");
            }
            //Front end validation on the value being positive.
            if($scope.transfer.value < 0) {
                return $scope.displayTransferError("You must send a value greater than zero.");
            } 

            var test = eBankingAPIservice.postTransfer($scope.transfer.accountNumber).save($scope.transfer, function(result) {
                $scope.messages.transfer.success.success = true;
                $scope.messages.transfer.err.err = false;
                $scope.messages.transfer.success.title = "Transfer Successful";
                $scope.messages.transfer.success.message = "£" + result.value + "  has been sent from " + result.accountNumber + " to " + result.otherAccountNumber; 

                eBankingAPIservice.getAccounts().query(function(ids) {
                   for (var i = 0; i < ids.length; i++) {
                        $scope.accountsList[i].balance = ids[i].balance;
                    };
                });
            }, 
            function(data, status, headers, config) {
                $scope.displayTransferError(data.data.message);
            });

        };



        $scope.processPayment = function() {

            //Front end validation on the sender account number existing.
            if(!$scope.payment.accountNumber) {
                return $scope.displayPaymentError("You must chose a sender account.");
            }
            //Front end validation on the receiver account number existing.
            if(!$scope.payment.otherAccountNumber) {
                return $scope.displayPaymentError("You must chose a recipient account.");
            }
            //Front end validation on the value being positive.
            if($scope.payment.value < 0) {
                return $scope.displayPaymentError("You must send a value greater than zero.");
            } 


            var test = eBankingAPIservice.postTransfer($scope.payment.accountNumber).save($scope.payment, function(result) {

                $scope.messages.payment.success.success = true;
                $scope.messages.payment.err.err = false;
                $scope.messages.payment.success.title = "Transfer Successful";
                $scope.messages.payment.success.message = "£" + result.value + "  has been sent from " + result.accountNumber + " to " + result.otherAccountNumber; 

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