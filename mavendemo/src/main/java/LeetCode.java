import java.util.HashMap;
import java.util.Map;

public class LeetCode {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] s = twoSum(nums, 18);
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]+",");
        }

    }

    //两数之和 https://leetcode-cn.com/problems/two-sum/
    public static int[] twoSum(int[] nums, int target) {
        //哈希法
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<nums.length;i++){
            Integer val = target-nums[i];
            if (map.containsKey(val)){
                return new int[]{ map.get(val),i };
            }
            map.put(nums[i],i);
        }
        return null;
    }
}
