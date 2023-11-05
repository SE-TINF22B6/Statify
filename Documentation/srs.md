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

> [!IMPORTANT]  
> It is not necessary to cover all of the following categories, but focus on what your project will implement.  
> If some nonfunctional requirements are described as user stories in your backlog, add their **links** in this section, or any information to guide the reader find them in your backlog, such as a **label** of those relevant user stories.

> Categories: Usability, Reliability, Performance, Efficiency, Integrity, Maintainability, Flexibility, Testability, Reusability, Security.  


### 4. Technical constraints
> Specify any major constraints, assumptions or dependencies, e.g., any restrictions about which type of server to use, which type of open source license must be complied, etc. 
