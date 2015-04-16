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
            System.out.println("Received and handled : " + message.getBody(String.class));
            Thread.sleep(1000);
            jmsCounter.increment();
        } catch (Exception e) {
            System.err.println("Error while handling message payload: " + e.getMessage());
        }
    }

}
