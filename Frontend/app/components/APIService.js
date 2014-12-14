angular.module('eBanking.APIService', ['ngResource']).
	
	factory('eBankingAPIservice', ['$resource',
		function($resource) {

		var eBankingAPI = {};
		eBankingAPI.route = 'https://localhost:8080/api';

		eBankingAPI.getAccounts = function() {
			return $resource(eBankingAPI.route + '/customer/accounts');
		}

		eBankingAPI.getAccount = function(accountId) {
			return $resource(eBankingAPI.route + '/customer/accounts/:accountId',
				{ accountId: accountId },
				{ query: { method: 'GET'} });
		}

		eBankingAPI.getCustomerDetails = function() {
			return $resource(eBankingAPI.route + '/customer/');
		}

		eBankingAPI.postCustomerDetails = function() {
			return $resource(eBankingAPI.route + '/customer/',
				{},
				{ save: {
						method: 'POST',
						isArray: false,
						headers:{'Content-Type':'application/json; charset=UTF-8'} 
					}
				});
		}

		eBankingAPI.postTransfer = function(accountId) {
			return $resource(eBankingAPI.route + '/customer/accounts/:accountId/transactions',
				{ accountId: accountId },
				{ save: {
						method: 'POST',
						isArray: false,
						headers:{'Content-Type':'application/json; charset=UTF-8'} 
					}
				});
		}

		eBankingAPI.getTransactions = function(accountId, page) {
			return $resource(eBankingAPI.route + '/customer/accounts/:accountId/transactions?page=:page',
				{ accountId: accountId, page: page },
				{ query: { method: 'GET', isArray: true} });
		}

		return eBankingAPI;
}]);