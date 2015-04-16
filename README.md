# playground-jee7
Playing around with JEE7 and JSE8 features

Example JEE7 Application
------------------------
Clone and fork this as a starting point when testing JEE7 features with JSE8. 

### AppServer
Should deloy and run on any JEE7 server (tested using WildFly 8.2 Final).    
There are lots of Wildfly application examples on GitHub : https://github.com/wildfly/quickstart 

### ./bin/jboss-cli.sh
WildFly needs to have durable/persistant queue available with the following internal ane external JNDI names.  
`
jms-queue add --queue-address=MyQueue --entries=java:/jms/queue/MyQueue,java:/jboss/exported/jms/queue/MyQueue
`

### ./bin/add-user.sh
WildFly needs an ApplicationUser with "guest" role to be configured before for remote access to JMS.   
`
./bin/add-user.sh 
`


### Compiling
The build uses Maven and has "clean install" as default target.  
`mvn`

### Running
Install war file on WidlFly and browser to :  
`http://localhost:8080/jee7-1.0-SNAPSHOT/`

### Testing
This will run all unit tests :  
`mvn test`
