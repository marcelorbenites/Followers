# Followers

Simple application to load followers from Followers API. Infinite scroll is implemented based on an offset that can be configured via a Gradle property named **FOLLOWERS_LOAD_OFFSET**.

## Build

`./gradlew build`

## Unit Tests

`./gradlew check`

## Component Tests

`./gradlew connectedCheck`

## Architecture

The architecture is a **Clean Architecture** flavor. Presenters, Controllers and View Models were added when there was enough logic to be tested in isolation. The Presenter and the Controller in Clean Architecture are separated, differently from many implementations of MVP where the Presenter is also the Controller.

When creating a new application it is important to be able to grow an architecture avoiding off-the-shelf solutions that often result in overengineering. By adopting incremental design it is possible to have time to market at the same time that a cohesive architecture is created through continuous refactoring. The most important thing is to keep the **Domain** independent from **Data** and **Presentation** implementation details.

It would be easy to create an SDK from this application. Also by using Kotlin/Native the Domain, the Presenters, the View Models and the Controllers could be reused by an iOS application.

## State Management

The Domain layer is written as a **State Machine** which always caches the current state in memory. That strategy allows the Domain to be completely agnostic to the **lifecycle of the View**, what is specially useful in an Android application. By associating Domain classes to the **lifecycle of the Application** and by delegating **Multithreading** control to the Domain layer, use cases are not affected by Configuration Changes. 

All use cases are fired because of an action of the user in the Presentation layer. They could also be fired by Data layer events (e.g. push notification, Bluetooth event, etc...). Use cases are never fired based on the lifecycle of the View (e.g. **Activity onCreate or onResume**). 

The list of followers is loaded based on **Application onCreate** event, also the follower is selected when the user clicks in a cell in **FollowerListFragment**, not based on the lifecycle of **FollowerDetailFragment**. In many applications it is hard to follow this approach, because part of the state management that should be in the Domain was leaked in the Presentation.

One could open the application and constantly trigger configuration changes by rotating the device. The thread that loads the followers would not be affected, the UI would be consistent and once the operation was finished the result would be displayed.

## Dependencies

All dependencies in the application are abstracted, including **RxJava**, which is used exclusively for Multithreading. By constraining multithtreading to the Domain layer, Data layer is kept synchronous, what facilitates testing and the development of the Domain layer.

Besides **RxJava**, **Picasso** and **OKHttp** are also abstracted from the Domain layer. Picasso, OkHttp and RxJava could be easily exchanged for other libraries.

Many Android developers use **Dagger 2** for **Dependency Injection**, but since the update from **Dagger 1** the complexity and boilerplate code needed configure it increased dramatically. Dagger 1 relied strongly on reflection, which made it very simple to use. When Google created Dagger 2 it replaced reflection for code generation which made it very hard to configure. This project is an example of how simple it is to use Kotlin lazy operator together with some interfaces to workaround the fact that it is not possible to inject dependencies through Android Components' constructors. 

No external libraries are used for JSON serialization, instead simple **JSONObject** is used. There are plenty of options for JSON serialization like **Moshi**, **Jackson** and **Gson**, but all of them require the Domain classes to be annotated. By using library specific annotations in Domain classes we would leak external dependencies in the Domain. Another option would be to use a **Mapper** to map Data models to Domain models, but the effort in order to do that is higher than simply deserializing the JSON using JSONObject.

## Testing Strategy

The project was developed using **TDD** with two layers of testing: **Unit Tests** and **Component Tests**. Presentation, Domain and Data layers are tested in isolation through Unit Tests. Afterwards the integration between the layers is tested using Component Tests while **Test Doubles** are used for **External Services**, therefore Component Tests are hermetic. More information in a Medium [post](https://engineering.talkdesk.com/android-testing-strategy-73269539c13d) written by me.

## Future Development

- Create an iOS application and reuse the Domain, Presenters, View Models and Controllers using Kotlin/Native.
- Update FollowerListFragment to display loading as an item of the list, instead of displaying it in the bottom of the view.
- Update FollowerListFragment to display error as a Snackbar when error occurs while loading more followers, instead of hidding the items already loaded.
- Create End-to-End automated test to verify the application works as expected when integrated with **External Services**.





