---
layout: page
title: User Guide
---

CodeSphere is a **desktop contact management app, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI).
It is an app targeted at helping Teaching Assistants (TAs) of the NUS School of Computing manage the administration of their students better.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## Table of Contents
1. [**User Guide Tips**](#user-guide-tips)
2. [**Quick Start**](#quick-start)
3. [**Tutorial for Beginners**](#tutorial-for-beginners)
4. [**Features**](#features)
    * [**Accessing Input History**](#accessing-input-history)
    * [**Universal Commands**](#universal-commands)
        * [`help` Viewing help](#viewing-help--help)
        * [`exit` Exiting the program](#exiting-the-program--exit)
    * [**Home Page Commands**](#home-page-commands)
        * [`add` Adding a new course](#adding-a-course--add)
        * [`edit` Editing the details of a course](#editing-a-course--edit)
        * [`delete` Deleting a course](#deleting-a-course--delete)
        * [`clear` Clearing all courses](#clearing-all-courses-clear)
        * [`select` Selecting a course](#selecting-a-course-select)
        * [`find` Finding a course](#finding-a-student-find)
        * [`reset` Resetting the course list](#resetting-the-course-list--reset)
    * [**Course Page Commands**](#course-page-commands)
        * [`add` Adding a student](#adding-a-student--add)
        * [`edit` Editing the details of a student](#editing-a-student--edit)
        * [`delete` Deleting a student](#deleting-a-student-delete)
        * [`clear` Clearing all students](#clearing-all-students-clear)
        * [`reset` Resetting student list to its original state](#resetting-student-list-reset)
        * [`sort` Sorting all students](#sorting-all-students-sort)
        * [`find` Finding a student and keywords for each student](#finding-a-student-find)
        * [`list` Finding students with pending questions](#list-all-pending-questions-list)
        * [`remark` Adding a remark for a student](#adding-a-remark-for-a-student-remark)
        * [`pq` Adding a pending question for a student](#adding-a-pending-question-for-a-student-pq)
        * [`remove` Removing a remark/pending question](#removing-a-remarkpending-question-of-a-student-remove)
        * [`home` Returning to home page](#returning-to-the-home-page-home)
    * [**Miscellaneous**](#miscellaneous)
        * [Saving the data](#saving-the-data)
        * [Editing the data file](#editing-the-data-file)
5. [**FAQ**](#faq)
6. [**Known Issues**](#known-issues)
7. [**Command summary**](#command-summary)
    * [**Home Page**](#home-page)
    * [**Course Page**](#course-page)
8. [**Glossary**](#glossary)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **User Guide Tips**

### Finding what you need

1. [Quick start](#quick-start) will help you set up CodeSphere.
2. [Tutorial for Beginners](#tutorial-for-beginners) walks you through our graphical interface and also guides you on managing a course on CodeSphere.
3. [Features](#features) will help you understand how our features can be useful for your management.
4. [FAQ](#faq) answers the most common questions from our users. If you have questions for us, this section might just prove extremely useful!
5. [Command Summary](#command-summary) provides a summarised list of our features for your easy reference.
6. [Glossary](#glossary) explains some of the more complicated terms we used in the guide. If you do not understand some terms, this section might help!
7. Return to the table of contents by using [_Back to Top_](#table-of-contents) at the bottom of each page.

### Understanding symbols and syntax

| Symbol/Syntax        | Meaning                                                |
|----------------------|--------------------------------------------------------|
| `command`            | A command or keyword present,                          |
| :exclamation:        | Warning or some important information for you to know. |
| :bulb:               | Tips from us!                                          |
| :information_source: | Information that you should take note of.              |

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **Quick Start**

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `codesphere.jar` from [here](https://github.com/AY2324S1-CS2103T-W15-4/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for the CodeSphere app.
4. Double-click the file to run the app. A GUI similar to the below should appear in a few seconds. This is the home page. Note how the app contains some sample data.

   ![Ui](images/HomePage.png)

In the command box, type in any command and hit enter to execute the command!
Eg. typing `help` and hitting enter after will show the Help window. Some other example commands you can try:
* `help` : View help.
* `select 1` :  Selects the first course and navigates to the list of students in this course.
* `add c/CS1101S` : Adds a course named `CS1101S` to the course list.
* `edit 1 c/CS1231S` : Updates the first course in the course list with the edited course name.
* `delete 3` :
    * If you are currently on the home page, it deletes the third course shown in the current list.
    * If you are currently on the course page, it deletes the third student shown in the current list.

* `exit` : Exits the app.

For a more detailed walk-through on how to use CodeSphere to start managing your students, head to [Tutorial for Beginners](#tutorial-for-beginners).
Else, if you prefer exploring the app yourself, head to [Features](#features) below for the list of commands.

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **Tutorial for Beginners**
*If you are not a beginner, or would like to explore CodeSphere on your own,
feel free to skip this tutorial or jump to whichever portion you require.*

### Tutorial Contents
1. [**Adding a course**](#tutorial-adding-a-course)
2. [**Adding students**](#tutorial-adding-students)
3. [**Adding remarks / pending questions**](#tutorial-adding-remarks--pending-questions)

**Let's explore CodeSphere's features through the eyes of a typical TA.**
As you enter into the application for the first time, after a brief splash screen of the CodeSphere logo,
you will see an empty page. This is our `home` page, used to display a list of all the courses that you wish to manage.

![Empty Homepage](images/tutorial/homepage_empty.png)

At the top of the application, there is an underlined portion that we will refer to as the *Command Line*.
This is where your commands are typed, and they are confirmed by pressing the ***ENTER*** key on your keyboard.
Below the Command Line is the *Result Box* (which is the outlined box),
which will tell you the outcome of any commands that you have entered.
Lastly, underneath that is the *Display Panel*, which will be updated after each command is entered.


### Tutorial: Adding a course

*(For demonstration purposes, we'll be using `CS1101S` as our chosen course.
Feel free to replace this with any course of your choosing.)*

Let's begin by adding our course into our home page with the command `add c/CS1101S`.
You should see the course immediately appear in the list,
and a result informing you that there is a *New course added: CS1101S*

![CS1101S in Homepage](images/tutorial/homepage_CS1101S.png)

### Tutorial: Adding students

Let us add some students into this course.
First, we will have to navigate into our CS1101S course by using the command
`select 1`, which selects the first item in the course list.
The course's item number is displayed beside the course name (note the **1.** beside *CS1101S* in the picture)

Your application should update to look like something like this.
The course entry (CS1101S) displays the summary statistics of all the students listed within the course (currently none).
*Tip: You can always refer to the Result Box to see whether the command you entered has been successful*

![Empty CS1101S](images/tutorial/course_CS1101S.png)

The course is currently empty, so let's add a few students into the list with the following commands:
*(Remember to press 'Enter' after each command)*
`add n/John Tan e/e0123456@u.nus.edu t/good`
`add n/Mary Lim e/e1234567@u.nus.edu t/average`
`add n/David Lee e/e2345678@u.nus.edu t/poor`

>:information_source: Note: When creating students, you must always include their name (`n/`), email (`e/`) and performance tag (`t/`).

![Filled CS1101S](images/tutorial/course_students.png)

### Tutorial: Adding remarks / pending questions

Lastly, we shall look into adding remarks or pending questions for the students.
Let's add a remark "Will be 15 minutes late for tutorial" to our student *John Tan* (who is number 1 in the list)
by typing
`remark 1 r/Will be 15 minutes late for tutorial`.

Likewise, let's note down Mary's pending question about "Tutorial 2 Question 4" through the command
`pq 2 pq/Tutorial 2 Question 4`.

Your page should now be updated with the remark and pending question under the respective student's data entry.

![Add Remark and PQ](images/tutorial/remark_pq.png)

And there you have it! Your first course created, with students' details and even extra information all noted down,
displayed to you at a glance!

>:bulb: As a final tip, you can use the directional ***UP*** and ***DOWN*** keys on your keyboard to access the commands that
you have previously entered.

Feel free to explore CodeSphere's other features, such as
[listing out all students with pending questions](#list-all-pending-questions-list),
[sorting the students by different criteria](#sorting-all-students-sort) or even
[finding students with certain keywords in selected field](#finding-a-student-find).

There's many features specially curated for you to manage your students with ease,
so have fun and may your administrative work be effortless!

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **Features**

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
    * e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
    * e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/GOOD` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
    * e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/GOOD`, `t/GOOD t/AVERAGE` etc.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
    * e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

[_Back to Top_](#table-of-contents)

<div style="page-break-after: always"></div>

---
### **Accessing Input History**

CodeSphere saves all inputs that you have previously entered.
In the CLI command box, just use your 'up' and 'down' arrow keys to access any inputs that you have entered before.

> :information_source: *For your information*: Inputs that were invalid will be shown in red.

---

### **Universal Commands**
Commands in CodeSphere are mostly only used exclusively on the [home page](#home-page-commands) or the [course page](#course-page-commands).
However, commands in this section can be used on either page at any time.

### Viewing help : `help`

Shows a message explaining how to access the help page.

**Format:** `help`

### Exiting the program : `exit`

Exits the program.

**Format:** `exit`

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

### **Home Page Commands**
CodeSphere has a home page that displays the list of courses you are currently overseeing and have stored in the app.

*{screenshot to be added}*

Commands exclusive to the home page can help you:
* `add` new courses
* `edit` the details of existing courses
* `delete` existing courses
* `clear` all existing courses
* `find` finding a course
* `reset` resetting the course list to original state


The `select` command brings you to the Course page of the selected course.

[_Back to Top_](#table-of-contents)

<div style="page-break-after: always"></div>

### Adding a course : `add`
Adds a course to the list of courses.

**Format:** `add c/COURSENAME`
* `COURSENAME` is a string that is a valid course in the NUS School of Computing.

**Examples:**
* `add c/CS1101S`
* `add c/CS1231S`

**Command succeeds:** Success message shown, course successfully added and stored in database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the course is not added/stored in the database.

### Editing a course : `edit`
Edits the details of an existing course from the list of courses.

**Format:** `edit INDEX c/NEW_COURSENAME`
* Edits the course at the specified `INDEX`. Existing course name will be updated to the input course name.
* The index refers to the index number shown in the displayed course list.
* `NEW_COURSENAME` is a string that is a valid course in the NUS School of Computing.
* `INDEX` must be a positive integer 1, 2, 3, ...

**Examples:** `edit 1 c/CS1101S` Edits the course of the first course in the course list to be CS1101S.

**Command succeeds:** Success message shown, course successfully edited and updated in database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the course is not edited in the database.

### Deleting a course : `delete`
Deletes the specified course from the list of courses.

**Format:** `delete INDEX`
* Deletes the course at the specified `INDEX`.
* The index refers to the index number shown in the displayed course list.
* `INDEX` must be a positive integer 1, 2, 3, ...

**Command succeeds:** Success message shown, course successfully deleted and removed from database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the course is not removed from the database.

**Examples:** `delete 2` Deletes the course at index 2 of the displayed course list.

### Clearing all courses: `clear`
Clears all courses in the displayed list of courses.

Format: `clear`

### Selecting a course: `select`
Selects the specified course from the list of courses.

**Format:** `select INDEX`
* Selects the course at the specified `INDEX`.
* The index refers to the index number shown in the displayed course list.
* `INDEX` must be a positive integer 1, 2, 3, ...

**Example:** `select 2` Selects the course at index 2 of the displayed course list.

**Command succeeds:** Success message shown, course successfully selected,resulting in a change in GUI.

**Command failure:** Incorrect format/index results in an error message shown and the course is not selected.

### Finding a course: `find`
Find a course **given a keyword** from the list of courses you are teaching.

**Format:** `find KEYWORD`
* The search is case-insensitive. e.g `cs` will match any course name with `CS` containted.
* As long as the keyword is containted the course name, the course will be filtered out and displayed.

**Example:** `find CS` Displays all courses with CS inside their name.

**Command succeeds:** Success message shown, courses successfully filtered, resulting in a change in GUI.

**Command failure:** Incorrect format results in an error message shown and the course is not filtered.

### Resetting the course list: `reset`
Shows a list of all courses in the original state after filtering.

**Format:** `reset`

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

### **Course Page Commands**
Once you have created and selected a course to manage, you can now start adding your students in the course that you wish to track!
The Course page of a course displays the list of students that you are overseeing in your course (added by you).
Students are arranged in order of addition.

*{screenshot to be added}*

Commands exclusive to the course page can help you:
* `add` new students
* `edit` the details of existing students
* `delete` existing students
* `clear` all existing students
* `reset` the student list back to its original state
* `sort` students by certain fields
* `find` students in the course
* `list` all existing students with pending questions
* add a `remark` to a student
* add a pending question `pq` to a student
* `remove` a remark or pending question from a student

[_Back to Top_](#table-of-contents)

<div style="page-break-after: always"></div>

### Adding a student : `add`
Adds a student to the list of students in the selected course that the user is overseeing.

**Format:** `add n/NAME e/EMAIL t/ENUM_TAG`
* `NAME`: Student names are case-sensitive and should only contain alphabets and some specified special characters (, / - ‘).
* `EMAIL`: NUS undergraduate student’s email, in the format of exxxxxxx@u.nus.edu. Alphabets in the email are case-insensitive.
* `ENUM_TAG`: a pre-defined enumerated tag definitions GOOD, AVERAGE, POOR. Inputs for tags are case-insensitive.

**Examples:**
* `add n/Susan Tan e/e0946283@u.nus.edu t/GOOD`
* `add n/Koh Mei Ling, Mary e/e9739423@u.nus.edu t/AVERAGE`
* `add n/Ashley-Jane Lim e/E9739153@U.NUS.EDU t/poor`

**Command succeeds:** Success message shown, student successfully added and stored in database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the student is not added/stored in the database.

### Editing a student : `edit`

Edits an existing student that the user is currently overseeing.

**Format:** `edit INDEX [n/NAME] [e/EMAIL] [r/REMARK] [pq/PENDING_QUESTION]`
* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed students list.
* The index must be a positive integer (1, 2, 3, …​), and it should fall within the range of students currently displayed such that it corresponds to a valid student.
* Optional fields are indicated by square brackets [ ], but at least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags/remarks/pending questions, the respective existing fields of the person will be removed i.e adding of tags/remarks/pending questions is not cumulative.

**Examples:**
* `edit 1 t/good` Edits the tag of the first student in the displayed student list to be GOOD.
* `edit 2 n/Alex Yeoh t/average` Edits the name of the second student to be Alex Yeoh and changes the tag to AVERAGE.
* `edit 3 r/participative` Edits the remark of the third student to be "participative".
* `edit 4 pq/what is a logic gate?` Edits the pending question field of the fourth student to be "what is a logic gate?".

**Command succeeds:** Success message shown, student successfully edited and updated in database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the student is not updated in the database.

### Deleting a student: `delete`

Deletes the specified student from the list of students you are overseeing in the selected course.

**Format:** `delete INDEX`
* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* The index must be a positive integer (1, 2, 3, …​), and it should fall within the range of students currently displayed such that it corresponds to a valid student.

**Examples:** `delete 2` Deletes the student at index 2 of the displayed students list.

**Command succeeds:** Success message shown, student successfully removed from database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the student is not removed from the database.

### Clearing all students: `clear`
Clears all students in the displayed list of students in the selected course.

**Format:** `clear`

### Resetting student list: `reset`
Shows a list of all students in the original state after filtering or sorting.

**Format:** `reset`

### Sorting all students: `sort`
Sorts the list of students you are overseeing in the selected course **by name or by tag**.

**Format**: `sort`

### Finding a student: `find`
Find a student **by a certain field** from the list of students you are overseeing in the selected course.

**Format:** `find [n/KEYWORD] [t/TAG] [pq/QUESTION] [r/REMARK]`
* The search is case-insensitive. e.g hans will match Hans.
* Only ONE prefix can be specified each time you use the command.
* The search is dependent on the prefix.
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans.
* Only full words will be matched e.g. Han will not match Hans.

**Example:**
* `find n/John` returns `john` and `John Doe`.
* `find t/good` Find the student(s) tagged as `GOOD`, and the details of the student(s) will be displayed.

### List all pending questions: `list`
List all the students with non-empty pending question fields.

**Format:** `list pq/`

**Command succeeds:** Success message shown to user, no change in the database, a change in GUI is displayed.

**Command failure:** Users enter the command with incorrect formatting, resulting in an error message shown to the user.

### Adding a remark for a student: `remark`
Adds a remark to the specified student from the list of students.

**Format:** `remark INDEX r/REMARK`
* Adds a remark for the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* `INDEX` must be a **positive integer** 1, 2, 3, …

**Example:** `remark 2 r/needs more help` Adds a remark to the student at index 2 of the displayed students list saying needs more help.

**Command succeeds:** Success message shown, remark successfully added to student and updated in database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the remark is not added to the student.

### Adding a pending question for a student: `pq`
Adds a pending question to a specified student from the list of students of a course.
**Format:** `pq INDEX pq/PENDING_QUESTION`
* Adds a pending question for the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* `INDEX` must be a **positive integer** 1, 2, 3, …

**Example:**
* `pq 2 pq/What is a logic gate?` Adds a pending question to the student at index 2 of the displayed student ,with the question “What is a logic gate?”. This indicates a need to follow-up with the student.

**Command succeeds:** Success message shown, pending question successfully added and updated in database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and pending question is not added to the student.

### Removing a remark/pending question of a student: `remove`
Removes a remark or pending question of a specified student from the list of students.

**Format:** `remove INDEX [r/] [pq/]`
* Removes a pending question of the student at the specified `INDEX`.
* At least one of the optional fields must be provided.
* The index refers to the index number shown in the displayed students list.
* `INDEX` must be a **positive integer** 1, 2, 3, …

**Example:**
* `remove 2 pq/` Removes a pending question of the student at index 2 of the displayed students list.
* `remove 2 r/` Removes a remark of the student at index 2 of the displayed students list.

**Command succeeds:** Success message shown, remark/pending question successfully removed from student, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the remark/pending question is not removed from the student.

### Returning to the home page: `home`
Returns to the [home page](#home-page-commands) showing the list of courses you are overseeing.

**Format:** `home`

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

### **Miscellaneous**

### Saving the data

CodeSphere data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CodeSphere data is saved automatically as a JSON file `[JAR file location]/data/codesphere.json`. Advanced users are welcome to update data directly by editing that data file.

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **FAQ**

Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CodeSphere home folder.

--------------------------------------------------------------------------------------------------------------------
## **Known Issues**

1. When using multiple screens, if you move the application to a secondary screen, and later switch to using only the primary screen,
   the GUI will open off-screen. The remedy is to delete the preferences.json file created by the application before running the application again.

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **Command Summary**

### Home Page

| Action                | Format, Examples                                           |
|-----------------------|------------------------------------------------------------|
| **Help**              | `help`                                                     |
| **Add Course**        | `add c/COURSENAME`<br/> e.g. `add c/CS1101S`               |
| **Edit Course**       | `edit INDEX c/NEW_COURSENAME`<br/> e.g. `edit 1 c/CS1231S` |
| **Delete Course**     | `delete INDEX` e.g. `delete 1`                             |
| **Clear All Courses** | `clear`                                                    |
| **Select Course**     | `select INDEX` e.g. `select 1`                             |
| **Find Course**       | `find KEYWORD` e.g. `find CS`                              |
| **Exit**              | `exit`                                                     |

[_Back to Top_](#table-of-contents)

<div style="page-break-after: always"></div>

### Course Page

| Action                                 | Format, Examples                                                                       |
|----------------------------------------|----------------------------------------------------------------------------------------|
| **Help**                               | `help`                                                                                 |
| **Add Student**                        | `add n/NAME e/EMAIL t/ENUM_TAG`<br/>e.g. `add n/Susan Tan e/e0123456@u.nus.edu t/GOOD` |
| **Edit Student**                       | `edit INDEX [n/NAME] [e/EMAIL] [t/ENUM_TAG]`<br/> e.g. `edit 1 n/JOHN`                 |
| **Delete Student**                     | `delete INDEX` e.g. `delete 1`                                                         |
| **Clear All Students**                 | `clear`                                                                                |
| **Reset Student List**                 | `reset`                                                                                |
| **Sort Students**                      | `sort`                                                                                 |                                                                                        |
| **Find Students**                      | `find [n/KEYWORD] [t/TAG] [pg/QUESTION] [r/REMARK]` e.g. `find n/John`                 |
| **List Students**                      | `list`                                                                                 |
| **Add Remark**                         | `remark INDEX r/REMARK`<br/>e.g. `remark 1 r/needs more help`                          |
| **Add Pending Question**               | `pq INDEX r/PENDINGQUESTION`<br/>e.g. `pq 1 pq/What is a logic gate?`                  |
| **Remove Remark/<br>Pending Question** | `remove INDEX [r/] [pq/]` e.g. `remove 2 r/`                                           |
| **Home**                               | `home`                                                                                 |
| **Exit**                               | `exit`                                                                                 |

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **Glossary**

| Term/Keyword                           | Definition                                                                                                 |
|----------------------------------------|------------------------------------------------------------------------------------------------------------|
| **Command Line Interface (CLI)**       | An interface that allows you to interact with our program by inputting lines of text called command-lines. |


[_Back to Top_](#table-of-contents)