angular.module('customerDetailsController', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/details', {
            templateUrl: 'views/myDetailsView.html',
            controller: 'CustomerDetailsViewController'
        });
    }
])

.controller('DetailsListCtrl', ['$scope', 'Details',
    function($scope, Details) {
        $scope.details = Details.query();
    }
])

.controller('CustomerDetailsViewController', ['$scope', '$routeParams', 'Details',
    function($scope, $routeParams, Details) {
        $scope.details = Details.get(function(details) {
            $scope.details = details;
            return $scope.details;
        });
    }
]);