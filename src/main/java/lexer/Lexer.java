package lexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class Lexer {
	public static int line = 1; /* 行号 */
	char peek = ' '; /* 下一个读入字符 */
	Hashtable<String, Word> words = new Hashtable<String, Word>(); /* 符号表 */
	// /* token集 */
	private List<String> table = new LinkedList<String>();/* token集 */
	private List<String> tokens = new LinkedList<String>(); /* token表 */
	private List<Error> errors = new LinkedList<Error>(); /* 报错表 */
	BufferedReader reader = null;
	private Boolean isEnd = false; /* 是否读取到文件的结尾 */
	private static int numlimit = 1 << 24; /* 数字上限 */
	private static int lengthlimit = 32; /* 标识符长度上限 */
	private static int lengthvalid = 8; /* 标识符有效长度 */

	public Boolean getReaderState() {/* 获取读入状态 */
		return this.isEnd;
	}

	/* 保存存储在table中的 */
	public void saveSymbolsTable() throws IOException {
		FileWriter writer = new FileWriter("符号表.txt");
		writer.write("[符号表]          [符号类型信息]\n");
		writer.write("\r\n");

		for (int i = 0; i < table.size(); ++i) {
			String w = table.get(i);
			/* 写入文件 */
			if (i > 120)
				writer.write("<" + w + ">	:			<id>" + "\r\n");
			else
				writer.write("<" + w + ">	:			<key>" + "\r\n");
		}

		writer.flush();
	}

	/* 保存Tokens */
	public void saveTokens() throws IOException {
		FileWriter writer = new FileWriter("Tokens表.txt");
		writer.write("[符号]  \n");
		writer.write("\r\n");

		for (int i = 0; i < tokens.size(); ++i) {
			String tok = tokens.get(i);

			// 写入文件
			writer.write("< " + tok + " >" + "\r\n");
		}

		writer.flush();
	}

	/* 保存 Errors */
	public void saveErrors() throws IOException {
		FileWriter ew = new FileWriter("errors.txt");
		ew.write("\r\n");

		for (int i = 0; i < errors.size(); ++i) {

			Error error = errors.get(i);
			/* 写入文件 */
			ew.write(error.writeError() + "\r\n");
		}

		ew.flush();
	}

	void reserve(Word w) { /* 保存单词到符号表 */
		words.put(w.lexme, w);
		table.add(w.lexme);
	}

	public Lexer() {
		try { /* 初始化读取文件变量 */
			reader = new BufferedReader(new FileReader("输入.txt"));
		} catch (IOException e) {
			System.out.print(e);
		}

		/* 关键字 */{
			this.reserve(Word.Absolute);
			this.reserve(Word.Abstract);
			this.reserve(Word.Array);
			this.reserve(Word.As);
			this.reserve(Word.Asm);
			this.reserve(Word.Assembler);
			this.reserve(Word.At);
			this.reserve(Word.Automated);
			this.reserve(Word.Begin);
			this.reserve(Word.Case);
			this.reserve(Word.Cdecl);
			this.reserve(Word.Class);
			this.reserve(Word.Const);
			this.reserve(Word.Constructor);
			this.reserve(Word.Contains);
			this.reserve(Word.Default);
			this.reserve(Word.Destructor);
			this.reserve(Word.Dispid);
			this.reserve(Word.Dispinterface);
			this.reserve(Word.Do);
			this.reserve(Word.Downto);
			this.reserve(Word.Dynamic);
			this.reserve(Word.Else);
			this.reserve(Word.End);
			this.reserve(Word.Except);
			this.reserve(Word.Export);
			this.reserve(Word.Exports);
			this.reserve(Word.External);
			this.reserve(Word.Far);
			this.reserve(Word.File);
			this.reserve(Word.Finalization);
			this.reserve(Word.Finally);
			this.reserve(Word.For);
			this.reserve(Word.Forward);
			this.reserve(Word.Function);
			this.reserve(Word.Goto);
			this.reserve(Word.If);
			this.reserve(Word.Implementation);
			this.reserve(Word.Implements);
			this.reserve(Word.In);
			this.reserve(Word.Index);
			this.reserve(Word.Inherited);
			this.reserve(Word.Initialization);
			this.reserve(Word.Inline);
			this.reserve(Word.Interface);
			this.reserve(Word.Is);
			this.reserve(Word.Label);
			this.reserve(Word.Library);
			this.reserve(Word.Message);
			this.reserve(Word.Name);
			this.reserve(Word.Near);
			this.reserve(Word.Nil);
			this.reserve(Word.Nodefault);
			this.reserve(Word.Object);
			this.reserve(Word.Of);
			this.reserve(Word.On);
			this.reserve(Word.Out);
			this.reserve(Word.Overload);
			this.reserve(Word.Override);
			this.reserve(Word.Package);
			this.reserve(Word.Packed);
			this.reserve(Word.Pascal);
			this.reserve(Word.Private);
			this.reserve(Word.Procedure);
			this.reserve(Word.Program);
			this.reserve(Word.Property);
			this.reserve(Word.Protected);
			this.reserve(Word.Public);
			this.reserve(Word.Published);
			this.reserve(Word.Raise);
			this.reserve(Word.Read);
			this.reserve(Word.Readonly);
			this.reserve(Word.Record);
			this.reserve(Word.Register);
			this.reserve(Word.Reintroduce);
			this.reserve(Word.Repeat);
			this.reserve(Word.Requires);
			this.reserve(Word.Resident);
			this.reserve(Word.Resourcestring);
			this.reserve(Word.Safecall);
			this.reserve(Word.Set);
			this.reserve(Word.Shl);
			this.reserve(Word.Shr);
			this.reserve(Word.Stdcall);
			this.reserve(Word.Stored);
			this.reserve(Word.String);
			this.reserve(Word.Then);
			this.reserve(Word.Threadvar);
			this.reserve(Word.To);
			this.reserve(Word.Try);
			this.reserve(Word.Type);
			this.reserve(Word.Unit);
			this.reserve(Word.Until);
			this.reserve(Word.Uses);
			this.reserve(Word.Var);
			this.reserve(Word.Virtual);
			this.reserve(Word.While);
			this.reserve(Word.With);
			this.reserve(Word.Write);
			this.reserve(Word.Writeonly);
		}
		/* 运算关键字 */{
			this.reserve(Operation.And);
			this.reserve(Operation.DivInt);
			this.reserve(Operation.Mod);
			this.reserve(Operation.Not);
			this.reserve(Operation.Or);
			this.reserve(Operation.Xor);
		}
		/* 类型 */{
			this.reserve(Type.Shortint);
			this.reserve(Type.Integer);
			this.reserve(Type.Longint);
			this.reserve(Type.Byte);
			this.reserve(Type.Word);
			this.reserve(Type.Dword);
			this.reserve(Type.Int64);
			this.reserve(Type.Qword);
			this.reserve(Type.Singer);
			this.reserve(Type.Real);
			this.reserve(Type.Double);
			this.reserve(Type.Extended);
			this.reserve(Type.Comp);
			this.reserve(Type.Char);
			this.reserve(Type.Boolean);
		}
	}

	public char bts(char p) {
		/* 如果peek是大写字母，则化为小写字母 */
		if (p >= 'A' && p <= 'Z') {
			p = (char) (p + 32);
		}
		return p;
	}

	public void readch() throws IOException {
		peek = (char) reader.read();
		peek = bts(peek);
		if ((int) peek == 0xffff) {
			this.isEnd = true;
		}
	}

	public Boolean readch(char ch) throws IOException {
		readch();
		if (this.peek != ch) {
			return false;
		}
		this.peek = ' ';
		return true;
	}

	/* 清除空白 */
	public void clear() throws IOException {
		for (;; readch()) {
			if (peek == ' ' || peek == '\t' || peek == '\r')
				continue;
			else if (peek == '\n')
				line++;
			else
				return;
		}
	}

	/* 取数字 */
	public Token digitGet() throws IOException {
		int value = 0;
		int mult = 10;
		double v = 0.0;
		double mu = 0.1;
		boolean isReal = false;
		if (peek == '0') {
			if (readch('x')) {
				readch();
				mult = 36;
			} else if (peek == '.') {
				isReal = true;
			} else if (!Character.isDigit(peek)) {
				Num n = new Num(value);
				tokens.add(n.toString());
				return n;
			}
		}
		if (!isReal) {
			do {
				value = mult
						* value
						+ (Character.isDigit(peek) ? (peek - '0')
								: (peek - 'a' + 10));
				readch();
			} while (Character.isLetterOrDigit(peek));
			if (value >= numlimit) {
				Error error = new Error(line, "整数越界");
				errors.add(error);
				System.out.println("at "+line+" line,  error: 整数越界;");
				return null;
			}
		}
		if (isReal || peek == '.') {
			readch();
			do {
				v = v + mu * (peek - '0');
				mu = mu * 0.1;
				readch();
			} while (Character.isDigit(peek));
		}
		if (v == 0.000000) {
			Num n = new Num(value);
			tokens.add(n.toString());
			return n;
		} else {
			Real r = new Real(v + value);
			tokens.add(r.toString());
			return r;
		}
	}

	public Token wordGet() throws IOException { /* 取词 */
		StringBuffer sb = new StringBuffer();
		do {
			sb.append(peek);
			readch();
		} while (Character.isLetterOrDigit(peek));

		String s = sb.toString();
		if (s.length() >= lengthlimit) {
			Error error = new Error(line, "标识符长度超过限制");
			System.out.println("at "+line+" line,  error: 标识符长度超过限制;");
			errors.add(error);
			s = "";
		}
		if (s.length() >= lengthvalid) {
			s = s.substring(0, lengthvalid);
			System.out.println("at "+line+" line,  warning: 标识符长度超过合法长度;");
		}
		Word w = words.get(s);
		if (w != null) { /* 词存在 */
			tokens.add(w.toString());
		} else {
			w = new Word(s, Tag.ID);
			if (!s.equals("")) {
				tokens.add("id," + w.toString());
				table.add(w.lexme);// 加入符号表
			} else {
				return null;
			}
			words.put(s, w);
		}
		return w;
	}

	private boolean isOperation(char peek) {
		return peek == '+' || peek == '-' || peek == '*' || peek == '/'
				|| peek == '>' || peek == '<' || peek == '=' || peek == ':';
	}

	/* 取运算符 */
	private Token operationGet() throws IOException {
		switch (peek) {
		case ':':
			if (readch('='))
				return Operation.Asn;
			else
				return Operation.Col;
		case '+':
			readch();
			return Operation.Plus;
		case '-': {
			if (readch('-')) { /* 清除注释 */
				while (peek != '\n' && (int) peek != 0xffff)
					readch();
				// return Word.Note;
				return null;
			} else
				return Operation.Minus;
		}
		case '*':
			readch();
			return Operation.Mult;
		case '/':
			readch();
			return Operation.DivReal;
		case '=':
			readch();
			return Operation.Eq;
		case '>':
			if (readch('='))
				return Operation.Ge;
			else
				return Operation.Gr;
		case '<':
			if (readch('='))
				return Operation.Le;
			else if (peek == '>') { // peek值已经改变
				peek = ' ';
				return Operation.Ne;
			} else
				return Operation.Ls;
		}
		return null;
	}

	public Token scan() throws IOException {

		/* 清除空白 */
		clear();
		// System.out.println(peek);

		/* 运算符识别 */
		if (isOperation(peek)) {
			Token t = operationGet();
			if(t!=null)tokens.add(t.toString());
			return t;
		}

		/* 整数float数识别 */
		if (Character.isDigit(peek)) {
			return digitGet();
		}

		/* 关键字&标识符识别 */
		if (Character.isLetter(peek)) {
			return wordGet();
		}

		/* 单字符词素 */
		Token tok = new Token(peek);
		if ((int) peek != 0xffff)
			tokens.add(tok.toString());
		peek = ' ';
		return tok;
	}
}
