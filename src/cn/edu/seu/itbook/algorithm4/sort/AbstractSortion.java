package cn.edu.seu.itbook.algorithm4.sort;


public abstract class AbstractSortion {

	public static void sort(Comparable[] a) {}
	
	
	public static boolean less(Comparable v,Comparable w) {
		return v.compareTo(w)<0;
	}
	
	public static void exch(Comparable[] a, int i,int j) {
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	
	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in =new Scanner(System.in);
		int[] original = {3,1,2,8,6,9,5,4,0};
		Integer[] a=new Integer[10];
		for (int i = 0; i < a.length; i++) {
			a[i]=original[i];
		}
		while(in.hasNext()) {
			Integer a=in.nextInt();
		}
		in.close();
		sort(a);
		System.out.println(isSorted(a));
		show(a);
	}
*/
}
