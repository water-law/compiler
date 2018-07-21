package lexer;

import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;

public class InputTest {

	
	@Test
	public void testGetList() throws IOException {
		Input in = new Input();
		LinkedList<String> tList = in.getList();
		for(int i = 0 ; i < tList.size();i++){
			System.out.println(tList.get(i));
		}
	}

}
