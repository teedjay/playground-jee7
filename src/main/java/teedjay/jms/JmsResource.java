package teedjay.jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Date;

/**
 * Defines a simple method to add JMS messages onto a named queue
 *
 * Create a durable queue using this command with : jboss-cli.sh
 * jms-queue add --queue-address=MyQueue --entries=java:/jms/queue/MyQueue
 *
 * Verify in WildFly gui that queue is in JNDI as as : java:/jms/queue/MyQueue
 *
 * @author thore
 */
@Path("jms")
public class JmsResource {

    @Resource(lookup = "jms/queue/MyQueue")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    @GET
    @Path("add")
    public String getTestDataAsXml() {
        String json = "{\"milliseconds\":" + System.currentTimeMillis() + ",\"message\":\"Message from TeeDjay @" + new Date() + "\"}";
        jmsContext.createProducer().send(queue, json);
        return "<h1> Added JSON </h1>\n" + json;
    }

}
