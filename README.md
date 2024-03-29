# The Problem

Our providers in Dallas, TX need the ability to see nearby shifts available on the ShiftKey platform to plan their
 work week. Your goal is to build an user interface that allows them to accomplish this.
 
## Details
Use the `MainActivity` to get started. Using an address of "Dallas, TX", display one week of nearby shifts that come
 back from the [Available Shifts API](https://bitbucket.org/shiftkeyllc/ios-coding-challenge/src/master/API-DOC.md) in a list. The shift cell should include information relevant to the provider
  and when tapped, it should present a different screen to display more information about the shift.

The coding challenge should take around 2-4 hours. The submitted code should reflect what you feel represents your best work.

You should be prepared to talk about your solution in an interview setting.

To start working on the challenge, clone this repo onto your development machine. Commit your changes there and when finished, publish your repo on your public bitbucket or github account.

### Requirements ###

* Written in Kotlin
* Fetches available shifts using the [Available Shifts API](https://bitbucket.org/shiftkeyllc/ios-coding-challenge/src/master/API-DOC.md) with an address of "Dallas, TX"
* On app launch, the current week of shifts should be fetched and displayed, starting with today's date
* Scrolling to the end of the current week on the list loads shifts for the next week
* Tapping on a shift cell should present a shift details screen with shift details
* Use of 3rd party libraries is allowed. But, be thoughtful about which libraries are used and why

### What are we looking for? ###

* Solid architecture
* Use of design patterns
* Follow Material design principles
* Experience with android architecture components
* Familiarity with libraries/frameworks
* Testability

### Marjor design pattern and Android components used 
Show case of 
* MVVM architecture,
* ViewModel coroutine scope
* RecycleView and it's adapter
* Pull to Refresh layout for the detailed view
* Jetpack Navigation for future scalability, 
* Databinding to simplify coding in UI(activity) by moving the binding to layout file, 
* LiveData observed in UI to avoid ANR and comply with single responsibility design principle
