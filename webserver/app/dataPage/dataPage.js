angular
	.module('driverPageApp', ['moment-picker'])
	.controller('driverPageCtrl', ['$scope', function($scope) {
		
		
		$scope.test = function () {
			console.log($scope.ctrl);
		}
	}
]);
