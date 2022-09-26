package sgz.wizjson;

import java.util.List;
import java.util.Map;

public interface Json {
    //public abstract Object object();
    public abstract Class<?> getOriginType();
    public abstract String toString();

    public static JsonObject from(Map<String, Object> value) {

        return new JsonObject(value);
    }

    public static JsonArray from(List<Object> value) {

        return new JsonArray(value);
    }

    //JSON.parse(json)
    static void parse(String value) {

    }

    static String stringify(Object value) {

        return "";
    }

    //JSON.generate只允许将对象或数组转换成JSON字符串
    static void generate(Object obj) {
        if (obj.getClass().isArray()) {
             new JsonArray((List<Object>)obj);

        }

    }

    static JsonArray generate(List<Object> value) {
            return new JsonArray(value);
    }

    static JsonObject generate(Map<String, Object> value) {
        return new JsonObject(value);
    }



}
