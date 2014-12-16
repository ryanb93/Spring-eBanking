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

        $scope.displayDetailsError = function(message) {
                $scope.messages.details.err.err = true;
                $scope.messages.details.success.success = false;
                $scope.messages.details.err.stat = "Details Update Failed";
                $scope.messages.details.err.message = message;
                return;
        }

        $scope.processForm = function() {

            //Front end validation on the sender account number existing.
            if(!$scope.details.firstName) {
                return $scope.displayDetailsError("You must enter a first name.");
            }
            if(!$scope.details.lastName) {
                return $scope.displayDetailsError("You must enter a last name.");
            }
            if(!$scope.details.address.houseNumber) {
                return $scope.displayDetailsError("You must enter a house number.");
            }
            if(!$scope.details.address.street) {
                return $scope.displayDetailsError("You must enter a street.");
            }
            if(!$scope.details.address.postCode) {
                return $scope.displayDetailsError("You must enter a post code.");
            }
            if(!$scope.details.address.city) {
                return $scope.displayDetailsError("You must enter a city.");
            }
            if(!$scope.details.address.county) {
                return $scope.displayDetailsError("You must enter a county.");
            }
            if(!$scope.details.address.country) {
                return $scope.displayDetailsError("You must enter a country.");
            }

            var test = eBankingAPIservice.postCustomerDetails().save($scope.details, function(result) {
                $scope.messages.details.err.err = false;
                $scope.messages.details.success.success = true;
                $scope.messages.details.success.title = "Thank you";
                $scope.messages.details.success.message = "Your details have been updated successfully."; 
            }, 
            function(data, status, headers, config) {
                $scope.displayDetailsError(data.data.message);
            });

        };
    },
]);