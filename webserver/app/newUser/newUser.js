var newUserApp = angular.module('newUserPage', ['ngCookies']);

newUserApp.config(['$cookiesProvider', function($cookiesProvider) {
		$cookiesProvider.defaults.path='/';
		$cookiesProvider.defaults.domain='localhost';
	}]);
	
newUserApp.controller('newUserCtrl', ['$scope', '$cookies', function($scope, $cookies) {
	$scope.user = {
		username: "username", 
		password: "password",
		id: "1",
		type: ""};
	$scope.retrieved = "";

	$scope.createNewUser = function() {
		var newUser = {
			username: $scope.user.username,
			password: $scope.user.password,
			id: $scope.user.id,
			type: $scope.user.type,
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
	
	$scope.backToLogin = function() {
		location.href = '/'
	}
}]);