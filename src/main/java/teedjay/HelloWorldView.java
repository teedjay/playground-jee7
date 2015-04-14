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

    public void submit() {
        if (name == null) name = "";
        name = name.trim();
        if (name.length() > 1) {
            name = name.substring(0, 1).toUpperCase().concat(name.substring(1).toLowerCase());
        } else {
            name = name.toUpperCase();
        }
    }

}
