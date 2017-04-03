var demoApp = angular.module("proyectoApp", ['ngRoute']);


demoApp.config(function ($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true);

    $routeProvider
        .when("/", {
            templateUrl: "index.html",
            controller: "homeIndexController"

        })

    $routeProvider
        .when("/page2", {
            templateUrl: "/app/page2/page2.html",
            controller: "page2Controller"

        })

    $routeProvider.otherwise({ redirectTo: "/" });



});
