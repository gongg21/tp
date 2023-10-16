
    var pageVueRenderFn = function noop (a, b, c) {};
    var pageVueStaticRenderFns = [function anonymous(
) {
with(this){return _c('h1',{attrs:{"id":"codesphere-user-guide"}},[_v("CodeSphere User Guide"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#codesphere-user-guide","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("CodeSphere is a "),_c('strong',[_v("desktop contact management app, optimised for use via a Command Line Interface (CLI)")]),_v(" while still having the benefits of a Graphical User Interface (GUI). It is an app targeted at connecting Year 1 Computer Science students in the NUS School of Computing.")])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"features"}},[_v("Features"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#features","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Viewing help: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")])]),_v(" "),_c('li',[_v("Adding a student: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add")])]),_v(" "),_c('li',[_v("Editing a student: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit")])]),_v(" "),_c('li',[_v("Deleting student: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete")])]),_v(" "),_c('li',[_v("Tagging a student: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("tag")])]),_v(" "),_c('li',[_v("Exit the app: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")])])])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"features-2"}},[_v("Features"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#features-2","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"adding-a-student-add-coming-soon"}},[_v("Adding a student : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#adding-a-student-add-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/Name p/COURSE e/EMAIL")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("NAME: string")]),_v(" "),_c('li',[_v("COURSE: string that is a valid course in NUS SoC")]),_v(" "),_c('li',[_v("EMAIL: string ending with @u.nus.edu")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/Susan Tan c/CS1101S e/susantan@u.nus.edu")])]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/Ben Koh c/CS1231S e/benkoh@u.nus.edu")])])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"editing-a-student-edit-coming-soon"}},[_v("Editing a student : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#editing-a-student-edit-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit INDEX [n/NAME] [c/COURSE] [e/EMAIL] [r/REMARK]...")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Edits the person at the specified "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("INDEX")]),_v(". The index refers to the index number shown in the displayed students list.")]),_v(" "),_c('li',[_v("The index "),_c('strong',[_v("must be a positive integer")]),_v(" 1, 2, 3, …​")]),_v(" "),_c('li',[_v("At least one of the optional fields must be provided.")]),_v(" "),_c('li',[_v("Existing values will be updated to the input values.")]),_v(" "),_c('li',[_v("When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.")]),_v(" "),_c('li',[_v("You can remove all the person’s tags by typing t/ without specifying any tags after.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit 1 c/CS1101S e/susantan@u.nus.edu")]),_v(" Edits the course and email address of the 1st person to be CS1101S and "),_c('a',{attrs:{"href":"mailto:susantan@u.nus.edu"}},[_v("susantan@u.nus.edu")]),_v(" respectively.")]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit 2 n/Alex Yeoh t/")]),_v(" Edits the name of the 2nd person to be Alex Yeoh and clears all existing tags.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"deleting-a-student-delete-coming-soon"}},[_v("Deleting a student: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#deleting-a-student-delete-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete INDEX")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Deletes the student at the specified "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("INDEX")]),_v(". The index refers to the index number shown in the displayed students list.")]),_v(" "),_c('li',[_v("The index refers to the index number shown in the displayed students list.")]),_v(" "),_c('li',[_v("The index "),_c('strong',[_v("must be a positive integer")]),_v(" 1, 2, 3, …​")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete 2")]),_v(" Deletes the student at index 2 of the displayed students list.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Success message shown to user, the specified student is successfully deleted and removed from the database, change in GUI.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Users enter the command with incorrect formatting, resulting in an error message shown to the user and the specified student is not deleted and removed from the database.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"tagging-a-student-tag-coming-soon"}},[_v("Tagging a student "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("tag")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#tagging-a-student-tag-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("tag INDEX t/ [ENUM_TAG]")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Adds a tag for the student at the specified "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("INDEX")]),_v(". The index refers to the index number shown in the displayed students list.")]),_v(" "),_c('li',[_v("The index "),_c('strong',[_v("must be a positive intege")]),_v("r 1, 2, 3, …​")]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("ENUM_TAG")]),_v(" must be from the pre-defined enumerated tag definitions "),_c('em',[_v("good, average, poor")]),_v(".")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("tag 1 t/AVERAGE")]),_v(" Tags the student at index 1 of the displayed students list to be AVERAGE.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Success message shown to user, the specified student is successfully tagged as a ‘average performing student’, change in GUI.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Users enter the command with incorrect formatting, resulting in an error message shown to the user and tag status (whether untagged / previously tagged) of the specified student remains as it was before.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"adding-a-remark-for-a-student-remark-coming-soon"}},[_v("Adding a remark for a student: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#adding-a-remark-for-a-student-remark-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark INDEX r/REMARK")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Adds a remark for the student at the specified "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("INDEX")]),_v(". The index refers to the index number shown in the displayed students list.")]),_v(" "),_c('li',[_v("The index must be a "),_c('strong',[_v("positive integer")]),_v(" 1, 2, 3, …")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark 2 r/needs more help")]),_v(" Adds a remark to the student at index 2 of the displayed students list saying needs more help.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Success message shown to user, a remark is added to the specified student and this information is updated to the database, change in GUI.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Users enter the command with incorrect formatting, resulting in an error message shown to the user and no new remark is added to the specified student.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"viewing-help-help-coming-soon"}},[_v("Viewing help : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#viewing-help-help-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"exiting-the-program-exit-coming-soon"}},[_v("Exiting the program : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#exiting-the-program-exit-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Success message shown to user, exit the GUI.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Error message shown to user.")])])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"command-summary"}},[_v("Command summary"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#command-summary","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('div',{staticClass:"table-responsive"},[_c('table',{staticClass:"markbind-table table table-bordered table-striped"},[_c('thead',[_c('tr',[_c('th',[_v("Action")]),_v(" "),_c('th',[_v("Format, Examples")])])]),_v(" "),_c('tbody',[_c('tr',[_c('td',[_c('strong',[_v("Add")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/NAME c/COURSE e/EMAIL")]),_v(" "),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/Susan Tan c/CS1101S e/susantan@u.nus.edu")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Edit")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit INDEX [n/NAME] [c/COURSE] [e/EMAIL] [r/REMARK]")]),_v(" "),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit 1 c/CS1101S e/susantan@u.nus.edu")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Delete")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete INDEX")]),_c('br'),_v(" e.g.,"),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete 2")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Tag")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("tag INDEX t/ [ENUM_TAG]")]),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("tag 1 t/AVERAGE")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Remark")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark INDEX r/REMARK")]),_v(" "),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark 2 r/needs more help")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Exit")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")])])])])])])}
},function anonymous(
) {
with(this){return _c('footer',[_c('div',{staticClass:"text-center"},[_c('small',[_v("["),_c('span',[_c('strong',[_v("Powered by")])]),_v(" "),_c('img',{attrs:{"src":"https://markbind.org/favicon.ico","width":"30"}}),_v(" "),_c('a',{attrs:{"href":"https://markbind.org/"}},[_v("MarkBind 5.1.0")]),_v(", generated on Mon, 16 Oct 2023, 16:03:21 GMT+8]")])])])}
}];
  