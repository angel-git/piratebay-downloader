This software connects to http://www.pogdesign.co.uk/cat/ and checks the TV shows of the day, you can chose one and it will look for it in http://thepiratebay.sx/ and will also trigger uTorrent to start the download.
Is a console environment. Check screenshot.png to see how it looks.

[KNOWN ISSUES]
- The GUI application does not run behind a proxy
- The GUI application is on beta state!!

[INSTRUCTIONS]
- you need to have installed uTorrent. You can download from http://www.utorrent.com/
- you need to edit the file configuration.properties under src/resources/com/ags/pirate/configuration folder to set up the application. (if you are not behind a proxy don't worry for the proxy configuration)
- you need to have maven installed. http://maven.apache.org/

[RUNNING]
- in the root of the project, execute: "mvn clean install" to create the command line application and the gui application
- the previous step will generate a bat/sh file under your project_location/target/appassembler/bin folder
- execute Pirate.bat or Pirate.sh with the argument -proxy if you are behind one.

This is my first project that I upload on GitHub, I know there are better tools to do this task but as I did it a while ago I thought that somebody will find it useful. And also is really fast to download torrents just using numbers :-)
There aren't comments but as is a small project and don't think they are necessary
Hope somebody will enjoy it :)