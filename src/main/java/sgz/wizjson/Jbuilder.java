package sgz.wizjson;

import java.util.Collection;
import java.util.Map;

public class Jbuilder {
    private JsonObject result;

    public Jbuilder() {
        this.result = new JsonObject();

    }

    void wrap(Object value) {
//        if (value == null) {
//            this.setNull(key, value);
//        } else if (value instanceof Map) {
//            this.setMap(key, value);
//        } else if (value instanceof Collection) {
//            this.setCollection(key, value);
//        } else if (value.getClass().isArray()) {
//            this.setArray(key, value);
//        } else if (value instanceof Number) {
//            this.setNumber(key, value);
//        } else if (value instanceof String) {
//            this.setString(key, value);
//        } else if (value instanceof Boolean) {
//            this.setBoolean(key, value);
//        } else if(value instanceof Enum){
//            this.setEnum(key, value);
//        }


    }

    public void setObject(String key, Object value) {
        this.result.put(key, value);
    }

    void setNull(String key, Object value) {
        this.result.put(key, "null");
    }

    void setMap(String key, Object value) {

    }

    void setCollection(String key, Object value) {

    }

    void setArray(String key, Object value) {

    }

    void setNumber(String key, Object value) {

    }

    void setString(String key, Object value) {

    }

    void setBoolean(String key, Object value) {

    }

    void setEnum(String key, Object value) {

    }

    void merge(Object value) {

    }

    String toJson() {
        //this.result.accept(Visit v);
        return "";
    }

}
