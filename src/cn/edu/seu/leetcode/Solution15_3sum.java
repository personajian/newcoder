package cn.edu.seu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution15_3sum {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> lists=new ArrayList<>();

        int zero = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int target = zero - nums[i];

            for (int j = i; j < nums.length;j++){
                if (map.containsKey(nums[j])){
                    int repeat=map.get(nums[j]);
                    map.put(nums[j],++repeat);
                }
                else
                    map.put(nums[j],1);
            }

            for(int j=i;j<nums.length;j++){
                int differ=target-nums[j];
                if(map.containsKey(differ)){
                    ArrayList<Integer> list=new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(differ);
                    lists.add(list);
                }

            }
        }
        return lists;

    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> threeSum = new Solution15_3sum().threeSum(nums);

        for(List list:threeSum){
            for (Object i:list)
                System.out.print((Integer)i);
            System.out.println();
        }
    }
}