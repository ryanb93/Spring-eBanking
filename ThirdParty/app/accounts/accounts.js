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
            var test = eBankingAPIservice.postTransfer($scope.transfer.accountNumber).save($scope.transfer, function(result) {
                //Transfer should fail.
            },
            function(data, status, headers, config) {
                $scope.error.error = true;
                $scope.error.status = "Transfer Failed";
                $scope.error.message = data.data.message;
            });
        }

    }
]);