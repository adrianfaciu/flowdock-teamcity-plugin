## TeamCity Flowdock custom notifier

TeamCity plugin that allows sending notifications to Flowdock on build events.
No authentication needed, it works with flow source tokens.

For instructions on [how to use see here](HOWTO.md).

### Build
Use Maven 3.3.9 or newer.

Issue 'mvn package' command from the root project to build your plugin. Resulting package <artifactId>.zip will be placed in 'target' directory. 
 
### Install
To install the plugin, put zip archive to 'plugins' dir under TeamCity data directory and restart the server.

 
### TODO list (randomish order)
* build level settings
* add more event information to notifications
* user defined/customized messages for notifications
* add support for Flowdock OAuth
* custom actions for threads (create issue in bug tracker if broken build, open drop folder, open web app etc.)
* view test results in notification
* custom user image (instead or if no Gravatar)
* more options to customize behaviour

Big thanks to [tcSlackBuildNotifier](https://github.com/PeteGoo/tcSlackBuildNotifier) which was quite useful for finding out how TeamCity and a custom notifier work :)

