angular
	.module('driverPageApp', ['moment-picker'])
	.controller('driverPageCtrl', ['$scope', '$http', function($scope, $http) {
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
		
		$scope.test = function () {
			console.log($scope.ctrl);
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
