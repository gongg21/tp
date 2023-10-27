---
layout: page
title: User Guide
---

CodeSphere is a **desktop contact management app, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). It is an app targeted at connecting Year 1 Computer Science students in the NUS School of Computing.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## Table of Contents
1. [**User Guide Tips**](#user-guide-tips)
2. [**Quick Start**](#quick-start)
3. [**Tutorial for Beginners**](#tutorial-for-beginners)
4. [**Features**](#features)
    * [**Universal Commands**](#universal-commands)
        * [`help` Viewing help](#viewing-help--help)
        * [`exit` Exiting the program](#exiting-the-program--exit)
    * [**Home Page Commands**](#home-page-commands)
        * [`add` Adding a new course](#adding-a-course--add)
        * [`edit` Editing the details of a course](#editing-a-course--edit)
        * [`delete` Deleting a course](#deleting-a-course--delete)
        * [`clear` Selecting a course](#clearing-all-course--clear)
        * [`select` Selecting a course](#selecting-a-course--select)
    * [**Course Page Commands**](#course-page-commands)
        * [`add` Adding a student](#adding-a-student--add)
        * [`edit` Editing the details of a student](#editing-a-student--edit)
        * [`delete` Deleting a student](#deleting-a-student--delete)
        * [`list` Listing all students](#listing-all-students--list)
        * [`sort` Sorting all students](#sorting-all-students--sort-coming-soon)
        * [`find` Finding a student by name](#finding-a-student--find)
        * [`find pq` Finding students with pending questions](#find-students-with-pending-questions--find-pq)
        * [`remark` Adding a remark for a student](#adding-a-remark-for-a-student--remark)
        * [`pq` Adding a pending question for a student](#adding-a-pending-question-for-a-student--pq)
        * [`remove` Removing a remark/pending question](#removing-a-remarkpending-question-of-a-student--remove)
        * [`home` Returning to home page](#returning-to-the-home-page--home)
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

   ![Ui](images/Ui.png)

In the command box, type in any command and hit enter to execute the command! 
Eg. typing `help` and hitting enter after will show the Help window. Some other example commands you can try:
* `help` : View help.
* `select 1` : Selects and goes into the 1st course in the course list.
* `add c/CS1101S` : Adds a course named `CS1101S` to the course list.
* `edit 1 c/CS1231S` : Updates the first course in the course list with the edited course name.
* `delete 3` : Deletes the third course shown in the course list.
* `exit` : Exits the app.

For a more detailed walk-through on how to use CodeSphere to start managing your students, head to [Tutorial for Beginners](#tutorial-for-beginners).
Else, if you prefer exploring the app yourself, head to [Features](#features) below for the list of commands.

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **Tutorial for Beginners**
*{to be added}*

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

### Clearing all course: `clear`
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
* `list` all existing students in the course
* `find` an existing student in the course
* add a `remark` to a student
* add a pending question `pq` to a student 
* `remove` a remark or pending question from a student

[_Back to Top_](#table-of-contents)

<div style="page-break-after: always"></div>

### Adding a student : `add`
Adds a student to the list of students in the selected course that the user is overseeing.

**Format:** `add n/NAME e/EMAIL t/ENUM_TAG`
* `NAME`: a string that is a valid name.
* `EMAIL`: string ending with @u.nus.edu.
* `ENUM_TAG`: a pre-defined enumerated tag definitions GOOD, AVERAGE, POOR.

**Examples:**
* `add n/Susan Tan e/e0946283@u.nus.edu t/GOOD`
* `add n/Ben Koh e/e9739423@u.nus.edu t/AVERAGE`

**Command succeeds:** Success message shown, student successfully added and stored in database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the student is not added/stored in the database.

### Editing a student : `edit`

Edits an existing student that the user is currently overseeing.

**Format:** `edit INDEX [n/NAME] [c/COURSE] [e/EMAIL] [r/REMARK]...`
* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed students list.
* The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing t/ without specifying any tags after.

**Examples:**
* `edit 1 c/CS1101S e/e0946283@u.nus.edu` Edits the course and email address of the 1st person to be CS1101S and e0946283@u.nus.edu respectively.
* `edit 2 n/Alex Yeoh t/` Edits the name of the 2nd person to be Alex Yeoh and clears all existing tags.

**Command succeeds:** Success message shown, student successfully edited and updated in database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the student is not updated in the database.

### Deleting a student: `delete`

Deletes the specified student from the list of students you are overseeing in the selected course.

**Format:** `delete INDEX`
* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* `INDEX` **must be a positive integer** 1, 2, 3, ...

**Examples:** `delete 2` Deletes the student at index 2 of the displayed students list.

**Command succeeds:** Success message shown, student successfully removed from database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and the student is not removed from the database.

### Listing all students: `list`

Shows a list of all students you are overseeing in the selected course.

**Format:** `list`

### Sorting all students: `sort` [coming soon]
Sorts the list of  students you are overseeing in the selected course by name or by tag.

### Finding a student: `find`
Find a student **by name** from the list of students you are overseeing in the selected course.

**Format:** `find [KEYWORDS]`
* The search is case-insensitive. e.g hans will match Hans.
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans.
* Only the name is searched.
* Only full words will be matched e.g. Han will not match Hans.

**Example:**
* `find John` returns `john` and `John Doe`.
* `find Fiona Alissa` Find the student(s) with name Fiona or Alissa, and the details of the student(s) will be displayed.

### Find students with pending questions: `find pq`
Find all students with pending questions from the list of students you are overseeing in the selected course.

**Format:** `find pq`

**Example:**
* `find John` returns all students with non-empty pending questions.
* `find pq` returns all students with pending questions.

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

**Format:** `pq INDEX pq/PENDINGQUESTION`
* Adds a pending question for the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* `INDEX` must be a **positive integer** 1, 2, 3, …

**Example:**
* `pq 2 pq/What is a logic gate?` Adds a pending question to the student at index 2 of the displayed student list asking the question “What is a logic gate?”. This indicates a requirement to follow-up with the student.

**Command succeeds:** Success message shown, pending question successfully added and updated in database, change in GUI.

**Command failure:** Incorrect format results in an error message shown and pending question is not added to the student.

### Removing a remark/pending question of a student: `remove`
Removes a remark or pending question of a specified student from the list of students.

**Format:** `remove INDEX r/ pq/`
* Removes a pending question of the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* `INDEX` must be a **positive integer** 1, 2, 3, …

**Example:**
* `remove 2 pq/` Removes a pending question of the student at index 2 of the displayed students list.

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
| **List Student**                       | `list`                                                                                 |                              
| **Sort Students**<br/>[coming soon]    | `sort`                                                                                 |
| **Find Student**                       | `find KEYWORDS` e.g. `find John`                                                       |
| **Find Pending Question**              | `find pq`                                                                              |
| **Add Remark**                         | `remark INDEX r/REMARK`<br/>e.g. `remark 1 r/needs more help`                          |                              
| **Add Pending Question**               | `pq INDEX r/PENDINGQUESTION`<br/>e.g. `pq 1 pq/What is a logic gate?`                  |
| **Remove Remark/<br>Pending Question** | `remove INDEX [r/] [pq/]` e.g. `remove 2 r/`                                           |
| **Home**                               | `home`                                                                                 |
| **Exit**                               | `exit`                                                                                 |

[_Back to Top_](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **Glossary**

*{to be added}*

[_Back to Top_](#table-of-contents)



