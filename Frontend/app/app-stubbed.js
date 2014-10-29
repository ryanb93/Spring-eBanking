(function(ng) {

    console.log("==================================================");
    console.log("======== WARNING! USING A STUBBED BACKEND ========");
    console.log("======== WARNING! USING A STUBBED BACKEND ========");
    console.log("======== WARNING! USING A STUBBED BACKEND ========");
    console.log("==================================================");

    var jsonCustomer = {
        "customerId": "544be631036458271642f6bb",
        "firstName": "Jorden",
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
        "accountIds": ["544cf4610364aaa77dd7132a", "544cf4610364aaa77dd7132b", "544cf4610364aaa77dd7132c"]
    };

    var account = {
        "accountId": "544cf4610364aaa77dd7132b",
        "customerId": "544be631036458271642f6bb",
        "accountNumber": "12345678",
        "sortCode": "123456",
        "accountType": "CURRENT",
        "balance": 700.0
    };

    var transactions = {"transactions": [{
        "transactionId": "544ce5630364e8a153367ec8",
        "accountId": "544cf4610364aaa77dd7132b",
        "sender": "Ryan Burke",
        "recipient": "Jorden Whitefield",
        "value": 100.0,
        "date": 12345
    }, {
        "transactionId": "544e453d03648cdd216b1063",
        "accountId": "544cf4610364aaa77dd7132b",
        "sender": "Ryan EEEE Burke",
        "recipient": "Jorden Whitefield",
        "value": 500.0,
        "date": 12345676
    },{
        "transactionId": "544ce5630364e8a153367ec8",
        "accountId": "544cf4610364aaa77dd7132b",
        "sender": "Ryan Burke",
        "recipient": "Jorden Whitefield",
        "value": 100.0,
        "date": 12345
    }, {
        "transactionId": "544e453d03648cdd216b1063",
        "accountId": "544cf4610364aaa77dd7132b",
        "sender": "Ryan EEEE Burke",
        "recipient": "Jorden Whitefield",
        "value": 500.0,
        "date": 12345676
    },]};


    ng.module('eBanking')
        .run(function($httpBackend) {

            //Let all html views through.
            $httpBackend.whenGET(/.*.html/).passThrough();

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