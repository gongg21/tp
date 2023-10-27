---
  layout: page
  title: Developer Guide
  pageNav: 3
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [`diagrams`](https://github.com/AY2324S1-CS2103T-W15-4/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.
Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/MainApp.java)
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of five components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.
* [**`StageManager`**](#stageManager-component): Keeps track of the App's current stage.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

StageManager (shown in the diagram above) is accessible by the Logic component for checking and updating the app's current stage.

Each of the other four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface.
Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component),
as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)
![Interactions Inside the Ui Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `CourseListPanel`, `CombinedPanel`, `StatusBarFooter` etc.
All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `MainWindow` includes a `DisplayPanel`, which has three different states it can toggle between
1) The `SplashPanel` for the opening splash window
2) The `CombinedPanel` that displays the student list and a course list sidebar
3) The `CoursePanel` that displays the course list

The `UI` component uses the JavaFx UI framework.
The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder.
For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,
* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the `UI` can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute the commands.
* keeps a reference to the `StageManager` to update the `UI` with its corresponding display panel
* depends on some classes in the `Model` component, as it displays `Course` and `Student` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `CodeSphereParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add an item).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCourseCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `CodeSphereParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `CodeSphereParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.
* All `XYZCommandParser` classes throw a ParseException if there are any errors with the arguments.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The `Model` component,

* stores the app data i.e., all `Course` objects (which are contained in a `UniqueCourseList` object).
* stores the currently 'selected' `Course` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Course>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components).

### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both CourseList data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `CourseListStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.codesphere.commons` package.

--------------------------------------------------------------------------------------------------------------------
## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add a student
After selecting a `Course` from the `UniqueCourseList`,  `Student` objects can be added into the `UniqueStudentList` of the `Course`.  Compulsory fields for the `AddCommand` include `Name`, `Email` and a performance `Tag`. The optional `Remark` and `PendingQuestion` fields cannot be added using the `AddCommand`.
Given below is an example usage scenario and how the adding mechanism works. We will skip to where the `AddCommand#execute()` method is called.

* Step 1. The `AddCommand` object’s `execute()` method is called.
* Step 2. `StageManager` is used to obtain the current `Course` selected.
* Step 3. A check for duplicates in the `UniqueStudentList` of the current `Course` is done. If the new `Student` to be added already exists, a `CommandException` is thrown.
* Step 4. The new `Student` is added into the `UniqueStudentList` of the current `Course`.


### Edit a student
`Student` objects are stored in their respective Course’s `UniqueStudentList`. The details (name, email, remark, pending question, tag) of a student in a course can be edited by changing the fields of the `Student` object.
Given below is an example usage scenario and how the editing mechanism is carried out on a `Student` in a course. We will skip to where the `EditCommand#execute()` method is called.

* Step 1. The `EditCommand` object’s `execute()` method is called.
* Step 2. The index provided is checked to be within bounds of the course’s student list. If it is not, a `CommandException` is thrown.
* Step 3. A new `Student` object, `editedStudent` is created with the edited inputs.
* Step 4. A check for duplicates in the current course is done. If there is a duplicate, a `CommandException` is thrown.
* Step 5. The original student in the current course’s student list is replaced with `editedStudent`.

### Edit a course
A course’s course name can be edited by changing the `CourseName` field of a `Course` object.
Given below is an example usage scenario and how the editing mechanism is carried out. As per the examples above, we will skip to where the `EditCourseCommand#execute()` method is called.

* Step 1. The `EditCourseCommand` object’s `execute()` method is called.
* Step 2. The index provided is checked to be within bounds of the course’s student list. If it is not, a `CommandException` is thrown.
* Step 3. A new `Course` object, `editedCourse` is created with the edited course name.
* Step 4. A check for duplicates in the model is done. If there is a duplicate, a `CommandException` is thrown.
* Step 5. The original course is replaced with `editedCourse`.

### Add a Pending Question to a Student
After selecting a list of `Students` of the current `Course`, the targeted `Student` object is identified from the list of `Students`.
A new `Student` object is created , and it will replace the old `Student` object in the list.
Given below is an example usage scenario and how the adding pending question mechanism works. We will skip to where the `PendingQuestionCommand#execute()` method is called.

* Step 1. The `execute()` method of the `PendingQuestionCommand` object is invoked.
* Step 2. `StageManager` is used to retrieve the current `Course` by calling `StageManager#getCurrentCourse()`.
* Step 3. The list of `Student` belong to the course object will be obtained by calling `Course#getFilteredStudentList()` and identify the targeted `Student` from the list.
* Step 4. A check for index from user input will be done. If the index is invalid,  a `CommandException` will be thrown.
* Step 5. A new `Student` object is created by passing in the `PendingQuestion` instance to the `Student` constructor.
* Step 6. Update the newly created `Student` instance to replace the old `Student` instance.

