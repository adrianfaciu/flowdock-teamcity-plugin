# How to use the plugin

Instructions on how to install, configure and use the plugin.

## Add the plugin to TeamCity
Easiest way to get the plugin is to select 'Releases' tab on the GitHub project and download the zip file.

To install the plugin, put zip archive to 'plugins' dir under TeamCity data directory and restart the server.

## Create a flow source in Flowdock

In order to be able to create sources for our flows we need first to create a Developer application inside Flowdock.
We can go to the ["Developer Applications"](https://www.flowdock.com/oauth/applications/) tab and hit 'New Application'.

We can use the following settings: 
- any name, description and help text that is relevant for us
- check 'Shortcut application' checkbox, because we'll use it only to create sources (for now)
- add a nice icon to the 'Small icon' field, for example TeamCity logo

Once we have the application created we can use it to create new sources. 
Give the source a relevant name, select the flow where you want the messages to be posted and hit 'bGenerate Source'.

Be sure to copy/paste the string somewhere safe because you'll not be able to see it again. 
This token will allow us to send messages to the flow that we selected.

## Configuring the plugin in TeamCity 

The flow source can be set in two places. The main one in Administration -> Flowdock Notifier.
This one will be used to send all the notifications.

If we want to overwrite it for a specific project, meaning send the notifications for that project to a different flow,
we can go to Projects -> Project Tab -> Flowdock Notifier and paste a different token.

When the plugin is sending a notification it will look for a token on the project level, if there is one it will use it, 
otherwise it will use the token from the main settings.

## Enable notifications

To enable notifications in TeamCity, you need to go to the user profile page (click on the user in the top right corner) and go to 'Notification Rules' tab.

Here you can select 'Flowdock Notifier', hit 'Add new rule' and configure what notifications you would like to receive.