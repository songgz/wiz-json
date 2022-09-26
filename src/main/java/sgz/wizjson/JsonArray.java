package sgz.wizjson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JsonArray extends ArrayList<Object> {



    public JsonArray(Collection<?> value) {

        this.addAll(value);
    }

    public JsonArray(Object value) {
        this.addAll(Arrays.asList(value));
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
//        if (this.json.size() > 0) {
//            builder.append('[');
//            this.json.forEach((a) -> {
//                if (builder.charAt(builder.length()-1) != '['){
//                    builder.append(',');
//                }
//                builder.append(a);
//            });
//            builder.append(']');
//        }
        return builder.toString();
    }




}
