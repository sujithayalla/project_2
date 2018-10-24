/**
 * BlogService
 */
app.factory('BlogService',function($http){
	var blogService={}
	
	blogService.addBlog=function(blog){
		return $http.post("http://localhost:9090/project2middleware/addblogpost",blog)
	}
	
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get("http://localhost:9090/project2middleware/blogswaitingforapproval")
	}
	
	blogService.getBlogsApproved=function(){
		return $http.get("http://localhost:9090/project2middleware/blogsapproved")
	}
	
	blogService.getBlog=function(blogPostId){
		return $http.get("http://localhost:9090/project2middleware/getBlog/"+blogPostId)
	}
	
	return blogService;
})
