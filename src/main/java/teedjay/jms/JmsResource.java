package teedjay.jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Defines a simple REST method to add JMS messages onto a named queue
 *
 * Create a durable queue using this command with : jboss-cli.sh
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
        JmsPayload p = new JmsPayload("" + System.nanoTime(), "" + System.currentTimeMillis(), "HI FROM JAX-RS");
        String json = p.toJSON();
        jmsContext.createProducer().send(queue, json);
        return "<h1> Added JSON </h1>\n" + json;
    }

}
