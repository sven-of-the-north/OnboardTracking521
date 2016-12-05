angular
	.module('driverPageApp', ['moment-picker'])
	.config(['$sceDelegateProvider', function($sceDelegateProvider) {
		$sceDelegateProvider.resourceUrlWhitelist([
		'self',
		'http://localhost:8080',
		'http://localhost:8081' ])}
	])
	.controller('driverPageCtrl', ['$scope', '$http', '$templateCache', 
	function($scope, $http, $templateCache) {
		
/** standard get request **/
//		$http({
//			method: 'GET',
//			url: 'http://localhost:8080/events'
//		}).then( function successCallback(response) {
//			console.log(response);
//		}, function errorCallback(response)
//		{
//			console.log(response);
//		});

/** using CORS **/		
		var request = createCORSRequest("get", "http://localhost:8080/events/");
		if (request){
			request.onload = function() {
				console.log(request);
			};

			request.send();
		}
		
		$scope.test = function () {
			console.log($scope.ctrl);
		}
	}
]);

/** using CORS **/		
function createCORSRequest(method, url){
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr){
        xhr.open(method, url, true);
    } else if (typeof XDomainRequest != "undefined"){
        xhr = new XDomainRequest();
        xhr.open(method, url);
    } else {
        xhr = null;
    }
    return xhr;
}
