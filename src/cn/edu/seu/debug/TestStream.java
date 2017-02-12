package cn.edu.seu.debug;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author personajian
 *
 */
public class TestStream {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file=new File("a.txt");//相对路径是相对与项目的根路径(开头没有/)。
		InputStream is = new FileInputStream(file);
		InputStream in = TestStream.class.getResourceAsStream("/cn/edu/seu/debug/a.txt");
		System.out.println((char)is.read());
		System.out.println((char)in.read());
		is.close();
		in.close();
		File file2= new File("b.txt");
		fileCopy(file,file2);
	}

	/**
	 * 利用简单的I/O流完成文件的拷贝
	 * @param source
	 * @param distinct
	 * @throws Exception
	 */
	public static void fileCopy(File source, File distinct) throws Exception {
		// TODO Auto-generated method stub
/*		InputStream in = TestStream.class.getResourceAsStream("/cn/edu//seu/debug/"+source);
		OutputStream out = TestStream.class.getResourceAsStream("/cn/edu//seu/debug/"+source);*/
		InputStream in= new FileInputStream(source);
		OutputStream out= new FileOutputStream(distinct);
		byte[] buffer= new byte[1024];
		int count;
		while((count=in.read(buffer))!=-1){
			System.out.println(buffer);
			System.out.println(count);
			out.write(buffer,0,count);
			//reader=in.read(b);
		}
		out.close();
		in.close();

		
	}
	
	
	
}
