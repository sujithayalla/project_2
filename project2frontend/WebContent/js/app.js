/**
 * Angular Module
 */
var app=angular.module("app",['ngRoute'])

app.config(function($routeProvider){
	$routeProvider
	.when('/registration',{controller:'UserCtrl',templateUrl:'views/registration.html'})
	.when('/login',{controller:'UserCtrl',templateUrl:'views/login.html'})
	.when('/home',{templateUrl:'views/home.html'})
	.otherwise({templateUrl:'views/home.html'})
})

