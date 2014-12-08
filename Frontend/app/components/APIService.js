angular.module('eBanking.APIService', ['ngResource'])
  
  .factory('eBankingAPIservice', ['$resource',
    function($resource) {

    var eBankingAPI = {};

    eBankingAPI.getAccounts = function(customerId) {
      return $resource('https://localhost:8080/api/customers/:customerId/accounts',
        { customerId: customerId },
        { query: { method: 'GET', } });
    }

    eBankingAPI.getAccount = function(customerId, accountId) {
      return $resource('https://localhost:8080/api/customers/:customerId/accounts/:accountId',
        { customerId: customerId, accountId: accountId },
        { query: { method: 'GET', } });
    }

    eBankingAPI.getCustomerDetails = function(customerId) {
      return $resource('https://localhost:8080/api/customers/:customerId/',
        { customerId: customerId},
        { query: { method: 'GET', } });
    }

    eBankingAPI.postCustomerDetails = function(customerId, newDetails) {
      return $resource('https://localhost:8080/api/customers/:customerId/',
        { customerId: customerId},
        { save: {
            method: 'POST',
            isArray: false,
            headers:{'Content-Type':'application/json; charset=UTF-8'} 
          }
        });
    }

    eBankingAPI.getTransactions = function(customerId, accountId, page) {
      return $resource('https://localhost:8080/api/customers/:customerId/accounts/:accountId/transactions?page=:page',
        { customerId: customerId, accountId: accountId, page: page },
        { query: { method: 'GET', } });
    }

    return eBankingAPI;
  }]);