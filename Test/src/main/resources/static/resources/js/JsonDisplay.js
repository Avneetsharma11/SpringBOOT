var jsonApp = angular.module('jsonApp', [ 'ui.router' ]);

jsonApp.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
			$urlRouterProvider.when('/', '/ShowDetail').otherwise('/');
			$stateProvider.state('ShowDetail', {
				url : "/Detail/{KeyName}",
				templateUrl : "resources/ui-views/Show.html",
				controller : "ShowDetails"
			}).state('AddReco', {
				url : "/AddReco",
				templateUrl : "resources/ui-views/AddReco.html",
				controller : "AddDetails"
			}).state('EdidRecord', {
				url : "/EdidRecord/{id}",
				templateUrl : "resources/ui-views/EditRecord.html",
				controller : "AddDetails"
			})
		} ]);

jsonApp.controller('ShowDetails', function($scope, jsonService, $stateParams,
		$state, $rootScope) {
	var init = function() {
		for ( var i in $scope.$parent.result) {
			if ($scope.$parent.result[i].id == $stateParams.KeyName) {
				$scope.person = $scope.$parent.result[i];
				break;
			}
		}
	}

	$scope.cancelForm = function() {
		window.location = "app";
	}
	init();
});

jsonApp.controller('AddDetails', function($scope, jsonService, $stateParams,
		$state, $rootScope) {

	var init = function() {
		if ($stateParams.id) {
			for ( var i in $scope.$parent.result) {
				if ($scope.$parent.result[i].id == $stateParams.id) {
					$scope.newRecord = angular.copy($scope.$parent.result[i]);
					break;
				}
			}
		}
	};
	
	$scope.saveContact = function() {
		jsonService.SaveData($scope.newRecord).then(function(data) {
			if (data === 'false') {
				alert("Failed to save plz... try again");
			} else {
				alert("sucessFully Added !");
				$scope.newRecord = {};
				window.location = "app";
			}
		});
	};

	$scope.cancelForm = function() {
		window.location = "app";
	}
	init();
});

jsonApp.controller('jsonAppController', function($scope, jsonService, $state,
		$stateParams, $rootScope) {
	var init = function() {
		jsonService.getData().then(function(data) {
			$scope.result = data;
		});
	}
	$scope.deleteRecord = function(id) {
		jsonService.deleteData(id).then(function(data) {
			alert("sucessFully Deleted...");
			window.location = "app";
		});
	};
	init();
});
jsonApp.run([ '$rootScope', '$state', '$stateParams',
		function($rootScope, $state, $stateParams) {
			$rootScope.$state = $state;
			$rootScope.$stateParams = $stateParams;
		} ]);