/**
 * BlogInDetailCtrl
 * /getBlogApprovalForm/:id
 * /getBlogApprovalForm/101
 */
app.controller('BlogInDetailCtrl',function($scope,BlogService,$location,$rootScope,$routeParams,$sce){
	var blogPostId=$routeParams.id //this gives the id of the blog post
	
	if(blogPostId!=undefined){
		BlogService.getBlog(blogPostId).then(function(response){
			//response.data ? single blog post object -> select * from blogpost where id=?
			//get the details shows that particular blogpost
			
			$scope.blogPost=response.data
			$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
		},function(response){
			$scope.blogPost=response.data
			if(response.ststus==401 && response.data.errorCode==5)
		$location.path('/login')
			
		})
	
	}
	
	$scope.approve=function(blogpost){
		BlogService.approve(blogpost).then(
				function(response){ //change the url to list of blogs waiting for approval
			$location.path('/blogWaitingForApproval/1')
		},function(response){
			// Not logged in or not Authorized or Exception
			$scope.error=response.data
			if(response.status==401&& response.data.errorCode==5)
			$location.path('/login')
			
			
		})
	}
	$scope.reject=function(blogpost){
		BlogService.reject(blogpost).then(
				function(response){ //change the url to list of blogs waiting for approval
			$location.path('/blogWaitingForApproval/1')
		},function(response){
			// Not logged in or not Authorized or Exception
			$scope.error=response.data
			if(response.status==401 && response.data.errorCode==5)
			$location.path('/login')
			
			
		})
	}
	
})	
	
