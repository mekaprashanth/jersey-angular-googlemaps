var sampleApp = angular.module('sampleApp', []);

sampleApp.factory("dataService", function($http, $rootScope) {
	var selectedCountry = "";
	var selectedAirport = {};
	return {
		getCountries : function(searchStr) {
			return $http.get(
					"/jersey/service/myresource/searchMatchingcountries", {
						params : {
							"countryStr" : searchStr
						}
					});
		},

		getAirports : function() {
			return $http.get(
					"/jersey/service/myresource/searchairportsbycountry", {
						params : {
							"countryStr" : selectedCountry
						}
					});
		},

		setSelectedCountry : function(country) {
			selectedCountry = country;
			$rootScope.$broadcast("startAirportSearch");
		},

		getSelectedCountry : function() {
			return selectedCountry;
		},

		setSelectedAirport : function(airportName) {
			selectedAirport = airportName;
			$rootScope.$broadcast("startMapGeneration");
		},

		getSelectedAirport : function() {
			return selectedAirport;
		}
	};

});

sampleApp.controller("countryCntrl", function($scope, dataService, $log) {
	$scope.pageHeading = "AngularJS Demo with Google Maps";
	$scope.countries = [];
	$scope.countryChange = function() {
		var promise = dataService.getCountries($scope.searchString);
		var payload;
		promise.then(function(payload) {
			$scope.countries = payload.data;
		}, function(errorPayload) {
			$log.error('failure loading movie', errorPayload);
		});
	};

	$scope.selectCountry = function(selectedCountry) {
		$scope.searchString = selectedCountry;
		dataService.setSelectedCountry(selectedCountry);
	};
});

sampleApp.controller("airportCntrl", function($scope, $http, dataService) {
	var searchAirports = function() {
		var promise = dataService.getAirports();
		var payload;
		promise.then(function(payload) {
			$scope.airportDetails = payload.data;
		}, function(errorPayload) {
			$log.error('failure loading movie', errorPayload);
		});
	};
	$scope.$on('startAirportSearch', function() {
		searchAirports();
	});

	$scope.selectAirport = function(selectedAirport) {
		$scope.airportSearch = selectedAirport.name;
		dataService.setSelectedAirport(selectedAirport);
	};
});

sampleApp.controller("mapCntrl", function($scope, $http, dataService) {
	google.maps.event.addDomListener(window, 'load', initialize);
	function initialize() {
		getLocation();
	}
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(success, error);
		} else {
			// default location
		}
	}

	function success(position) {
		var latlng = new google.maps.LatLng(position.coords.latitude,
				position.coords.longitude);

		var mapOptions = {
			center : latlng,
			scrollWheel : false,
			zoom : 12
		};

		var marker = new google.maps.Marker({
			position : latlng,
			url : '/',
			animation : google.maps.Animation.DROP
		});

		$scope.map = new google.maps.Map(document.getElementById("map-canvas"),
				mapOptions);

		marker.setMap($scope.map);
	}

	function error(msg) {
		if (msg.code == 1) {
			// PERMISSION_DENIED
		} else if (msg.code == 2) {
			// POSITION_UNAVAILABLE
		} else {
		} // TIMEOUT
	}

	$scope.$on('startMapGeneration', function() {
		var lat = dataService.getSelectedAirport().latitude;
		var lng = dataService.getSelectedAirport().longitude;
		changeMap(lat, lng);
	});

	createMarker = function(lat, lng) {
		var latlng = new google.maps.LatLng(lat, lng);
		return new google.maps.Marker({
			position : latlng,
			url : '/',
			animation : google.maps.Animation.DROP
		});
	};

	var getMapOptions = function(lat, lng) {
		return mapOptions = {
			zoom : 12,
			center : new google.maps.LatLng(lat, lng),
			mapTypeId : google.maps.MapTypeId.TERRAIN
		};
	};
	
	var changeMap = function(lat, lng) {
		$scope.map = new google.maps.Map(document.getElementById("map-canvas"),
				getMapOptions(lat, lng));
		var marker = createMarker(lat, lng);
		marker.setMap($scope.map);
	};
});

sampleApp.filter('airportFilter', function() {
	// All filters must return a function. The first parameter
	// is the data that is to be filtered, and the second is an
	// argument that may be passed with a colon (searchFor:searchString)

	return function(arr, searchString) {
		if (!searchString) {
			return arr;
		}
		var result = [];
		searchString = searchString.toLowerCase();
		// Using the forEach helper method to loop through the array
		angular.forEach(arr, function(item) {
			if ((item.name.toLowerCase().indexOf(searchString) !== -1)
					|| (item.city.toLowerCase().indexOf(searchString) !== -1)) {
				result.push(item);
			}
		});
		return result;
	};

});