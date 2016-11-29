angular.module('myApp', [])
	.controller('formCtrl', function($scope) {
    $scope.user = {
        username:"username", 
        password:"password"};
    $scope.login = function() {
        alert("Attemping login with this information: \nUsername: " + $scope.user.username + 
                                                      " Password: " + $scope.user.password );
	};
	$scope.newUser = function() {
		alert("TODO: go to new user creation page");
    };
});
