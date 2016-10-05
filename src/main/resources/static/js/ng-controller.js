angular.module('TIYAngularApp', [])
   .controller('SampleController', function($scope, $http) {

        $scope.getMessages = function() {
            console.log("About to go get me some data!");

            $http.get("/messages.json")
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.messages = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };

        $scope.addMessage = function(message) {
            $http.post("/addMessage.json", $scope.message)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.messages = $scope.getMessages();

                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };

        $scope.login = function() {
                    console.log("About to go get me some data!");

                    $http.post("/login.json", $scope.userLogin)
                        .then(
                            function successCallback(response) {
                                console.log(response.data);
                                console.log("Adding data to scope");
                                user = {}
                                user = response.data;
                                $scope.message.user = user;
                            },
                            function errorCallback(response) {
                                console.log("Unable to get data");
                            });
                };
        $scope.logout = function() {
            $scope.userLogin.name = null;
            $scope.userLogin.password = "";
            $scope.message.user = {};
        }
        $scope.message = {};
        $scope.message.user = {};
        $scope.userLogin = {};
        $scope.messages = {};
        console.log("Controller initialized!!");
        $scope.getMessages();
    });