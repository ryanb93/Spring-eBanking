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

        eBankingAPIservice.getAccounts().query(function(ids) {
            $scope.accountsList = ids;
        });

        $scope.error = {};
        $scope.error.error = false;


        $scope.processTransfer = function() {
            var test = eBankingAPIservice.postTransfer($scope.transfer.senderAccountNumber).save($scope.transfer, function(result) {
                console.log(result);
            },
            function(data, status, headers, config) {
                $scope.error.error = true;
                $scope.error.status = data.status;
                $scope.error.message = data.statusText;
                console.log($scope.error);
            });
        }

    }
]);