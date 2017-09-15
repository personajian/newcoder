package cn.edu.seu.itcompany.neteasy0;

import java.util.*;

public class Test {

	private static LinkedList<Integer> s1 = new LinkedList<>();
	private static LinkedList<Integer> s2 = new LinkedList<>();

	private static int ans;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			int n = in.nextInt();
			int[] arr = new int[n];

			for (int i = 0; i < n; i++) {
				arr[i] = in.nextInt();
			}

			ans = solve(arr);

			System.out.println(ans);

		}
		in.close();

	}

	private static int solve(int[] arr) {

		int ans1 = 0, ans2 = 0, temp1, temp2, temp, remain1_0, remain2_0, remain1_2;
		
		int pop1,pop2;
		
		s1.push(arr[0]);
		s2.push(arr[1]);
		for (int i = 2; i < arr.length; i++) {
			temp1 = s1.peek();
			temp2 = s2.peek();
			temp = arr[i];
			remain1_0 = Math.abs(temp1 - temp);
			remain2_0 = Math.abs(temp2 - temp);
			remain1_2 = Math.abs(temp1 - temp2);
			if (remain1_0 <= remain2_0 && remain1_0 <= remain1_2)
				s1.push(temp);
			else if (remain2_0 < remain1_0 && remain2_0 <= remain1_2)
				s2.push(temp);

			else {
				s1.push(s2.pop());
				s2.push(temp);
			}
		}
		
		pop1 = s1.pop();
		pop2 = s2.pop();
		if (!s1.isEmpty()) {
			while (!s1.isEmpty()) {
				int t = s1.pop();
				ans1 += Math.abs(t - pop1);
				pop1 = t;
			}
		} else
			ans1 = pop1;
		if (!s2.isEmpty()) {
			while (!s2.isEmpty()) {
				int t = s2.pop();
				ans2 += Math.abs(t - pop2);
				pop2 = t;
			}
		} else
			ans2 = pop2;
		return ans1 + ans2;
	}

}
