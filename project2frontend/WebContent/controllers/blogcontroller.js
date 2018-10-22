/**
 * BlogCtrl
 */
app.controller('BlogCtrl',function($scope,BlogService,$location){
	$scope.addBlog=function(blog){
		BlogService.addBlog(blog).then(
				function(response){
					alert('Blog details inserted successfully and it is waiting for approval')
					$location.path('/home')
				},
				function(response){
					$scope.error=response.data
					if(response.status==401)
						$location.path('/login')
				})
	}
})

