## SpaceX Launches

A simple demo project to display the past rocket launches  of spacex based on MVVM clean architecture.
Below are the screeshots of the application

![Figure 1](/media/SplashScreen.jpg=250*350)
![Figure 2](/media/LaunchListScreen.jpg=250*350)
![Figure 3](/media/LaunchDetailScreen.jpg=250*350) 

### App Features

* Users can view list of past spacex launches with basic details available about the launch.
* Users can tap and see much more details about the launch.
* Detail screen might also have media items like video, wikipedia and other article.
* Users can tap the media.

### App Packages

* common - Contains common class files.

    * di        - Depenency Injection Classes using Dagger2.
    * model     - Data classes.
    * recycler  - Base and common recyclerview related class files.
    * AppUtil   - Util class file.
    * SpaceXDatabase - Room Database class
    
* detail - Holds classes used for detail screen.

    * model - Data classes related to detail screen.
    * ui    - Activities and Holders of detail screen.
    
* list - Contains classes to used to list spacex launches.

    * di - Depenency Injection Classes using Dagger2.
    * endpoint - Service endpoint
    * repository - Viewmodel's repository class to make remote service / db calls.
    * ui - Activities and Holders of list screen.
    * viewmodel - List screens ViewModel and ViewModelFactory classes.
 
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


