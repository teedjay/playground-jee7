package teedjay.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * MDB to receive messages from queue and simulate some workload, we limit to 10 concurrent instances (15 is default)
 *
 * @author thore
 */
@MessageDriven(name = "MyQueueMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/MyQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "10") })
public class JmsReceiver implements MessageListener {

    @Inject JmsCounter jmsCounter;

    @Override
    public void onMessage(Message message) {
        try {
            String json = message.getBody(String.class);
            JmsPayload p = JmsPayload.fromJSON(json);
            Thread.sleep(1000); // simulate some work
            System.out.println("Received payload for processing message " + p.getMsgNo() + " and handled action : " + p.getCurrentStep());
            jmsCounter.increment();
        } catch (Exception e) {
            System.err.println("Error while handling message payload: " + e.getMessage());
        }
    }

}
