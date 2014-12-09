angular.module('eBanking.APIService', ['ngResource'])
  
  .factory('eBankingAPIservice', ['$resource',
    function($resource) {

    var eBankingAPI = {};

    eBankingAPI.getAccounts = function() {
      return $resource('https://localhost:8080/api/customer/accounts');
    }

    eBankingAPI.getAccount = function(accountId) {
      return $resource('https://localhost:8080/api/customer/accounts/:accountId',
        { accountId: accountId },
        { query: { method: 'GET'} });
    }

    eBankingAPI.getCustomerDetails = function() {
      return $resource('https://localhost:8080/api/customer/');
    }

    eBankingAPI.postCustomerDetails = function(customerId, newDetails) {
      return $resource('https://localhost:8080/api/customer/',
        { save: {
            method: 'POST',
            isArray: false,
            headers:{'Content-Type':'application/json; charset=UTF-8'} 
          }
        });
    }

    eBankingAPI.getTransactions = function(accountId, page) {
      return $resource('https://localhost:8080/api/customers/accounts/:accountId/transactions?page=:page',
        { accountId: accountId, page: page },
        { query: { method: 'GET', } });
    }

    return eBankingAPI;
  }]);