(function() {
    'use strict';

    angular.module('accountsViewController', ['ngRoute'])

    .config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.when('/accounts', {
                templateUrl: 'views/accountsView.html',
                controller: 'AccountsViewController'
            });
        }
    ])

    .controller('AccountsViewController', ['$scope', '$routeParams', 'Details',
        function($scope, $routeParams, Details) {
            $scope.details = Details.get(function(details) {
                $scope.details = details;
                return $scope.details;
            });
        }
    ]);

})();