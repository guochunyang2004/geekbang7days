import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        System.out.println("-------测试------");
        HashMapDemo();
    }

    public static void HashMapDemo(){
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        map.put("aaa",1);
        map.put("bbb",2);
        map.putMapEntries(map,true);
        System.out.println(map);
    }
}
