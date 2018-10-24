/**
 * BlogInDetailCtrl
 * /getBlogApprovalForm/:id
 * /getBlogApprovalForm/101
 */
app.controller('BlogInDetailCtrl',function($scope,BlogService,$location,$rootScope,$routeParams){
	var blogPostId=$routeParams.id //this gives the id of the blog post
	
	if(blogPostId!=undefined){
		BlogService.getBlog(blogPostId).then(function(response){
			//response.data ? single blog post object -> select * from blogpost where id=?
			//get the details shows that particular blogpost
			
			$scope.blogPost=response.data
		},function(response){
			$scope.blogPost=response.data
			if(response.ststus==401 && response.data.errorCode==5)
		$location.path('/login')
			
		})
	
	}
})	
	
