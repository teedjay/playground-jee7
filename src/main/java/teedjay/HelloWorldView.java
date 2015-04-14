package teedjay;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Simple CDI for holding request information for the hello-world.xhtml jsf page
 *
 * @author thore
 */
@Named
@RequestScoped
public class HelloWorldView {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
