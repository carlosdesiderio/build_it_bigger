# Build It Bigger Project
Udacity Android Nanodegree Project FOUR submission : Gradle for Android project

## Project Specification

In this project, an app is created with multiple flavours that uses
multiple libraries and Google Cloud Endpoints. The app consists
of four modules:
* ["javajokes"](/javajokes) :<br /> A Java library that provides jokes. The main module
 has 
 a project dependency between the app and the new Java Library. 
* ["jokedisplay"](/jokedisplay) :<br /> An Android Library containing an activity for displaying jokes. 
The main module has a project dependency between the app and the new Android Library. 
 When the button is selected a joke from the Java Library is passed to the Android Library to be displayed. 
* [backend_v2](/backend_v2) :<br /> A Google Cloud Endpoints (GCE) project that serves those 
jokes. The main module has a project dependency between the app and the GCE module. An Async task
 is used to retrieve jokes from the App Engine.
* [app](/app):<br /> An Android app that fetches jokes from the GCE module and passes them to the Android Library 
for display. <br /> <br /> 
The project also has the following features:<br /> 
* Connected tests to verify that the async task is loading jokes.
* Project contains paid/free flavors. The paid flavor has no ads.


###Initial code 
The initial code can be found in the
[course repository](https://github.com/udacity/ud867/tree/master/FinalProject).


### Other Project Components

#### Google Cloud Endpoint & AppEngine Module:

The initial request was for a Google Cloud Endpoints Module to be created in the Android Studio IDE. However, at the 
moment of development and
 starting from Android Studio 3.0, the ability to create an App Engine backends had been 
 removed. 
 
 Several changes to the GCE module gradle files have to be carried out in order to transition the 
 project to the new framework version. Instruction at the
 [Transition Android 
  Projects readme page](https://github.com/GoogleCloudPlatform/endpoints-framework-gradle-plugin/blob/master/ANDROID_README.md#transitioning-android-projects)
  were followed.

 The library was deployed using the App Engine Plugin at the terminal using the following 
 resources:
 * [App Engine Plugin readme pages](https://github.com/GoogleCloudPlatform/app-gradle-plugin)
 * [App Engine Standard & Google Cloud 
 Endpoints Frameworks & Java readme pages](https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/appengine-java8/endpoints-v2-backend)
 

#### Interstitial Ad

The free app variant display interstitial ads between the main activity and the joke activity

#### Loading Indicator

A loading indicator is shown while the joke is being retrieved and
disappears when the joke is ready.

#### Functional Tests and Configuring a Test Task

A connected test was implemented to check that the async task successfully retrieves a non-empty
string. 

Also a Gradle task was added to run test locally using the GCE dev server. The 
[runDaemonServerAndroidTest](https://github.com/carlosdesiderio/build_it_bigger/blob/8526f438cd11f799cd7400168736a6282242b42a/build.gradle#L29) 
task carries out the following:
1. Launches the GCE local development server
2. Runs all tests
3. Shuts the server down again

To run the task, use the following command from the project root folder:

```text
$ ./gradlew runDaemonServerAndroidTest
```
#### Libraries
This is a list of the 
Google Cloud libraries used in the project:

**Admob libraries**
```text
    play-services-ads
    google-http-client-android
```
**GC Endpoint Framework v2 libraries for appengine deployment**<br />
   ```text
    endpoints-framework
    endpoints-management-control-appengine
    endpoints-framework-auth
```
* **GC Endpoint Framework v2 client libraries**
```text
    google-api-client
    google-http-client-android
```

For a list of support and other Android libraries, see the 
[build.gradle](app/build.gradle)file

### Contact:
labs@desiderio.me.uk

