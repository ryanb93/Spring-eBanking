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

        eBankingAPIservice.getCustomerDetails().get(function(details) {
            $scope.details = details;
        });

        $scope.processForm = function() {
            var test = eBankingAPIservice.postCustomerDetails().save($scope.details, function(result) {
                console.log(result);
            });
        }

    },
]);