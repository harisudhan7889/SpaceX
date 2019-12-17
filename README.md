## SpaceX Launches

A simple demo project to display the past rocket launches  of spacex based on MVVM clean architecture.
Below are the screeshots of the application

<img src="https://github.com/harisudhan7889/SpaceX/blob/master/media/SpacexScreens.jpg" width="920" style="max-width:920%;">

### App Features

* Users can view list of past spacex launches with basic details available about the launch.
* Users can tap and see much more details about the launch.
* Detail screen might also have media items like video, wikipedia and other article.
* Users can tap the media.

### Flow Diagram

<img src="https://github.com/harisudhan7889/SpaceX/blob/master/media/FlowDiagram.png" width="920" style="max-width:920%;">

### App Packages

* common - Contains common class files.

    * archcomponent - Base Android Architecture Class files.  
    * di        - Depenency Injection Classes using Dagger2.
    * extension - Kotlin Extension Functions
    * model     - Data classes.
    * recycler  - Base and common recyclerview related class files.
    * ui        - Base abstract ui components.
    * widget    - Common custom view
    * AppUtil   - Util class file.
    * SpaceXDatabase - Room Database class
    
* detail - Holds classes used for detail screen.

    * archcomponent - Android Architecture Component Class files.
    * model - Data classes related to detail screen.
    * ui    - Activities and Holders of detail screen.
    
* list - Contains classes to used to list spacex launches.

    * archcomponent - Android Architecture Component, ViewModel and Usecase.
    * di - Depenency Injection Classes using Dagger2.
    * endpoint - Service endpoint
    * ui - Activities and Holders of list screen.

* di - Main Dependency Injection Classes.    
 
### App spec

* Kotlin
* Java 
* MVVM Architecture
* Android Architecture Components (LiveData, Lifecycle, ViewModel, Room Persistence Library, ConstraintLayout)
* RxJava2 for implementing Observable pattern.
* Dagger 2 for dependency injection.
* Retrofit 2 for API integration.
* Gson for serialisation.
* Picasso for image loading.
* Timber for planting logs.


