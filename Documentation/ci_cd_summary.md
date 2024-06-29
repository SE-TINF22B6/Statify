# Statify

## CI/CD Summary

### Node.js CI Workflow

**Purpose:** 

To install Node.js dependencies, cache them, build the source code, and run tests.

**Triggered:** 

On pushes to the "main" and "develop" branches, and on pull requests to the "main" branch.

**Steps:**  

→ retrieves the repository code   
→ installs Node.js   
→ configures npm caching  
→ executes “npm ci” to cleanly install dependencies  
→ runs “npm run build” if a build script is present   
→ executes `npm test` to run the test suite   

### Java CI with Maven Workflow

**Purpose:** 

To build the Java project with Maven and ensure dependencies are up to date, as well as to generate a dependency graph.

**Triggered:** 

On pushes to the "main" and "develop" branches, and on pull requests to these branches.

**Steps:**

→  retrieves the repository code  
→  installs JDK and Maven   
→  configures the environment variables for Java  
→  executes “mvn package” to build the project   
→  uses the Depgraph Maven plugin to update the project's dependency graph  
