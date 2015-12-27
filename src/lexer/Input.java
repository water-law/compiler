package lexer;

import java.io.IOException;
import java.util.LinkedList;

public class Input {
	private LinkedList<String> inputStack;

	public Input() {
		inputStack = new LinkedList<String>();
	}
	
	public LinkedList<String> getList() throws IOException {

		Lexer lexer = new Lexer();
		boolean iskey;

		// 0xffff是EOF
		while (lexer.getReaderState() == false) {//(int) lexer.peek != 0xffff
			Token token = lexer.scan();
			if (token != null) {
				if (token instanceof Word) {
					if (token.tag != Tag.ID) {
						iskey = true;
					} else {
						iskey = false;
					}
					inputStack.add(((Word) token).getString(iskey));
					//System.out.println(((Word) token).getString(iskey));
				} else if (token instanceof Num) {
					inputStack.add(token.getString());
				} else if (token instanceof Real) {
					inputStack.add(token.getString());
				} else {
					// 屏蔽换行和回车的ASCII码值，10和13
					if (token.tag != 13 && token.tag != 10) {
						inputStack.add(token.getString());//这句话有问题
					}
				}
			}
		}
		lexer.saveSymbolsTable();
		// while (!inputStack.isEmpty()) {
				// System.out.println(inputStack.pop());
				// }
		inputStack.removeLast();
		inputStack.add("$");
		return inputStack;
	}

}
