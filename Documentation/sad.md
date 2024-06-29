# Statify
## Software Architecture Documentation
> This template is a simplified version based on the documentation templates from IBM Rational Unified Process (RUP) and arc42.org (https://docs.arc42.org/home/)
> If necessary, you can add more topics related to the architecture design of your application.

### 1. Introduction
#### 1.1 Overview 

> For our project, we use a layered architecture.   
> **1. Layer:** Frontend, part of our application that will be visible to the user     
> **2. Layer:** Backend, contains business logic, provides API for Frontend, connected to our database
> **3. Layer:** Database, stores data

> For the Frontend to access the stored data, it has to request the Backend, which can get the data from the Database. Therefore our layered architecture is closed, as a request by the Frontend has to pass all layers.

#### 1.2 Constraints
> Due to the requirements of our stakholders, the following architectural decisions were made:
> - Usage of a relational database -> implemented as 3. Layer
> - Stateful application
> - Graphical user interface -> implemented as 1. Layer
> - 3rd party service API -> called by 2. Layer
> - Implememntation as website
> - Programming languages and frameworks: Java Spring Boot, React
> - Time limit: 2 semesters
#### 1.3 Definitions, Acronyms and Abbreviations
> Definitions of all terms, acronyms, and abbreviations required to properly interpret this document.
#### 1.4 References
> A complete list of all documents referenced -- hyperlinks to those documents.

### 2. Architecture tactics
> Reference your architecturally significant requirements from Semester 3.
> Revise your architecture tactics from Semester 3.

### 3. Architecture design
> This section specifies the architecture design in various views.
> Minimum requirement:
> - sequence diagram on a component level and necessary description
> - component diagrams and/or diagrams, and necessary description   

#### 3.1 Component Diagram
> Our Component Diagram contains 3 components:  
> **1.** Frontend   
> **2.** Backend    
> **3.** Database   
> The Frontend is connected to the backend via the provided Spring Boot REST API defined in the SpotifyController.     
> The Frontend displays a users Statistics via the UserInterface. For that purpose, it requests the Backend's Statistics component, which provides either already generated Statistics from the Database or generates new statistics from a user's streams which are stored in the Database.    
> For accessing a user's streams, the Backend communicates with Spotify via the Spotify Web API.

![Component Diagram](diagrams/UML_Component_Diagram.svg)

#### 3.1 Overview 
> A summary of the architecture design -- highlights.  

#### 3.2 Runtime view (Tips: https://docs.arc42.org/section-6/)

#### 3.3 Deployment view (Tips: https://docs.arc42.org/section-7/)

#### 3.4 ... ...

