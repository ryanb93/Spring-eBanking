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

        $scope.processTransfer = function() {
            console.log("Transfer Requested");
            console.log($scope.transfer);
            var test = eBankingAPIservice.postTransfer($scope.transfer.senderAccountNumber).save($scope.transfer, function(result) {
                console.log(result);
            });
        }

        $scope.processPayment = function() {
            var test = eBankingAPIservice.postPayment().save($scope.payment, function(result) {
                console.log(result);
            });
        }

    },
]);