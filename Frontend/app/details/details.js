'use strict';

angular.module('eBanking.detailsControllers', [])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/details', {
            templateUrl: 'details/details.html',
        });
    }
])

.controller('detailsController', ['$scope', 'eBankingAPIservice',
    function($scope, eBankingAPIservice) {

        var customerId = "544be631036458271642f6bb";

        eBankingAPIservice.getAccounts(customerId).get(function(ids) {
            
            var accountIds = ids.accountIds;
            
            $scope.accountsList = [];

            //We have a bug here because results callback at different times the list order is random.
            angular.forEach(accountIds, function(accountId) {
                eBankingAPIservice.getAccount(customerId, accountId).get(function(details) {
                    $scope.accountsList.push(details);
                });
            });

        });

    }
]);