package LRU;
//https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
public class Node {
    public int key;
    public int val;
    public Node next,prev;
    public Node(int key,int val){
        this.key = key;
        this.val = val;
    }
}


