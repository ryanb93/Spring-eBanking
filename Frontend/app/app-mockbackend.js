(function(ng) {

    console.log("------- WARNING!!! USING STUBBED BACKEND -------");

    setupStubs();

    function setupStubs() {
        ng.module('eBankingApp')
            .run(function($httpBackend) {
                //Let all views through.
                $httpBackend.whenGET(/views\/.*/).passThrough();

                $httpBackend.whenGET(/http:\/\/localhost:8080\/api\/customers\/.*/).respond(function(method, url, data) {
                    console.log("Getting stubbed customer details.");

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

                    return [200, jsonCustomer, {}];
                });

            });
    }
})(angular);