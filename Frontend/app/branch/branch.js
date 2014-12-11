'use strict';

angular.module('eBanking.branchControllers', [])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/branch', {
            templateUrl: 'branch/branch.html',
        });
    }
]);