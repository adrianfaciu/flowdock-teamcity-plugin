## TeamCity server-side plugin

Work to do:
- decide and implement UI
- figure out best notification schema; custom messages; threads;
- log manager (in util)
- settings manager/repository (in util)
- add tests
- refactor
- update plugin info (xml file)
- update readme
- test @work and make updates
- publish to github

Add mote info:
- details
- usage
- ...

### Build
In order to build the plugin one needs to add an extra repository to maven:
```xml
        <profile>
            <repositories>
                <repository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>bintray-kittinunf-maven</id>
                    <name>bintray</name>
                    <url>http://dl.bintray.com/kittinunf/maven</url>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>bintray-kittinunf-maven</id>
                    <name>bintray-plugins</name>
                    <url>http://dl.bintray.com/kittinunf/maven</url>
                </pluginRepository>
            </pluginRepositories>
            <id>bintray</id>
        </profile>
```        
Inside settings.xml from maven config folder. And activate the profile:
 ```xml
       <activeProfiles>
            <activeProfile>bintray</activeProfile>
        </activeProfiles>
 ```
 Use Maven 3.3.9 or newer.

Issue 'mvn package' command from the root project to build your plugin. Resulting package <artifactId>.zip will be placed in 'target' directory. 
 
### Install
To install the plugin, put zip archive to 'plugins' dir under TeamCity data directory and restart the server.

 
