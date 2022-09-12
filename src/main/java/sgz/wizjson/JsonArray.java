package sgz.wizjson;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends ArrayList<Object> {

    public JsonArray(List<Object> value) {

        this.addAll(value);
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
