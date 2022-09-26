package sgz.wizjson;

import java.util.HashMap;
import java.util.Map;


public class JsonObject extends HashMap<String, Object> {

    public JsonObject() {
        super();
    }

    public JsonObject(Map<?, ?> value) {
        value.forEach((k, v) -> {
            this.put(k.toString(), v);
        });
    }

    public JsonObject(Object value) {

    }

    public Object object() {
        return this;
    }



    public String toString() {
        JsonWriter builder = new JsonWriter();
        builder.writeValue((Map<String, Object>) this);
        return builder.toString();
    }




    public String serialize() {
        return "";
    }

    public String serialize(StringBuilder builder) {


        return "";
    }

}
