import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class JunitTest {

	@Test
	public void testExceptionFinallyReturn() {
		try{
			System.out.println("try 代码块...");
			//return;
			throw new IOException();
		}catch(Exception e){
			System.out.println("catch 代码块...");
		}finally {
			System.out.println("finally 代码块...");
		}
		//fail("Not yet implemented");
	}

}
