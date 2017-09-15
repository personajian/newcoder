package cn.edu.seu.itbook.algorithm4.search;

public class Key implements Comparable<Key> {

	private int key;

	@Override
	public int compareTo(Key key) {
		int differ = this.key - key.key;
		if (differ > 0)
			return 1;
		else if (differ < 0)
			return -1;
		return 0;

		// return this.key>key.key?1:-1;
	}
}
