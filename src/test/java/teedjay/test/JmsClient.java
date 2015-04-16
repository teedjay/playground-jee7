package teedjay.test;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Manual testing of remote access to the JMS queue system
 *
 * @author thore
 */
public class JmsClient {

    private static final Logger log = Logger.getLogger(JmsClient.class.getName());

    // Set up all the default values
    private static final String DEFAULT_MESSAGE = "{\"message\":\"Hello, World - says the remote JmsClient!\"}";
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/MyQueue";
    private static final String DEFAULT_MESSAGE_COUNT = "100";
    private static final String DEFAULT_USERNAME = "jmsuser";
    private static final String DEFAULT_PASSWORD = "jmspwd123";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

    /**
     * (1) Make sure WildFly has been started
     * (2) Make sure queue is available under JNDI name : java:/jboss/exported/jms/queue/MyQueue (/jboss/exported/... will be remotely available)
     * (3) Make sure ApplicationUser "jmsuser" belongs to "guest" role (or other role that can access JMS, use ./bin/add-user.sh to create user)
     */
    public static void main(String[] args) {

        Context namingContext = null;

        try {

            String userName = System.getProperty("username", DEFAULT_USERNAME);
            String password = System.getProperty("password", DEFAULT_PASSWORD);

            // Set up the namingContext for the JNDI lookup
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
            env.put(Context.SECURITY_PRINCIPAL, userName);
            env.put(Context.SECURITY_CREDENTIALS, password);
            namingContext = new InitialContext(env);

            // Perform the JNDI lookups
            String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
            log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
            log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");

            String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
            log.info("Attempting to acquire destination \"" + destinationString + "\"");
            Destination destination = (Destination) namingContext.lookup(destinationString);
            log.info("Found destination \"" + destinationString + "\" in JNDI");

            int count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));
            String content = System.getProperty("message.content", DEFAULT_MESSAGE);

            try (JMSContext context = connectionFactory.createContext(userName, password)) {

                log.info("Sending " + count + " messages with content: " + content);
                JMSProducer producer = context.createProducer();
                // Send the specified number of messages
                for (int i = 0; i < count; i++) {
                    producer.send(destination, content);
                }

                /* Create the JMS consumer
                JMSConsumer consumer = context.createConsumer(destination);
                for (int i = 0; i < count; i++) {
                    String text = consumer.receiveBody(String.class, 5000);
                    log.info("Received message with content " + text);
                }
                */

                log.info("The MDB listing on the queue should have received " + count + " new messages");

            }
        } catch (NamingException e) {
            log.severe(e.getMessage());
        } finally {
            if (namingContext != null) {
                try {
                    namingContext.close();
                } catch (NamingException e) {
                    log.severe(e.getMessage());
                }
            }
        }

    }

}
