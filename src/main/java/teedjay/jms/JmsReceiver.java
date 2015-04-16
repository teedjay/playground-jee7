package teedjay.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * MDB to receive messages from queue
 *
 * @author thore
 */
@MessageDriven(name = "MyQueueMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/MyQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class JmsReceiver implements MessageListener {

    @Inject JmsCounter jmsCounter;

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Received and handled : " + message.getBody(String.class));
            jmsCounter.increment();
        } catch (JMSException e) {
            System.err.println("Error while fetching message payload: " + e.getMessage());
        }
    }

}
