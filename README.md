# AndroidRetrofit_Plus_SpringBoot_HotelMenu_App-with-FrontEnd-and-BackEnd


This Android project is a simple app that connects to a Spring Boot backend server using Retrofit. 

The app allows users to list, create, update, and delete items. The backend server is implemented using Spring Boot and MySQL.


======    To use the project, you will need to:    ========

1.Install the Android Studio IDE.

2.Clone the project from GitHub.

3.Import the project into Android Studio.

4.Set up the backend server.

5.Run the app.

6.The app is divided into two main activities:

The MainActivity is the home screen of the app. It displays a list of items.

The AddItemActivity allows users to create new items.

The app uses Retrofit to communicate with the backend server. Retrofit is a type-safe HTTP client for Android. It makes it easy to send and receive JSON data from a RESTful API.



The backend server is implemented using Spring Boot. Spring Boot is a framework that makes it easy to create stand-alone, production-grade Spring applications.

It provides a number of features that make it easy to develop RESTful APIs, such as auto-configuration and embedded Tomcat.

The MySQL database is used to store the data for the app. MySQL is a popular open source relational database management system.

===  some additional dependencies usen in (Android side) project ===

Retrofit dependencies

    implementation("com.squareup.retrofit2:retrofit:2.7.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.7.2")
    implementation ("com.squareup.okhttp3:okhttp:3.6.0")

Dependency to avoid duplicate class found Error

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
