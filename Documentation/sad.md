# Statify
## Software Architecture Documentation
### 1. Introduction
#### 1.1 Overview 
For our project, we use a layered architecture.   
**1. Layer:** Frontend, part of our application that will be visible to the user     
**2. Layer:** Backend, contains business logic, provides API for Frontend, connected to our database
**3. Layer:** Database, stores data

For the Frontend to access the stored data, it has to request the Backend, which can get the data from the Database. Therefore our layered architecture is closed, as a request by the Frontend has to pass all layers.

#### 1.2 Constraints
Due to the requirements of our stakholders, the following architectural decisions were made:
- Usage of a relational database -> implemented as 3. Layer
- Stateful application
- Graphical user interface -> implemented as 1. Layer
- 3rd party service API -> called by 2. Layer
- Implememntation as website
- Programming languages and frameworks: Java Spring Boot, React
- Time limit: 2 semesters
#### 1.3 Definitions, Acronyms and Abbreviations
> Definitions of all terms, acronyms, and abbreviations required to properly interpret this document.
#### 1.4 References
> A complete list of all documents referenced -- hyperlinks to those documents. 

[Component Diagram](diagrams/UML_Component_Diagram.svg)

### 2. Architecture tactics
> Reference your architecturally significant requirements from Semester 3.
> Revise your architecture tactics from Semester 3.

### 3. Architecture design
> This section specifies the architecture design in various views.
> Minimum requirement:
> - sequence diagram on a component level and necessary description
> - component diagrams and/or diagrams, and necessary description   

#### 3.1 Component Diagram
Our Component Diagram contains 3 components:  
**1.** Frontend   
**2.** Backend    
**3.** Database   
The Frontend is connected to the backend via the provided Spring Boot REST API defined in the SpotifyController.     
The Frontend displays a users Statistics via the UserInterface. For that purpose, it requests the Backend's Statistics component, which provides either already generated Statistics from the Database or generates new statistics from a user's streams which are stored in the Database.    
For accessing a user's streams, the Backend communicates with Spotify via the Spotify Web API.

![Component Diagram](diagrams/UML_Component_Diagram.svg)

#### 3.2 Runtime view
The Runtime View illustrates the dynamic behavior of the system as various components interact over time. This is  how the components collaborate to fulfill the application's use cases: 

##### User Login Sequence:
   - User: Initiates a login request via the Frontend.
   - Frontend: Sends login credentials to the Backend through the SpotifyController.
   - Backend: Validates credentials and requests user data from the Spotify Web API.
   - Spotify Web API: Verifies credentials and returns user authentication token and user data.
   - Backend: Stores user session information in the Database and responds to the Frontend with the session token.
   - Frontend: Stores the session token and transitions the user to the statistics view.

##### Statistics Retrieval Sequence
   - User: Requests to view their statistics.
   - Frontend: Sends a request to the Backend via the StatisticsController.
   - Backend: Checks if the statistics are already available in the Database.
     - If available, retrieves statistics from the Database.
     - If not, fetches user streams from the Spotify Web API, processes the data, and stores the generated statistics in the Database.
   - Backend: Sends the statistics data to the Frontend.
   - Frontend: Displays the statistics to the user.

#### 3.3 Deployment view 
This is how the various components are deployed across different servers and network boundaries:

##### Client Machine
   - Web Browser: Hosts the Frontend application built with React. The browser communicates with the Backend server via HTTPS.

##### Application Server
   - Backend (Spring Boot Application): Deployed on an application server. It handles REST API requests, processes business logic, and communicates with the Database and external services like the Spotify Web API.

##### Database Server
   - Relational Database (MySQL Server): Stores user data, session information, and generated statistics. The database server is accessed by the Backend for read and write operations.

##### External Services
   - Spotify Web API: An external service used by the Backend to fetch user stream data for generating statistics.


