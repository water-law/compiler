package parser;

/* 
 * 主程序 
 */
import java.io.*;
import java.lang.reflect.Field;

import lexer.*;

public class Main {
	public static void main(String[] args) throws IOException{
		Lexer lexer = new Lexer();

		while (lexer.getReaderState() == false) {
			lexer.scan();
		}

		/* 保存相关信息 */
		lexer.saveTokens(); //
		lexer.saveSymbolsTable(); //
		lexer.saveErrors();//

	}
}
