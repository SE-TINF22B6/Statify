# Code Refactoring Summary

## General Principles We Adhere To

In our project, we have several agreements we strictly follow to guarantee the understandability, readability, changeability, extensibility, and maintainability of our source code.

### Meaningful Names

We use meaningful names for our variables, functions, classes, etc., as we believe this enhances the readability and maintainability of the source code. When variables, functions, and classes have descriptive names, it becomes easier for others (and your future self) to understand the purpose and usage of each element without needing extensive comments or documentation. This approach reduces the cognitive load on us as developers, allowing us to quickly grasp the functionality of the code and make necessary modifications with confidence.

### Comments

We add comments, but only in sections where the code is not self-explanatory and obvious. Comments provide context that might not be immediately clear from the code alone. They also help us by offering insights into other developers' thought processes.

### Folder Structure

In our project, we have a well-organized folder structure. We believe this is important for managing large codebases efficiently. It helps to logically group related files and makes it easy to navigate and locate specific components. This structure allows for modular development, where different parts of the project can be developed, tested, and maintained independently.

### Several Smaller Functions Instead of One Large Function

We tend to break down large functions into several smaller ones. Smaller functions are easier to understand, test, and debug, as each one focuses on a single task or a specific aspect of the logic. This modular approach promotes **the DRY (Don't Repeat Yourself) principle**.

### Usage of Named Constants

We prefer to use named constants instead of hard-coded values, as named constants convey the meaning and purpose of specific values, reducing the likelihood of errors caused by unclear numbers. They also facilitate easy updates, as changes to the constant's value need to be made in only one place.

## Enhancements in the Backend

In our backend, there are several code parts that are not used completely or will be used partially later. Our current task is to get rid of the unused parts. At the moment, we have to consult our other group members to ensure that this will not cause any problems later. So this section will be edited soon.

We also adjusted our entities, as there have been many changes to the overall data structure we use. For the classes TopTracks, TopArtists, PlaylistStatistics we created a new base class, Statistics, from which they inherit. By using a common base class, reusable functionalities can be centrally defined and maintained, making the code more consistent and easier to extend. It adheres to **the Open/Closed Principle**, as the code is open for extension but closed for modification.
