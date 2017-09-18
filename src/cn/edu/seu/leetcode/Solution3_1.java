package cn.edu.seu.leetcode;

import java.util.HashMap;

/**
 * 最长无重复子串
 * 1. 利用HashMap来记录已经访问的字符出现过没有；
 * 若没出现过，继续i++
 * 若出现过，Longest就是当前hashmap.sizr()，并清空hashmap，从i位置重新开始
 *
 * @Author personajian
 * @Date 2017/9/18 15:09
 */
public class Solution3_1 {

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(new Solution3_1().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        int longest = Integer.MIN_VALUE;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {//若map中已经有了该字符，就更新longest，以及下标为重复字符的位置，并清空map
                longest = Math.max(map.size(), longest);
                i = map.get(ch);
                map.clear();
            } else {//若map中没有此字符，就继续添加(字符,下标)
                map.put(ch, i);
            }
        }

        longest = Math.max(map.size(), longest);
        return longest;
    }
}