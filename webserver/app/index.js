angular.module('loginPage', ['ngCookies'])
	.controller('loginCtrl', ['$scope', '$cookies', function($scope, $cookies) {
    $scope.user = {
        username:"username", 
        password:"password"};
	$scope.loginSuccess = false;
	
    $scope.login = function() {
		$scope.loginSuccess = false;
		
		var retrievedList = $cookies.getObject('userList');
		if ( typeof retrievedList == 'undefined' )
			return;
		
		for( i = 0; i < retrievedList.length; ++i )
		{
			if( retrievedList[i].username == $scope.user.username &&
				retrievedList[i].password == $scope.user.password )
				{
					$scope.loginSuccess = true;	
					location.href = 'dataPage/dataPage.html';
					break;
				}
		}
		
		if ( $scope.loginSuccess == false )
			alert("Invalid login information, please try again.");
		
	};
	$scope.newUser = function() {
		location.href = 'newUser/newUser.html';
	};
}]);
