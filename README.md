# Weather App

This application gets user location and shows current weather information.

<img src="docs/weather_app.jpg" width="300" />


## Tech

These tools have been used in this project:

1. [Kotlin](https://kotlinlang.org)
2. [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
3. [Retrofit](https://square.github.io/retrofit/)
3. [Moshi](https://github.com/square/moshi)
7. [Espresso](https://developer.android.com/training/testing/espresso)
7. [View Model](https://developer.android.com/topic/libraries/architecture/viewmodel)
8. [Live Data](https://developer.android.com/topic/libraries/architecture/livedata)
9. [Navigation](https://developer.android.com/guide/navigation)
10. [View Binding](https://developer.android.com/topic/libraries/view-binding)
11. [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
12. [JUnit](https://junit.org/junit4/)
13. [Glide](https://bumptech.github.io/glide/)
14. [Ktlint](https://github.com/pinterest/ktlint)


Only tested on

1. Samsung Galaxy Note 10+
2. Pixel 3a
3. Pocophone f1

## Design

The design is minimalistic. I made it with the sketch app.
You can get the sketch file [here](https://www.dropbox.com/sh/55yg7fh3wuqlu2w/AAA5YfU6kGrBbG2ZMWNgWYFta?dl=0)


## Archicture

[Clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) was used to perform separation of concerns.
[MVVM](https://en.wikipedia.org/wiki/Model–view–viewmodel) and 
[repository pattern](https://developer.android.com/jetpack/guide) were used in presentation 
and data layers accordingly. 

## Backend

[Weather Api](https://www.weatherapi.com) was used to for the remote data.
You will need an access key to get the app working.


## Download

You can download the apk directly from [here](https://www.dropbox.com/sh/wexu0lwdzdo1uvx/AAAGinxoexrYc436RIjGogH1a?dl=0).