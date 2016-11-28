angular.module('myApp', [])
	.controller('formCtrl', function($scope) {
		$scope.user = {
			username: "username", 
			password: "password",
			type: ""};
		$scope.createNewUser = function() {
			alert("Attemping create new user with this information: \nUsername: " + $scope.user.username + 
			" Password: " + $scope.user.password +
			" Type: " + $scope.user.type ); };
});