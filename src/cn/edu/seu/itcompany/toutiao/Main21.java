package cn.edu.seu.itcompany.toutiao;

import java.util.*;

public class Main21 {
    public static List<List<Integer>> getSubSets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        track(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void track(List<List<Integer>> list , List<Integer> temp, int[] nums, int begin){
        list.add(new ArrayList<>(temp));
        for(int i = begin; i < nums.length; i++){
            temp.add(nums[i]);
            track(list, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static int getMinOfSet(List<Integer> nums){
        int min = Integer.MAX_VALUE;
        for(int num : nums){
            if(num < min)
                min = num;
        }
        return min;
    }

    public static int getSumOfSet(List<Integer> nums){
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int length = in.nextInt();
            int[] array = new int[length];
            for(int i = 0; i < length;i++){
                array[i] = in.nextInt();
            }

            List<List<Integer>> sets = getSubSets(array);
            List<Integer> result = new ArrayList<>();
            for(List<Integer> list : sets){
                if(null != list){
                    result.add(getMinOfSet(list) * getSumOfSet(list));
                }
            }

            Integer max = Collections.max(result);
            System.out.println(max);
        }
        in.close();

    }
}
