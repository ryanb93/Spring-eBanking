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
            var test = eBankingAPIservice.postTransfer($scope.transfer.senderAccountNumber).save($scope.transfer, function(result) {
                console.log(result);
            });
        }

        $scope.processPayment = function() {
            var test = eBankingAPIservice.postTransfer($scope.payment.senderAccountNumber).save($scope.payment, function(result) {
                console.log(result);
            });
        }

    },
]);