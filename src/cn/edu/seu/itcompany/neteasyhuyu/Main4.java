package cn.edu.seu.itcompany.neteasyhuyu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] cand = new int[n];
            for (int i = 0; i < n; i++) {
                cand[i] = in.nextInt();
            }
            int target = in.nextInt();
            List<List<Integer>> lists = combinationSum2(cand, target);
            int max = 0;
            if (lists.size() == 0) System.out.println(-1);
            else {
                for (List<Integer> list : lists) {
                    int sum = 1;
                    for (int i : list) {
                        sum *= i;
                    }
                    max = Math.max(sum, max);
                }
                System.out.println(max);

            }
        }
        in.close();

    }

    //回溯法，深度优先搜索  2的n次方的时间复杂度
    public static List<List<Integer>> combinationSum2(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        dfs_com(cand, 0, target, path, res);
        return res;
    }

    public static void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(path));
            return;
        }
        if (target < 0) return;
        for (int i = cur; i < cand.length; i++) {
            if (i > cur && cand[i] == cand[i - 1]) continue;
            path.add(path.size(), cand[i]);
            dfs_com(cand, i + 1, target - cand[i], path, res);
            path.remove(path.size() - 1);
        }
    }
}

