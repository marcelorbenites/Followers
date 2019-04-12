# Followers

Simple application to load followers from Followers API.

## Build

`./gradlew build`

## Unit Tests

`./gradlew check`

## Component Tests

`./gradlew connectedCheck`

## Architecture

The architecture is a Clean Architecture flavor with MVC as default for the Presentation layer. Each view can use MVP or MVVM dependending on its needs. Currently none of the views require MVP or MVVM and a simple MVC is enough.

## State Management

The Domain layer is written as a State Machine always caching the current state in memory. That strategy allows the Domain to be completely agnostic to View lifecycle what is specially useful in an Android application. By associating Domain classes to the Application lifecycle and by delegating multithreading control to the Domain layer, use cases are not affected by Configuration Changes. 

All use cases are fired because of an action of the user in the UI. They could also be fired by Data layer events (e.g. push notification, Bluetooth event, etc...). Use cases are never fired based on UI lifecycle (e.g. Activity onCreate or onResume). 

The list of followers load is started based on Application onCreate method, also the follower selection in the Domain is done based in the user's click in the FollowerListFragment not based on FollowerDetailFragment lifecycle events.

## Dependencies

All dependencies in the application are abstracted, besides RxJava. RxJava is used exclusively for Threading, because of its excellent Unit Testing support. Also by constraining the usage of RxJava to the Domain layer we avoid letting it leak throughout the application, the Data layer is kept synchronous, what facilitates testing and the development of the Domain layer.

Besides RxJava, Picasso and OKHttp are also dependencies, but they are abstracted from the Domain layer. Picasso and OkHttp could be easily exchanged by other libraries.

Many Android developers use Dagger 2 for Dependency Injection, but since the update from Dagger 1 the complexity and boiler plate code needed for Dagger 2 is just not worth it. Dagger 1 relied strongly on reflection which made it very simple to use, when Google created Dagger 2 it replaced relfection for code generation which made it very hard to configure the library. This project is an example of how simple it is to use Kotlin lazy operator and some interfaces to workaround the fact that it is not possible to inject dependencies through Android Components' constructors. 

No external libraries are used for JSON serialization, instead simple JSONObject is used. There are plenty of options for JSON serialization like Moshi, Jackson and Gson, but all of them require the Domain classes to be annotated. By using library specific annotations in Domain classes we would leak external dependencies in the Domain. Another option would be to use a Mapper to map Data models to Domain models, but the effort in order to do that is higher than simply deserializing the JSON using  JSONObject.

## Testing Strategy

The project was developed using TDD with two layers of testing: Unit Tests and Component Tests. Presentation, Domain and Data layers are tested in isolation through Unit Tests. Afterwards the integration between the layers is tested using Component Tests by mocking External Services, therefore Component Tests are hermetic. More information in a Medium [post](https://engineering.talkdesk.com/android-testing-strategy-73269539c13d) written by me.



