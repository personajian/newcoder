package cn.edu.seu.leetcode;

import java.util.HashMap;

public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];
        int differ = 0;
        for (int i = 0; i < nums.length; i++) {
            differ = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (differ == nums[j]) {
                    index[0] = i;
                    index[1] = j;
                    break;
                }
            }
            //if(nums[index[0]]+nums[index[1]]==target) break;
        }
        return index;
    }

    public int[] twoSum1(int[] nums, int target) {
        int[] index = new int[2];
        //Arrays.sort(nums);
        int begin = 0;
        int end = nums.length - 1;
        while (begin < end) {
            int sum = nums[begin] + nums[end];
            if (sum == target) {
                index[0] = begin;
                index[1] = end;
                break;
            } else {
                if (sum < target)
                    begin++;
                else
                    end--;
            }
        }
        return index;
    }

    /**2sum哈希法
     * @Param
     * @Return
     */
    public int[] twoSum2(int[] nums, int target) {
        int[] index=new int[2];

        HashMap<Integer,Integer> map=new HashMap<>();

        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            int differ=target-nums[i];
            if(map.containsKey(differ)&&i!=map.get(differ)){
                index[0]=i;
                index[1]=map.get(differ);
                break;
            }
        }

        return index;

    }

        public static void main(String[] args) {
        Solution1 solution = new Solution1();
        int[] nums = {3, 4, 2};
        int target = 6;
        int[] index = new int[2];
        //index = solution.twoSum(nums, target);
        //index = solution.twoSum1(nums, target);
        index = solution.twoSum2(nums, target);
        for (int i : index) {
            System.out.println(i);
        }
    }
}
