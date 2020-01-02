
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        System.out.println("-------测试------");
        //HashMapDemo();
       // DequeDemo();
    }

    public static void HashMapDemo(){
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        map.put("aaa",1);
        map.put("bbb",2);
        //map.putMapEntries(map,true);
        System.out.println(map);
    }

    public static void DequeDemo(){
        Deque<String> deque = new ArrayDeque<String>() ;
        deque.add("a");
        deque.add("b");
        deque.addFirst("c");
        System.out.println(deque);

    }

    public static void PriorityQueueDemo(){
        Queue<String> queue = new PriorityQueue<String>();
        queue.add("a");
        queue.add("b");
        System.out.println(queue);
    }
}
