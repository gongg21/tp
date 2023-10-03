
    var pageVueRenderFn = function noop (a, b, c) {};
    var pageVueStaticRenderFns = [function anonymous(
) {
with(this){return _c('p',[_v("CodeSphere is a "),_c('strong',[_v("desktop contact management app, optimised for use via a Command Line Interface (CLI)")]),_v(" while still having the benefits of a Graphical User Interface (GUI). It is an app targeted at connecting Year 1 Computer Science students in the NUS School of Computing.")])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"features"}},[_v("Features"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#features","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Create profile: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("create")])]),_v(" "),_c('li',[_v("Deleting profile: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete")])]),_v(" "),_c('li',[_v("Adding profile information: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add")])]),_v(" "),_c('li',[_v("Removing profile information: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remove")])]),_v(" "),_c('li',[_v("Show your own profile: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("show")])]),_v(" "),_c('li',[_v("Exiting the app: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")])])])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"features-2"}},[_v("Features"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#features-2","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"creating-profile-create-coming-soon"}},[_v("Creating profile : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("create")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#creating-profile-create-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("create n/Name p/PHONE_NUMBER e/EMAIL")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("NAME: string")]),_v(" "),_c('li',[_v("PHONE_NUMBER: int of length 8")]),_v(" "),_c('li',[_v("EMAIL: string ending with @u.nus.edu")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("create n/Alex Yeoh p/87438807 e/alexyeoh@u.nus.edu")])]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("create n/Ben Koh p/94572431 e/benkoh@u.nus.edu")])])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"deleting-profile-delete-coming-soon"}},[_v("Deleting profile: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#deleting-profile-delete-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("User: delete")])])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("User confirms that they really wishes to delete their profile:\n"),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("System: Are you sure? (Y/N)")]),_v(" "),_c('br'),_v(" "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("User: Y")])]),_v(" "),_c('li',[_v("Success message shown, user profile is deleted from the interface and database.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("User changes their mind about deleting their profile\n"),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("System: Are you sure? (Y/N)")]),_v(" "),_c('br'),_v(" "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("User: N")])]),_v(" "),_c('li',[_v("Deletion failed message shown to user, profile not deleted.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"adding-profile-information-add-coming-soon"}},[_v("Adding profile information: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#adding-profile-information-add-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add INFORMATION [MORE_INFORMATION]…")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("INFORMATION: string")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add Major: Computer Science")])]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add Hobby: Gaming")])])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Success message shown to user, additional information added successfully and stored in database, change in GUI.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("User enters the command with incorrect formatting, resulting in an error message shown to the user and the additional information is not added into the database.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"removing-profile-information-remove-coming-soon"}},[_v("Removing profile information: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remove")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#removing-profile-information-remove-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remove INDEX")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Removes the information at the specified INDEX")]),_v(" "),_c('li',[_v("The index refers to the index number shown in the displayed profile.")]),_v(" "),_c('li',[_v("The index must be a "),_c('strong',[_v("positive integer")]),_v(" 1, 2, 3, …")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("show")]),_v(" followed by "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remove 2")]),_v(" removes the 2nd line of information to be removed.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("User profile with the deleted information and the data is removed from the interface and database.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Error message shown to the user, profile information not deleted from interface and database.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"showing-your-full-profile-show-coming-soon"}},[_v("Showing your full profile : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("show")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#showing-your-full-profile-show-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("show")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("User profile with all added information displayed.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Error message shown to user.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"exiting-the-program-exit-coming-soon"}},[_v("Exiting the program : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")]),_v(" [coming soon]"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#exiting-the-program-exit-coming-soon","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")])])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"command-summary"}},[_v("Command summary"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#command-summary","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('div',{staticClass:"table-responsive"},[_c('table',{staticClass:"markbind-table table table-bordered table-striped"},[_c('thead',[_c('tr',[_c('th',[_v("Action")]),_v(" "),_c('th',[_v("Format, Examples")])])]),_v(" "),_c('tbody',[_c('tr',[_c('td',[_c('strong',[_v("Create")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("create n/NAME p/PHONE_NUMBER e/EMAIL ")]),_v(" "),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/James Ho p/22224444 e/jamesho@u.nus.edu")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Delete")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Add")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add INFORMATION [MORE_INFORMATION]…")]),_c('br'),_v(" e.g.,"),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Add Major: Computer Science")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Remove")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remove INDEX")]),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remove 3")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Show")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("show")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Exit")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")])])])])])])}
}];
  