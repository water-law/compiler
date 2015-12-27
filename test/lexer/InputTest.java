package lexer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import lexer.Input;
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
