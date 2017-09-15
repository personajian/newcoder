package cn.edu.seu.itcompany.alibaba.ioc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestReadFile {

	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\dev\\workspace\\idea\\newcoder\\src\\cn\\edu\\seu\\itcompany\\alibaba\\ioc\\ioc.txt");

		//InputStreamReader in=new InputStreamReader(new FileInputStream(file));
		
		BufferedReader in=new BufferedReader(new FileReader(file));

		
		//ileInputStream in = new FileInputStream(file);

		String beanName = in.readLine();
		System.out.println(beanName);
	}

}
