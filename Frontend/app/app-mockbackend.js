(function(ng) {

    console.log("------- WARNING!!! USING STUBBED BACKEND -------");

    setupStubs();


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

    var accounts = {"accountIds":["544cf4610364aaa77dd7132a", "544cf4610364aaa77dd7132b", "544cf4610364aaa77dd7132c"]};    

    var account = {"accountId":"544cf4610364aaa77dd7132b","customerId":"544be631036458271642f6bb","accountNumber":"12345678","sortCode":"123456","accountType":"CURRENT","balance":500.0};           


    function setupStubs() {
        ng.module('eBankingApp')
            .run(function($httpBackend) {
                //Let all views through.
                $httpBackend.whenGET(/views\/.*/).passThrough();


                $httpBackend.whenGET(/http:\/\/localhost:8080\/api\/customers\/.*\/accounts\/.*/).respond(function(method, url, data) {
                    console.log("Getting stubbed single account list.");
                    return [200, account, {}];
                });

                $httpBackend.whenGET(/http:\/\/localhost:8080\/api\/customers\/.*\/accounts/).respond(function(method, url, data) {
                    console.log("Getting stubbed customer accounts list.");
                    return [200, accounts, {}];
                });

                $httpBackend.whenGET(/http:\/\/localhost:8080\/api\/customers\/.*/).respond(function(method, url, data) {
                    console.log("Getting stubbed customer details.");
                    return [200, jsonCustomer, {}];
                });

            });
    }
})(angular);