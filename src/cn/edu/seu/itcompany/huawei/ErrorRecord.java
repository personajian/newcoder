package cn.edu.seu.itcompany.huawei;

import java.io.File;
import java.util.Scanner;

/**
 * @author personajian
 * E:\V1R2\product\fpgadrive.c   1325
 * fpgadrive.c 1325 1
 */
public class ErrorRecord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String path=in.next();
			int errorLine=in.nextInt();
			File file=new File(path);
			String filename=file.getName();
		}
		in.close();
	}

	
}


