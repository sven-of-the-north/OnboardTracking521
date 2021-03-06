angular
	.module('driverPageApp', ['moment-picker', 'ngCookies'])
	.controller('driverPageCtrl', ['$scope', '$http', '$cookies', function($scope, $http, $cookies) {
		$http({
			method: 'GET',
			url: 'http://localhost:8080/events'
		}).then( function successCallback(response) {
			$scope.eventList = response.data._embedded.event;
			$scope.dataList = [];
			
			for( i = 0 ; i < $scope.eventList.length; i++ )
			{
				$scope.dataList.push( {  
					carId: $scope.eventList[i].carId,
					timeStamp: $scope.eventList[i].time,
					eventName: $scope.eventList[i].eventName,
					value: $scope.eventList[i].value
				});
			}
		}, function errorCallback(response)
		{
			alert('Error retrieving event log');
			console.log(response);
		});

/** using CORS **/		
//		var request = createCORSRequest("get", "http://localhost:8080/events/");
//		if (request){
//			request.onload = function() {
//				console.log(request);
//			};
//
//			request.send();
//		}
		
		$scope.logout = function () {
			$cookies.remove('activeUser');
			console.log("GOODBYE COOKIE");
			location.href = '../index.html';
		}

		// time filtering function for data table displaying
		$scope.displayRow = function(event){

            var currentTime = event.timeStamp;
            var activeUser = $cookies.getObject('activeUser');

            if(activeUser.type == 'driver' && !(activeUser.id == event.carId))
                return false;

			if(typeof $scope.ctrl == 'undefined')
				return true;
				
			var startTD = $scope.ctrl.startDate + ' ' + $scope.ctrl.startTime;
			var startUnix = moment(startTD).unix();
			var endTD = $scope.ctrl.endDate + ' ' + $scope.ctrl.endTime;
			var endUnix = moment(endTD).unix();
			
			if (startUnix <= currentTime && currentTime <= endUnix){
				return true;
			}
			else{
				return false;
			}
		}			
	}
]);

/** using CORS **/		
//function createCORSRequest(method, url){
//    var xhr = new XMLHttpRequest();
//    if ("withCredentials" in xhr){
//        xhr.open(method, url, true);
//    } else if (typeof XDomainRequest != "undefined"){
//        xhr = new XDomainRequest();
//        xhr.open(method, url);
//    } else {
//        xhr = null;
//    }
//    return xhr;
//}
