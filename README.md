### Notes App

Created by:
Braiden Miller

## How To Access:

# There are several projects in here:

ALL Java programs are run on server 8085;
angular is run on 4200

* NotesREST: Full stack web app. uses a MySQL database connected with Java and ran through Angular and deployed on AWS
Link to project on AWS: http://54.219.123.219:8080/NotesREST/#/home 
Username: user Password: password
Username: admin Password: password

* SimpleNotesREST: download the SimpleNotesREST.JAR file and run `java -jar SimpleNotesREST.jar &` on the terminal to use the curl commands for api calls

* ngGitHubAPI: this is the angular frontend for the angular/issues api from github. download the file navigate to it and run `ng - serve -o &`

## Description

* NotesREST: as a non-signed in user you will see the home page that asks you to register. If you try to navigate to the notes page and you are not logged in it will re-direct you to the register page. If you sign in with incorrect credentials a message pops up letting you know it didnt work. Once logged in you are taken to the notes page to see your notes. the number icon tells you how many active notes you have and will change colors if you have too many notes (yellow at 5 and red at 7). you can mark the notes to cross them out and view them later. The delete button completely deletes the note. there is a button to view all notes crossed off and not crossed off. The search bar adjusts the list as you type. the list is sorted by last updated. clicking on a list item will bring up more details and the ability to edit the note. Clicking the profile view will allow you to see and edit your info and allow you to deactivate your account.

Signing in as an admin will have the same features but a new button on the nav bar allowing you admin privlages. There you can see all users. By clicking the user you can see all of there notes and change their role or activated status. 

The site doesn't allow you to see notes unless you are that person (or admin) the site has protections so a basic user cannot navigate to the admin page it will redirect them to the home page. 

Spring security is used to hash the password and is using basic auth to protect the data.

* SimpleNotesREST: This is run from the command line and uses an in memory list of notes. Users can use a tool like postman to test the routes or terminal basic crud operations are used

* ngGitHubAPI: This connects the GitHub angular issues api and filters them by the last 7 days in order from most recent. clicking on an item will open more details. 


## Technology used

Java, Spring RESTFul Services, Spring Security, JPA, MySQL, HTML, CSS, Angular, Bootstrap, AWS, GitHub.

