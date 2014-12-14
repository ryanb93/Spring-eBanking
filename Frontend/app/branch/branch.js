'use strict';

angular.module('eBanking.branchControllers', [])

.config(function(uiGmapGoogleMapApiProvider) {
    uiGmapGoogleMapApiProvider.configure({
        v: '3.17',
        libraries: 'weather,geometry,visualization'
    });
})
.controller("branchController", function($scope, uiGmapGoogleMapApi) {

        	$scope.found = false;


    $scope.findMe = function() {

    	$scope.searching = true;

        navigator.geolocation.getCurrentPosition(function(pos) {

        	$scope.found = true;

        	$scope.map = { center: { latitude: pos.coords.latitude, longitude: pos.coords.longitude}, zoom: 12 };

	        $scope.map.markers = [{
			      id: 0,
			      coords: {
			        latitude: pos.coords.latitude,
			        longitude: pos.coords.longitude
			      },
			      details: "You"
	        }, {
			      id: 1,
			      coords: {
			        latitude: pos.coords.latitude + 0.01,
			        longitude: pos.coords.longitude - 0.03
			      },
			      details: "Branch 1"
	        }, {
			      id: 2,
			      coords: {
			        latitude: pos.coords.latitude - 0.03,
			        longitude: pos.coords.longitude + 0.005
			      },
			      details: "Branch 2"
	        }, {
			      id: 3,
			      coords: {
			        latitude: pos.coords.latitude + 0.009,
			        longitude: pos.coords.longitude + 0.05
			      },
			      details: "Branch 3"
	        }];

          	uiGmapGoogleMapApi.then(function(maps) {
        		$scope.searching = false;
    		});
        }, function(error) {
          alert('Unable to get location: ' + error.message);
        });
    };
});