--------------------------------------------------------------------------------------------------------------------
## **Documentation, logging, testing, configurations, dev-ops**
* [Documentation guide](Documentation.md)
* [Logging guide](Logging.md)
* [Testing guide](Testing.md)
* [Configurations guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------
## **Appendix: Requirements**

### Product scope

**Target user profile**:

* wants to be able to manage student information
* wants to be able to keep track of student performance
* prefer desktop apps over other types
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: provides an easy way to manage students, allowing for customised support to keep tabs on each student.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                 | I want to …​                                   | So that I can…​                                                 |
| ------ |-------------------------|------------------------------------------------|-----------------------------------------------------------------|
| `* * *` | user                    | add student profiles                           | keep track of the students in my class.                         |
| `* * *` | user                    | edit student profiles                          | keep accurate and up-to-date information on each student.       |
| `* * *` | user                    | delete a student profile                       | remove a student if the student is no longer in my class.       |
| `* * *` | user                    | tag students based on how well they are coping | I can easily identify students who may need additional support. |
| `* *`  | user                    | add remarks for individual students            | maintain a log of anything noteworthy.                          |
| `* *`  | user                    | add pending question for individual students   | efficiently manage and respond to their queries.                |
| `* *`  | user                    | see a filtered view of students for each class | efficiently manage my classes and students.                     |
| `* *`  | user                    | sort students by their tag level               | see which students need more of my help                         |
| `*`    | user with many students | sort students by name                          | locate a student easily.                                        |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `CodeSphere` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Edit student’s information**

**Preconditions**: The student has already been added into the app.

**Guarantees**: Unchanged fields during the edit will remain the same as before.

**MSS**

1. User chooses to edit a student’s information.
2. CodeSphere displays the student’s current information.
3. User edits the fields that need to be changed.
4. User saves the changes.
5. CodeSphere updates the student’s information.
    Use case ends.

**Extensions**

* 3a. CodeSphere detects incorrect format for the new changes.
    * 3a1. CodeSphere prompts the User to enter details in the correct format.
    * 3a2. User enters details again.</br>
    Use case resumes at step 4.

*  *a. At any time, the User chooses to cancel the edit.
    * *a1. CodeSphere requests to confirm the cancellation.
    * *a2. User confirms the cancellation.
    Use case ends.

**Use case: Delete a student**

**Preconditions**: The student has already been added into the app.

**MSS**

1. User chooses to delete a student.
2. CodeSphere displays the student’s current information.
3. CodeSphere prompts the user to confirm the deletion.
4. User confirms the deletion.
5. CodeSphere deletes the student.
   Use Case Ends.

**Extensions**

* 1a. CodeSphere detects that the target student does not exist.
    * 1a1. CodeSphere prompts the user to choose an existing student to be deleted.
    * 1a2. User chooses an existing student to be deleted.</br>
    Use case resumes at step 4

**Use case: Add a pending question for a student**

**Preconditions**: User has an existing profile.

**MSS**

1. User chooses to delete a student.
2. CodeSphere displays the student’s current information.
3. CodeSphere prompts the user to confirm the addition.
4. User confirms the addition.
5. CodeSphere adds a pending question tag to the student.
   Use Case Ends.

**Extensions**

* 1a. CodeSphere detects that the target student does not exist.
    * 1a1. CodeSphere prompts the user to choose an existing student to be tagged with a pending question.
    * 1a2. User chooses an existing student to be tagged.</br>
    Use case resumes at step 4

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  The application should be able to handle an increasing number of profiles and users without a significant degradation in performance.
3.  The application should gracefully handle errors, providing informative error messages to users in case of failures.
4.  Code should be organized into reusable and maintainable modules, making it easier to enhance and extend the application in the future.
5.  Maintain comprehensive documentation for developers.
6.  The application should run smoothly on different operating systems commonly used by the target users.
7.  Ensure that only authorized users can access and manipulate profile information.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: Command Line Interface. A way to interact with a computer or software by typing text-based commands, rather than using a mouse and graphical icons.

--------------------------------------------------------------------------------------------------------------------
## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.


### Deleting a Course

1. Deleting a course while all courses are being shown

    1. Test case: `delete 1`<br>
       Expected: First course is deleted from the list. Details of the deleted course shown in the status message.

    2. Test case: `delete 0`<br>
        Expected: No course is deleted. Error details shown in the status message.

    3. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.
