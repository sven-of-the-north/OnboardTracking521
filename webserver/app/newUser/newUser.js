angular.module('newUserPage', ['ngCookies'])
	.controller('newUserCtrl', ['$scope', '$cookies', function($scope, $cookies) {
		$scope.user = {
			username: "username", 
			password: "password",
			type: ""};
		$scope.retrieved = "";
		$scope.createNewUser = function() {
			var newUser = {
				username: $scope.user.username,
				password: $scope.user.password,
				type: $scope.user.type
			};
			
			var retrievedList = $cookies.getObject('userList');
			if( typeof retrievedList == 'undefined' )
			{
				var newList = [newUser];
				$cookies.putObject('userList', newList);
			}
			else
			{
				retrievedList.push(newUser);
				$cookies.putObject('userList', retrievedList);
			}
			
			$scope.retrieved = $cookies.getObject('userList');
		};
}]);