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

        eBankingAPIservice.getCustomerDetails(customerId).get(function(details) {
            $scope.details = details;
        });

        $scope.processForm = function() {
            var test = eBankingAPIservice.postCustomerDetails(customerId).save($scope.details, function(result) {
                console.log(result);
            });
        }

    },
]);