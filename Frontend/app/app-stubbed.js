(function(ng) {

    console.log("==================================================");
    console.log("======== WARNING! USING A STUBBED BACKEND ========");
    console.log("======== WARNING! USING A STUBBED BACKEND ========");
    console.log("======== WARNING! USING A STUBBED BACKEND ========");
    console.log("==================================================");

    var jsonCustomer = {
        "customerId": "544be631036458271642f6bb",
        "firstName": "Stubbed",
        "lastName": "Whitefield",
        "dateOfBirth": 16523,
        "address": {
            "houseNumber": "15",
            "street": "Test Road",
            "city": "Testingville",
            "county": "Testers",
            "country": "England",
            "postalCode": "TE5 T1N"
        }
    };

    var accounts = {
        "accounts": [{
            "accountId": "544cf4610364aaa77dd7132b",
            "customerId": "544be631036458271642f6bb",
            "accountNumber": "12345678",
            "sortCode": "123456",
            "accountType": "STUBBED",
            "balance": 500.0
        }, {
            "accountId": "544cf4610364aaa77dd713b",
            "customerId": "544be631036458271642f6bb",
            "accountNumber": "87654321",
            "sortCode": "654321",
            "accountType": "STUBBED",
            "balance": 50000.0
        }]
    };

    var account = {
        "accountId": "544cf4610364aaa77dd7132b",
        "customerId": "544be631036458271642f6bb",
        "accountNumber": "12345678",
        "sortCode": "123456",
        "accountType": "STUBBED",
        "balance": 500.0
    };

    var transactions = {
        "transactions": [{
            "transactionId": "544ce5630364e8a153367ec8",
            "accountId": "544cf4610364aaa77dd7132b",
            "sender": "Stubbed Burke",
            "recipient": "Stubbed Whitefield",
            "value": 120.0,
            "date": 1413611494,
            "transactionType": "DEBIT_CARD"
        }, {
            "transactionId": "544e453d03648cdd216b1063",
            "accountId": "544cf4610364aaa77dd7132b",
            "sender": "Stubbed Burke",
            "recipient": "Stubbed Whitefield",
            "value": 50000.0,
            "date": 1414611494,
            "transactionType": "CASH"
        }]
    };


    ng.module('eBanking')
        .run(function($httpBackend) {

            //Let all html views through.
            $httpBackend.whenGET(/.*.html/).passThrough();

            $httpBackend.whenPOST(/.*/).passThrough();

            $httpBackend.whenGET(/http:\/\/localhost:8080\/api\/customers\/.*\/accounts\/.*\/transactions/).respond(function(method, url, data) {
                return [200, transactions, {}];
            });

            $httpBackend.whenGET(/http:\/\/localhost:8080\/api\/customers\/.*\/accounts\/.*/).respond(function(method, url, data) {
                return [200, account, {}];
            });

            $httpBackend.whenGET(/http:\/\/localhost:8080\/api\/customers\/.*\/accounts/).respond(function(method, url, data) {
                return [200, accounts, {}];
            });

            $httpBackend.whenGET(/http:\/\/localhost:8080\/api\/customers\/.*/).respond(function(method, url, data) {
                return [200, jsonCustomer, {}];
            });

        });
})(angular);