package cn.edu.seu.newcoder.itcompany.huawei;

public class SpecialOffer23 {
	public static void main(String[] args) {
		change(".-.#-.-#.#.");
	}
	
	private static String[] tables = { "F", "G", "R", "S", "T", "L", "M", "N", "O", "P", "Q", "W", "X", "Y", "Z", "U",
			"A", "G", "H", "I", "J", "K", "B", "C", "D", "E", "l", "m", "n", "o", "p", "i", "j", "k", "f", "g", "h",
			"a", "b", "c", "d", "e", "q", "r", "w", "x", "y", "z", "s", "t", "u", "v" };

	public static void change(String s) {
		if (s == null || s.length() == 0)
			return;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c != '-' && c != '.' && c != '#') {
				return;
			}
		}
		String[] strs = s.split("#");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			String tmp = changeString(strs[i]);
			if (tmp.equals(""))
				continue;
			int index = 0, count = 0;
			;
			for (int j = tmp.length() - 1; j >= 0; j--) {
				if (tmp.charAt(j) == '1') {
					index += Math.pow(2, count);
				}
				count++;
			}
			if (index >= tables.length) {
				System.out.println("ERROR");
				return;
			}
			sb.append(tables[index]);
		}
		System.out.println(sb.toString());
	}

	private static String changeString(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-')
				sb.append("0");
			else
				sb.append("1");
		}
		return sb.toString();
	}

}


