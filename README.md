Media Remote Micro Edition
==========================

### OVERVIEW

MediaRemoteME is a set of mobile app and server software projects that can 
control media players such as iTunes and Yahoo Launchcast streaming music 
player from a Java Micro Edition (Java ME)-enabled mobile phone.

All MediaRemoteME projects have been created in Netbeans and tested in
Netbeans 6.9 to 7.1.

### PROJECT ARCHITECTURE

Details about the MediaRemoteME architecture are available on the website:
https://github.com/barbeau/Media-Remote-ME/wiki

### FOLDERS

1. `/MobileiTunesClient`        - Java ME Mobile app for a mobile phone that sends media commands to iTunesServer app
				hosted in a Java app server (e.g., Glassfish) running on same machine as media player
2. `/YahooLaunchCastController` - Desktop Library project that implements control of YahooLaunchCast streaming music player 
				via Java Robot API, which triggers cursor movement and clicks.
3. `/iTunesController`	      - Desktop Library project that implements control of iTunes via the iTunes COM for 
				Windows SDK
4. `/iTunesServer`	      - Java server app, deployable to Glassfish, which listens for commands from the 
				MobileiTunesClient mobile app and passes these commands to the different media controllers
5. `/mobile_images`	      - A folder which contains images for the mobile GUI that shows play, volume, next/prev 
				track buttons



[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/barbeau/media-remote-me/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

