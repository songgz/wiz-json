package sgz.wizjson;

import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class JsonWriter {
    private StringWriter json;
    private String delimiter;

    JsonWriter() {
        this.json = new StringWriter();
    }

    void writeNull() {
        this.json.append("null");
    }

    void writeValue(String value) {
        this.json.append('"');
        this.json.append(value);
        this.json.append('"');
    }

    void writeValue(char value) {
        this.json.append('"');
        this.json.append(value);
        this.json.append('"');
    }

    void writeValue(Number value) {
        this.json.append(value.toString());
    }

    void writeValue(Boolean value) {
        this.json.append(value ? "true" : "false");
    }

    void writeValue(Enum<?> value) {
        this.json.append('"');
        this.json.append(value.name());
        this.json.append('"');
    }


    void writeDelimiter() {
        this.json.append(',');
    }

    void writeMore() {
        if (this.delimiter != " ") {
            this.writeDelimiter();
        }else{
            this.delimiter = ",";
        }
    }

    void arrayBegin() {
        this.json.append('[');
        this.delimiter = " ";
    }

    void arrayElement(Object value) {
        this.writeMore();
        this.writeValue(value);
    }

    void arrayEnd() {
        this.json.append(']');
    }

    void objectBegin() {
        this.json.append('{');
        this.delimiter = " ";
    }

    void objectProperty(String key, Object value) {
        this.writeMore();
        this.writeName(key);
        this.writeValue(value);
    }

    void objectEnd() {
        this.json.append('}');
    }

    void writeName(Object name) {
        this.json.append('"');
        this.json.append(name.toString());
        this.json.append('"');
        this.json.append(':');
    }

    void writeValue(Object value) {
        if (value == null) {
            this.writeNull();
        } else if (value instanceof Map) {
            this.writeValue((Map) value);
        } else if (value instanceof Collection) {
            this.writeValue((Collection) value);
        } else if (value.getClass().isArray()) {
            this.writeArray(value);
        } else if (value instanceof Number) {
            this.writeValue((Number) value);
        } else if (value instanceof String) {
            this.writeValue((String) value);
        } else if (value instanceof Boolean) {
            this.writeValue((Boolean) value);
        } else if(value instanceof Enum){
            this.writeValue((Enum)value);
        } else if (value instanceof Character) {
            this.writeValue((char) value);

        }else{
            this.writeBean(value);
        }

    }

    void writeValue(Map<?, ?> value) {
        this.objectBegin();
        value.forEach((k, v) -> {
            this.objectProperty(k.toString(), v);
        });
        this.objectEnd();
    }

    void writeValue(Collection<?> value) {
        this.arrayBegin();
        value.forEach(item -> {
            this.arrayElement(item);
        });
        this.arrayEnd();
    }

    void writeArray(Object value) {
        this.arrayBegin();
        int length = Array.getLength(value);
        for (int i=0; i<length; i++){
            this.arrayElement(Array.get(value, i));
        }
        this.arrayEnd();
    }

    void writeValue(Iterable<?> value) {
        this.json.append('[');
            for (Object o: value){
                this.writeValue(o);
            }
        this.json.append(']');
    }

    void writeBean(Object value) {
        Class<?> clazz = value.getClass();
//        Method[] methods = clazz.getMethods();
        this.objectBegin();
        for (Method m : clazz.getMethods()) {
            if (m.getName().startsWith("get") && m.getParameterTypes().length <= 0) {
                try {
                    if (Object.class.getMethod(m.getName()) != null) {
                        continue;
                    }
                } catch (Exception ignore){}

                String property = m.getName().substring(3).toLowerCase();

                try {
                    Object res = m.invoke(value);
                    this.objectProperty(property, res);
                } catch (InvocationTargetException ite) {

                } catch (IllegalAccessException iae) {

            }


            }

        }
        this.objectEnd();
    }

    public String toString() {
        return this.json.toString();
    }


}
