---
layout: default.md
title: "User Guide"
pageNav: 3
---

# CodeSphere User Guide

CodeSphere is a **desktop contact management app, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). It is an app targeted at connecting Year 1 Computer Science students in the NUS School of Computing.

### Features
* Create profile: `create`
* Deleting profile: `delete`
* Adding profile information: `add`
* Removing profile information: `remove`
* Show your own profile: `show`
* Exiting the app: `exit`


--------------------------------------------------------------------------------------------------------------------

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Creating profile : `create` [coming soon]

Add basic profile information to the user profile.

Format: `create n/Name p/PHONE_NUMBER e/EMAIL`
* NAME: string
* PHONE_NUMBER: int of length 8
* EMAIL: string ending with @u.nus.edu

Examples:
* `create n/Alex Yeoh p/87438807 e/alexyeo@u.nus.edu`
* `create n/Ben Koh p/94572431 e/benkoh@u.nus.edu`

Command succeeds: Success message shown to user, profile created successfully and stored in database, change in GUI.

Command failure: Users enter the command with incorrect formatting, resulting in an error message shown to the user and the profile is not created and stored into the database.


### Deleting profile: `delete` [coming soon]

Deletes the entire user profile with verification check (user will no longer exist in the database).

Format: `delete`

Examples:
* `User: delete`

Command succeeds:
* User confirms that they really wishes to delete their profile:
  `System: Are you sure? (Y/N)` </br>
  `User: Y`
* Success message shown, user profile is deleted from the interface and database.

Command failure:
* User changes their mind about deleting their profile
  `System: Are you sure? (Y/N)` </br>
  `User: N`
* Deletion failed message shown to user, profile not deleted.

### Adding profile information: `add` [coming soon]

Adds additional information to the user profile (saved as String). Each new entry is saved as a new line in a numbered list.

Format: `add INFORMATION [MORE_INFORMATION]…`
* INFORMATION: string

Examples:
* `add Major: Computer Science`
* `add Hobby: Gaming`

Command succeeds:
* Success message shown to user, additional information added successfully and stored in database, change in GUI.

Command failure:
* User enters the command with incorrect formatting, resulting in an error message shown to the user and the additional information is not added into the database.

### Removing profile information: `remove` [coming soon]

Deletes a specified profile information from the user profile.

Format: `remove INDEX`
* Removes the information at the specified INDEX
* The index refers to the index number shown in the displayed profile.
* The index must be a **positive integer** 1, 2, 3, …

Examples:
* `show` followed by `remove 2` removes the 2nd line of information to be removed.

Command succeeds:
* User profile with the deleted information and the data is removed from the interface and database.

Command failure:
* Error message shown to the user, profile information not deleted from interface and database.

### Showing your full profile : `show` [coming soon]

Shows a user’s own user profile, including basic profile information and all additional information, for verification.

Format: `show`

Command succeeds:
*  User profile with all added information displayed.

Command failure:
* Error message shown to user.

### Exiting the program : `exit` [coming soon]

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------
## Command summary

Action | Format, Examples
--------|------------------
**Create** | `create n/NAME p/PHONE_NUMBER e/EMAIL ` <br> e.g., `add n/James Ho p/22224444 e/jamesho@u.nus.edu`
**Delete** | `delete`
**Add** | `add INFORMATION [MORE_INFORMATION]…`<br> e.g.,`Add Major: Computer Science`
**Remove** | `remove INDEX`<br> e.g., `remove 3`
**Show** | `show`
**Exit** | `exit`
