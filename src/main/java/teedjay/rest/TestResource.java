package teedjay.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Defines a few simple rest functions to demonstrate JSON and XML response
 *
 * @author thore
 */
@Path("test")
public class TestResource {

    @GET
    @Path("xml")
    @Produces("application/xml")
    public Response getTestDataAsXml() {
        TestData d = createSomeTestData();
        return Response.ok(d).build();
    }

    @GET
    @Path("json")
    @Produces("application/json")
    public Response getTestDataAsJson() {
        TestData d = createSomeTestData();
        return Response.ok(d).build();
    }

    private TestData createSomeTestData() {
        TestData d = new TestData();
        d.setName("Hi There");
        d.setAge(18 + System.currentTimeMillis() % 50);
        return d;
    }

}
