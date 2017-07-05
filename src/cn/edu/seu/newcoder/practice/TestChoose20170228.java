package cn.edu.seu.newcoder.practice;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestChoose20170228 {

	@Test
	public void Mathround() {
		System.out.println(Math.round(11.5));
	}

	@Test
	public void test1() {
		String a = "1234";
		String b = "1234";
		String c = new String("1234");
		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(a.equals(c));
	}

}
