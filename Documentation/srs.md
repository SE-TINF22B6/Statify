# Statify
## Software Requirements Specification
> This template is a simplified version based on the documentation templates from IBM Rational Unified Process (RUP).
### 1. Introduction
#### 1.1 Overview
> Statify is a web application that utilizes the Spotify Web API to perform various Spotify-based analytics.
#### 1.2 Scope
> This SRS document covers the complete system of Statify. Its goal is to give a detailed explanation of the system's structure, functionalities, and constraints. However, it is possible that we may separate it in the future.
#### 1.3 Definitions, Acronyms and Abbreviations
> **SRS** - Software Requirements Specification.  
> **API** - Application Programming Interface.  
> **React** - a JavaScript library.  
> **Java** - a multi-platform, object-oriented, and network-centric language.  
> **Spring** - an application framework and inversion of control container for the Java platform.  
> **Spotify Web API** - an interface that programs can use to retrieve and manage Spotify data over the internet.  
> **TMetric** - a time tracking tool.  

#### 1.4 References
> Currently, our SRS does not include any external documents.

### 2. Functional requirements
>  This section contains all the software requirements to a level of detail sufficient to enable designers to design a system to satisfy those requirements and testers to test that the system satisfies those requirements.  
>  This section is typically organized by feature, but alternative organization methods may also be appropriate, for example, organization by user or organization by subsystem.

> [!NOTE]
> You can insert links to your UML diagrams and user stories, or labels of user stories into this document.

#### 2.1 Overview 
> The web application allows the connection to a user's Spotify account to access their listening history. It is used to provide statistics about their listening habits, such as most-listened-to genres, artists, and time of day, etc..  
> **Our UML Use Case:** 
> ![UML Use Cases](diagrams/UML_Use_Cases.svg)  

#### 2.2 Account Management 
> - **Relevant user stories:** [#11](https://github.com/SE-TINF22B6/Statify/issues/11), [#17](https://github.com/SE-TINF22B6/Statify/issues/17).
> - **UI mockups:** see the register, login and profile pages in [our UI mockup](https://www.figma.com/file/JuoCjSig1km8syVQRFGuFX/Statify?type=design&node-id=0%3A1&mode=design&t=BU8bocMhP7VTCSb1-1).
> - **UML behavior diagrams:** and necessary text specification 
> - **Preconditions:** the user has to have an active Spotify account.
> - **Postconditions:** the user can now have his or her statistics generated.
> - **Estimated efforts:** high.  

![Log in](diagrams/Login_Activity_Diagram.svg)  
![Edit_Profile](diagrams/Edit_Profile_Activity_Diagram.svg)  

#### 2.3 Generation and Depiction of Statistics
> - **Relevant user stories:** [#12](https://github.com/SE-TINF22B6/Statify/issues/12).
> - **UI mockups:** see the statistics and track pages in [our UI mockup](https://www.figma.com/file/JuoCjSig1km8syVQRFGuFX/Statify?type=design&node-id=0%3A1&mode=design&t=BU8bocMhP7VTCSb1-1).
> - **UML behavior diagrams:** and necessary text specification 
> - **Preconditions:** the user has to be regestered and logged in. 
> - **Postconditions:** the user can now generate and see detailed analytics about his or her listening behaviour, such as: top 5 most listened to songs and artists, playlists statistics including the amount of tracks of each represented genre and the top genre of the playlist. The user can also see the features of a track.  
> - **Estimated efforts:** high.  

![Generate](diagrams/Generate_Activity_Diagram.svg)

### 3. Nonfunctional requirements
#### 3.1 Quality Attribute Scenarios

| Scenario | Quality Attribute       | Who/What              | Event                     | Influence                | Condition                  | Action                                          | Measurement                                                                 |
|-----------------------------|-----------------------------|-----------------------|---------------------------|---------------------------|----------------------------|-------------------------------------------------|-----------------------------------------------------------------------------|
| 1 | Security (Confidentiality)   | User                  | wishes to connect to Spotify account | Data within the system | Normal operation          | successful connection                          | authentication success                                                      |
| 2 | Usability (Feedback)         | User                  | inputs data               | Data within the system | Normal operation          | gets response if data is valid                  | show error message when data is invalid                                     |
| 3 | Security (Availability)      | Application           | refreshes the access token | Data within the system | Runtime                   | the application is able to continue accessing Spotify data | every 60 mins                                                                           |
| 4 | Usability (Compliance)       | Designer/Developer    | designs the UI             | UI                      | Design time               | the UI fulfills Spotify design guidelines      | [Spotify Design Guidelines](https://developer.spotify.com/documentation/design) |
| 5 | Performance (Latency)        | User                  | creates an account         | System                   | Runtime                   | user can generate statistics    | within 5 mins                                                                           |
| 6 | Performance (Latency)        | Developer             | develops data processing   | Code                    | Development               | data processing should be efficient            | generated data is available in 1 min                                        |
| 7 | Modifiability (Loose coupling, high cohesion) | Developer | develops modules and components | Code               | Development               | reduced interdependence of the components     | changes can be made easily                                                  |
| 8 | Usability (Customizability)  | User                  | wishes to use the application on different devices | UI | Normal operation | the design is responsive                      | all functions are available on any device                                   |
| 9 | Availability (Spotify API issues) | Spotify API       | is down                    | System                   | Normal operation          | log error message                              | the application responds, but only the functions not related to the Spotify API can be used |
| 10 | Performance (Latency)        | Frontend              | sends an API request to the backend | System            | Normal operation          | receives response                     | in 2 sec                                                                           |

#### 3.2 Utility tree

| Quality attribute    | Refinement             | Quality attribute scenarios   | Business value | Technical risk  |
| :---                 | :----                  | :----                         | :----          | :----           | 
| Security    | Confidentiality         | Scenario 1  (see the table above)                |  H        | L         |
|  Performance | Latency | Scenario 6 (see the table above)                  |  H        | H         |
| Modifiability | loose coupling, high cohesion    | Scenario 7 (see the table above)                 |  M        | H         |

#### 3.3 Tactics for Top 3 quality attribute scenarios

##### 3.3.1 ...

##### 3.3.2 ...

##### 3.3.3 ...

### 4. Technical constraints
> Specify any major constraints, assumptions or dependencies, e.g., any restrictions about which type of server to use, which type of open source license must be complied, etc. 
