jsonApp.factory("jsonService", function($http) {
	var jsonService = {};
	jsonService.getData = function() {
		
		var promise = $http({
			'url' : 'persons',
			'method' : 'GET',
			'headers' : {
				'Content-Type' : 'application/json'
			},
		}).then(function(response) {
			//alert(JSON.stringify(response.data));
			return response.data;
		});

		return promise;
	};
	
// Add Data	
	jsonService.SaveData = function(UserData) {
		var promise = $http({
			'url' : 'person/add',
			'method' : 'POST',
			'headers' : {'Content-Type' : 'application/json'},
			'data' : UserData
		}).then(function(response) {
			return response.data;
		});
		return promise;
	};
	
	jsonService.deleteData = function(id) {
		var promise = $http({
			'url' : 'remove',
			'method' : 'POST',
			'headers' : {'Content-Type' : 'application/json'},
			'params' : {id : id}
		}).then(function(response) {
			return response.data;
		});
		return promise;
	};

	return jsonService;

});