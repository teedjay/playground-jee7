package teedjay.jms;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Named singleton bean holding the number of JMS messages that has been handled successfully.
 *
 * @author thore
 */
@Named
@Singleton
public class JmsCounter {

    private volatile long counter;

    public synchronized void increment() {
        counter++;
    }

    public long getCounter() {
        return counter;
    }

}
