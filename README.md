# MvpExample - Android Code Challenge Solution for getting Data from Marvel Api

## Android development

The app is built in a multi-module structure and attempts to use the latest tools and libraries. In
a summary:

* Entirely written in Kotlin.
* Uses Mvp Architecture, Clean Architecture, Clean Code.
* Uses Room as data persistence, Retrofit for network request handling.
* Uses Kotlin Coroutines throughout threading.
* Uses Hilt for dependency injection
* Uses Material design Library
* Write 30 unit tests using Mockk library, Espresso is used for Ui testing
* Using KTS: Refers to Kotlin script, a flavor of the Kotlin language used by Gradle in build
  configuration files

## Solution

The Repository layer handles data operations. The app's data comes from a few different sources -
data is stored (either remotely or in a local cache for offline use), and the repository modules are
responsible for handling all data operations and abstracting the data sources from the rest of the
app.

The app uses an offline first algorithm. If online data can't be requested, but offline data is
available, use the offline data.

A lightweight domain layer sits between the data layer and the presentation layer and handles
discrete pieces of business logic off the UI thread.

A Custom Pagination is used for paginating the comics and characters from the local cache and
providing a better user experience.

## Sum up

For the purpose of providing an overview of my solution, I have only included general information in
this document. My hope is to have the chance to describe my solution and the pros and cons of it
further.
