## TeamCity Flowdock custom notifier

TeamCity plugin that allows sending notifications to Flowdock on build events.
No authentication needed, it works with flow source tokens.

### Build
Use Maven 3.3.9 or newer.

Issue 'mvn package' command from the root project to build your plugin. Resulting package <artifactId>.zip will be placed in 'target' directory. 
 
### Install
To install the plugin, put zip archive to 'plugins' dir under TeamCity data directory and restart the server.

 
