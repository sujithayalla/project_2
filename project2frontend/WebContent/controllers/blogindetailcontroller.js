/**
 * BlogInDetailCtrl
 * /getBlogApprovalForm/:id
 * /getBlogApprovalForm/101
 */
app.controller('BlogInDetailCtrl',function($scope,BlogService,$location,$rootScope,$routeParams,$sce){
	var blogPostId=$routeParams.id //this gives the id of the blog post
	$scope.isRejected=false
	if(blogPostId!=undefined){
	BlogService.getBlog(blogPostId).then(function(response){
		//response.data ? single blog post object -> select * from blogpost where id=?
		//get the details about that particular blogpost
		$scope.blogPost=response.data
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
	},function(response){
		$scope.error=response.data 
		if(response.status==401 && response.data.errorCode==5)
			$location.path('/login')
	})
	}
	
	$scope.approve=function(blogPost){
		BlogService.approve(blogPost).then(
		function(response){//change the url to list of blogs waiting for approval
			$location.path('/blogsWaitingForApproval/1')
		},function(response){
			// Not logged in or not authorized or exception 
			$scope.error=response.data //Not authorized or any exception in blogApprovalForm.html
			if(response.status==401 && response.data.errorCode==5)//Not loggedin, login.html
				$location.path('/login')
		})
	}
	
	$scope.reject=function(blogPost,rejectionReason){
		if(rejectionReason==undefined)
			rejectionReason='Not Mentioned by Admin'
		BlogService.reject(blogPost,rejectionReason).then(
				function(response){
					$location.path('/blogsWaitingForApproval/1')
				},function(response){
					$scope.error=response.data //Not authorized or any exception in blogApprovalForm.html
					if(response.status==401 && response.data.errorCode==5)//Not loggedin, login.html
						$location.path('/login')
				})
	}
	
	$scope.showTxtForRejectionReason=function(){
		$scope.isRejected=!$scope.isRejected
	}
})



	
