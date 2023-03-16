# AlbumsLBC

This application will download a list of albums from this url : https://satic.leboncoin.fr/img/shared/technical-test.json by displaying albums's thumbnail and displaying an overview of each album with a bigger image.

# Features

- Displaying a list of albums in a recycler view by adding a shimmer effect that give to user an information that the image is loading.
- By clicking in a row of this recycler view it will redirect the user to the album overview with the album's image in bigger size.

# Architecture
- The architecture is made up of the MVVM pattern which allows to separate the view from the data so that the view is not dependent on any specific model platform.
- This app use LiveData feature to ensure the UI marches the data state, ensure no memory leaking and no crashes due to activities lifecycle changes.

# Third party libraries

## Retrofit - https://square.github.io/retrofit/
- Retrofit is a type-safe HTTP client for Android and Java. It turns our HTTP API into a Java/Kotlin interface.
- Provides easy to use headers , custom headers and requests types.
- Easy to add converters like Moshi, Gson, etc..

## Picasso - https://square.github.io/picasso/
- Picasso is a powerful image downloading and caching library for Android
- Handling ImageView recycling and download cancellation in an adapter.
- Complex image transformations with minimal memory use.
- Automatic memory and disk caching.

## Room - https://developer.android.com/topic/libraries/architecture/room
- The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
- It provides a mocking of your models by using the qualifiers.

## Shimmer - https://github.com/facebook/shimmer-android
- Shimmer is an Android library that provides an easy way to add a shimmer effect to any view in your Android app.
- It is useful as an unobtrusive loading indicator, and was originally developed for Facebook Home.

![Simplified Diagram](https://user-images.githubusercontent.com/34271209/225513086-f6550720-8d5e-41d1-a0ee-5f81a028f200.png)

![Detailed Diagram](https://user-images.githubusercontent.com/34271209/225513070-5ac42702-79cf-4748-9b91-a33c9dfa54af.png)

