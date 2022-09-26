package sgz.wizjson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("test JsonWriter class")
public class JsonWriterTest {
    @Test
    void arrayTest() {
        JsonWriter json = new JsonWriter();
        int[] a = {1,2,3};
        json.writeArray(a);
        System.out.println(json);

        JsonWriter json1 = new JsonWriter();
        Integer[] a1 = {1,2,3};
        json1.writeArray(a1);
        System.out.println(json1);

    }
}
