angular.module('eBanking.APIService', ['ngResource'])
  
  .factory('eBankingAPIservice', ['$resource',
    function($resource) {

    var eBankingAPI = {};

    eBankingAPI.getAccounts = function(customerId) {
      return $resource('http://localhost:8080/api/customers/:customerId/accounts',
        { customerId: customerId },
        { query: { method: 'GET', } });
    }

    eBankingAPI.getAccount = function(customerId, accountId) {
      return $resource('http://localhost:8080/api/customers/:customerId/accounts/:accountId',
        { customerId: customerId, accountId: accountId },
        { query: { method: 'GET', } });
    }

    eBankingAPI.getCustomerDetails = function(customerId) {
      return $resource('http://localhost:8080/api/customers/:customerId/',
        { customerId: customerId},
        { query: { method: 'GET', } });
    }

    eBankingAPI.getTransactions = function(customerId, accountId) {
      return $resource('http://localhost:8080/api/customers/:customerId/accounts/:accountId/transactions',
        { customerId: customerId, accountId: accountId },
        { query: { method: 'GET', } });
    }

    return eBankingAPI;
  }]);