/**
 * UserCtrl
 */
app.controller('UserCtrl',function($scope,UserService,$location,$rootScope){
	
	$scope.registration=function(user){//user object from view 
		UserService.registration(user)//to service
		.then(function(response){
			$location.path('/login')
		},function(response){
			$scope.error=response.data
		})
	}
	
	$scope.login=function(user){
		UserService.login(user).then(
				function(response){
					//response.data is user object
					$rootScope.user=response.data
					$location.path('/home')
				},
				function(response){
					$scope.error=response.data
				})
	}
	
	
	$rootScope.logout=function(){//function is called from index.html
	  alert('logout function is called')
	  UserService.logout().then(function(response){
		  delete $rootScope.user
		  $location.path('/login')
	  },function(response){
		  delete $rootScope.user
		  if(response.status==401)//UNAUTHORIZED
			  $location.path('/login')
	  })
	}
	
	
})
