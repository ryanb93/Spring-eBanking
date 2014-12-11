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

        $scope.messages = {};
        $scope.messages.details = {};
        $scope.messages.details.err = {};
        $scope.messages.details.success = {};
        $scope.messages.details.err.err = false;
        $scope.messages.details.success.success = false;

        $scope.processForm = function() {
            var test = eBankingAPIservice.postCustomerDetails().save($scope.details, function(result) {
                $scope.messages.details.success.success = true;
                $scope.messages.details.err.err = false;
                $scope.messages.details.success.message = "Thank you, your details have been updated successfully."; 
            }, 
            function(data, status, headers, config) {
                $scope.messages.details.err.err = true;
                $scope.messages.details.success.success = false;
                $scope.messages.details.err.stat = data.status;
                $scope.messages.details.err.message = data.statusText;
            });

        };
    },
]);