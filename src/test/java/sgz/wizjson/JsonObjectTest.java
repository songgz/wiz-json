package sgz.wizjson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@DisplayName("test JsonObject class")
public class JsonObjectTest {

    enum Color
    {
        RED, GREEN, BLUE;
    }

    @Test
    @DisplayName("test serialize method")
    void serializeTest() {
        HashMap<String, Object> o = new HashMap<>();
        o.put("name","sgz");
        List<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add("ii");
        JsonObject jo = new JsonObject(o);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ss", "my is");
        map.put("uu", o);
        map.put("aa", list);
        map.put("ints", new int[]{1,2,3});
        JsonObject j = Json.generate(map);
        System.out.println(j.toString());

    }

    @Test
    @DisplayName("test toString method")
    void toStringTest() {
        List<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add("ii");
        JsonArray json = Json.generate(list);
        System.out.println(json);

    }

    @Test
    void arrayTest() {

        HashMap<Object, Object> map1 = null;
        HashMap<Object, Object> map = new HashMap<>();
        int[] a = new int[]{1,2,3};
        map.put("ar", a);
        map.put("b", true);
        map.put("ar0", "dkjfdk");
        map.put("byte", (char) 60);
        map.put("null1", Color.BLUE);
        map.put("ar1", 1);
        map.put("ar2", 1.08);
        map.put("ar3", 76767677788888L);

        JsonBuilder jb = new JsonBuilder();
        jb.writeValue(map);

        System.out.println(jb);

    }

    @Test
    void pojoObjectTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User user = new User();
        user.setAge(18);
        user.setName("jack");
        user.setGender("男人");

        JsonBuilder jb = new JsonBuilder();
        jb.writeBean(user);
        System.out.println(jb);

//        for (Method m: Object.class.getMethods()) {
//            System.out.println(m.getName());
//        }

    }

    @Test
    void demoTest() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ 1 u");

        char c = ' ';
        for (int i = 1; i < sb.length(); i++) {
            c = sb.charAt(sb.length() - i);
            System.out.println(c);
            if (c != ' ' && (c == '{' || c == '[')) {


            }

        }
        System.out.println(sb.charAt(sb.length() - 1));

    }
}
