
<h2>MESSAGE EXCHANGE SERVICE</h2>

An application to receive messages from some source(s), persist the message and forward it to some destination

<b>Getting Started</b>
Get a copy of the project by cloning or downloading the project zip.

<b>Prerequisites</b>
<ol>
<li>A mysql database hosted localhost with 3306 and schema name msg_exchange.</li>
<li>Databse user with name exchange and password 3xc#@n8e with create, inster and update rights.</li> 
<li>Test bench application running on localhost port 8181</li>
<li>Create an ACTIVE MQ queue with the default url of name <b>messages.exchange</b></li>
</ol>
<b>Instaling</b>
Run the following command from the main application directory to install jars and download dependencies.
<h4>mvn clean install</h4>

<b>Starting the application</b>
Option 1: Run the MainApp class 

<b>Application Log</b>
Application is configured to log activity to /data/logs/message_exchange.log



