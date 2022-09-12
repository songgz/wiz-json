package sgz.wizjson;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class JsonBuilder {
    private StringBuilder json;
    private String delimiter;

    JsonBuilder() {
        this.json = new StringBuilder();
    }

    void writeNull() {
        this.json.append("null");
    }

    void writeDelimiter() {
        this.json.append(',');
    }

    void arrayBegin() {
        this.json.append('[');
        this.delimiter = " ";
    }

    void arrayElement(Class<?> value) {
        this.writeMore();
        this.writeValue(value);
    }

    void arrayEnd() {
        this.json.append(']');
    }

    void writeMore() {
        if (this.delimiter != " ") {
            this.writeDelimiter();
        }else{
            this.delimiter = ",";
        }

    }

    void objectBegin() {
        this.json.append('{');
        this.delimiter = " ";
    }

    void writePair(String key, Object value) {
        this.writeMore();
        this.writeName(key);
        this.writeValue(value);
    }

    void objectEnd() {
        this.json.append('}');
    }

    void writeName(Object name) {
        this.json.append('"');
        this.json.append(name);
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
        }

    }

    void writeValue(Enum<?> value) {
        this.json.append('"');
        this.json.append(value.name());
        this.json.append('"');
    }

    void writeValue(Boolean value) {
        this.json.append(value ? "true" : "false");
    }

    void writeValue(char value) {
        this.json.append(value);
    }

    void writeValue(Map<?, ?> value) {
        if (value.isEmpty()) {
            this.json.append("{}");
            return ;
        }

        this.json.append('{');
        Iterator<? extends Map.Entry<?, ?>> iter = value.entrySet().iterator();
        if (iter.hasNext()) {
            Map.Entry<?, ?> first = iter.next();
            this.writeName(first.getKey());
            this.writeValue(first.getValue());
        }

        while (iter.hasNext()) {
            Map.Entry<?, ?> entry = iter.next();
            this.writeDelimiter();
            this.writeName(entry.getKey());
            this.writeValue(entry.getValue());
        }
        this.json.append('}');
    }

    void writeValue(Collection<?> value) {
        if (value.isEmpty()) {
            this.json.append("[]");
            return;
        }

        this.json.append('[');
        Iterator iter = value.iterator();
        if (iter.hasNext()) {
            this.writeValue(iter.next());
        }

        while (iter.hasNext()) {
            this.writeDelimiter();
            this.writeValue(iter.next());
        }
        this.json.append(']');
    }

    void writeValue(String value) {
        this.json.append('"');
        this.json.append(value);
        this.json.append('"');
    }



    void writeValue(Number value) {
        this.json.append(value);
    }

    void writeArray(Object value) {
        this.json.append('[');
        int length = Array.getLength(value);
        if (length > 0) {
            Object o = Array.get(value, 0);
            this.writeValue(o);
        }

        for (int i=1; i<length; i++){
            Object o = Array.get(value, i);
            this.writeDelimiter();
            this.writeValue(o);
        }
        this.json.append(']');
    }

    void writeValue(Iterable<?> value) {
        this.json.append('[');
            for (Object o: value){
                this.writeValue(o);
            }
        this.json.append(']');
    }

    void writeBean(Object value) throws InvocationTargetException {
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
                    this.writePair(property, res);
                } catch (InvocationTargetException ite) {

                } catch (IllegalAccessException iae) {

            }


            }

        }
        this.objectEnd();
    }



    String build() {
        return this.json.toString();
    }

    public String toString() {
        return this.json.toString();
    }


}
