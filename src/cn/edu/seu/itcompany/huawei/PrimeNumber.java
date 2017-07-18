package cn.edu.seu.itcompany.huawei;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PrimeNumber {
	
	public static String getResult(long ulDataInput) {
		
		List<Integer> primes=new LinkedList<Integer>();
		
		String str="";
		
		for(int number=2;number<=ulDataInput;number++) {
			boolean flag=true;
		    for (int d = 2; (d * d) < (number + 1); d++){
		        if (number%d==0) {
		        	flag=false;
		        	break;
		        }
		     }
		    
		    if(flag) primes.add(number);
		}		
				
		for(int i=0;i<primes.size();) {
			if(ulDataInput%primes.get(i)==0) {
				str+=primes.get(i)+" ";
				ulDataInput=ulDataInput/primes.get(i);
				if(ulDataInput==0) break;
			}else i++;
		}
			
		return str;
	}

	public static void main(String[] args) {
		Scanner in= new Scanner(System.in);
		while(in.hasNext()){
			long ulDataInput=in.nextLong();
			System.out.println(getResult(ulDataInput));
		}
		in.close();
	}
	
}
