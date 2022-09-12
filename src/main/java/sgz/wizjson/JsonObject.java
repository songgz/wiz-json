package sgz.wizjson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonObject extends HashMap<String, Object> {
    //private final Map<String, Object> json = new HashMap<>();

    public JsonObject(Map<String, Object> value) {
        super();
        value.forEach((k, v) -> {
            if (v instanceof Map) {
                v = new JsonObject((Map) v);
            }else if (v instanceof List) {
                v = new JsonArray((List) v);
            }
            this.put(k, v);
        });
    }

    public Object object() {
        return this;
    }

    public String toString() {
        JsonBuilder builder = new JsonBuilder();
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
