This software connects to http://www.pogdesign.co.uk/cat/ and checks the TV shows of the day, you can chose one and it will look for it in http://thepiratebay.sx/ and will also trigger uTorrent to start the download.
Is a console and gui environment

![Image](screenshot.png?raw=true)

KNOWN ISSUES
------------
- The GUI application is on beta state!!
- The GUI application does not run behind a proxy
- The GUI closes when there is no connection available

INSTRUCTIONS
------------
- you need to have installed uTorrent. You can download from [uTorrent](http://www.utorrent.com/)
- you need to edit the file configuration.properties under piratebay-downloader-common/src/main/resources/com/ags/pirate/common/configuration folder to set up the application.
- if you are behind a proxy put true the property pirate.http.proxyActivated
- you need to have maven installed. [manve site](http://maven.apache.org/)

RUNNING
-------
- in the root of the project, execute: `mvn clean install` to create the command line application and the gui application
- the previous step will generate a bat/sh file under:
    - your project_location/piratebay-downloaded-console/target/pirate-console/bin
    - your project_location/piratebay-downloaded-gui/target/pirate-gui/bin
- execute Pirate.bat or Pirate.sh

This is my first project that I upload on GitHub, I know there are better tools to do this task but as I did it a while ago I thought that somebody will find it useful. And also is really fast to download torrents just using numbers :-)
There aren't comments but as is a small project and don't think they are necessary
Hope somebody will enjoy it :)