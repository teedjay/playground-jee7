package teedjay.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Holder of a few data elements (JAXB needs root element to produce xml, easy to add annotation)
 *
 * @author thore
 */
@XmlRootElement
public class TestData {

    private Long age;
    private String name;

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
