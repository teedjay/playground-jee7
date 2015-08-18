package teedjay.rest;

import teedjay.jms.JmsResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Defines the application path for the REST system
 *
 * @author thore
 */
@ApplicationPath("/rest")
public class RestRoot extends Application {

    // TODO is this really needed in JEE7, or will CDI scan classpath and find resouces?
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(
            TestResource.class,
            JmsResource.class
        ));
    }

}
