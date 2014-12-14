'use strict';

angular.module('eBanking.detailsControllers', [])

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
                $scope.messages.details.success.title = "Thank you";
                $scope.messages.details.success.message = "Your details have been updated successfully."; 
            }, 
            function(data, status, headers, config) {
                console.log(data);
                console.log(data.message);
                $scope.messages.details.err.err = true;
                $scope.messages.details.success.success = false;
                $scope.messages.details.err.title = "Update Failed";
                $scope.messages.details.err.message = data.data.message;
            });

        };
    },
]);