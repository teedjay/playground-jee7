package teedjay.jms;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

/**
 * Defines a simple payload object that can be serialized to JSON.
 *
 * @author thore
 */
public class JmsPayload {

    private String id;
    private String msgNo;
    private String currentStep;

    // execution_plan consisting of named steps (pointed to by current_step)
    // system_values : a set of named RO properties controlled by the system
    // message_values : a set of named RW properties controlled by the flow

    public JmsPayload(String id, String msgNo, String currentStep) {
        this.id = id;
        this.msgNo = msgNo;
        this.currentStep = currentStep;
    }

    public static JmsPayload fromJSON(String jsonString) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return new JmsPayload(
                jsonObject.getString("id"),
                jsonObject.getString("msg_no"),
                jsonObject.getString("current_step")
            );
    }

    public String toJSON() {
        String json = Json.createObjectBuilder()
            .add("id", id)
            .add("msg_no", msgNo)
            .add("current_step", currentStep).build().toString();
        return json;
    }

    public String getMsgNo() {
        return msgNo;
    }

    public String getCurrentStep() {
        return currentStep;
    }

}
