angular.module('loginPage', [])
	.controller('loginCtrl', function($scope) {
    $scope.user = {
        username:"username", 
        password:"password"};
    $scope.login = function() {
        alert("Attemping login with this information: \nUsername: " + $scope.user.username + 
                                                      " Password: " + $scope.user.password );
	};
	$scope.newUser = function() {
		location.href = 'newUser/newUser.html';
	};
});
