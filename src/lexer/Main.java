package lexer;

/* 
 * 主程序 
 */
import java.io.*;
import java.lang.reflect.Field;

import lexer.*;

public class Main {
	public static void main(String[] args) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		Lexer lexer = new Lexer();

		while (lexer.getReaderState() == false) {
			lexer.scan();
		}

		/* 保存相关信息 */
		lexer.saveTokens(); //
		lexer.saveSymbolsTable(); //
		lexer.saveKeywords();//
		lexer.saveErrors();//
		char peek = ' ';
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("test.txt"));
		} catch (IOException e) {
			System.out.print(e);
		}
		peek = (char) reader.read();
		System.out.println(peek);
		if (peek == 'z') {
			peek = (char) reader.read();
			System.out.println(peek);
		}

		Integer a = 129, b = 129;
		System.out.println(a == b);// 1
		Integer c = 100, d = 100;
		System.out.println(c == d);// 2
		Class cache = Integer.class.getDeclaredClasses()[0]; // 1
		Field myCache = cache.getDeclaredField("cache"); // 2
		myCache.setAccessible(true);// 3

		Integer[] newCache = (Integer[]) myCache.get(cache); // 4
		System.out.println(newCache[133]);
		System.out.println(newCache[0]);
		// System.out.println(newCache.);
		newCache[132] = newCache[133]; // 5
		System.out.println(newCache.length);
		int a1 = 2;
		int b1 = a1 + a1;
		System.out.printf("%d + %d = %d", a1, a1, b1); //
		System.out.println(1000 == 1000);
	}
}
