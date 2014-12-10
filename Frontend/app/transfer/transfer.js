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

        $scope.processForm = function() {
            var test = eBankingAPIservice.postCustomerDetails().save($scope.details, function(result) {
                console.log(result);
            });
        }

    },
]);