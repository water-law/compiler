package lexer;
/*
 * 完成词法分析功能的类，数据成员line为行号，peek是向前的一个字符，words是符号表。
 * 成员函数reserve将给定记号加入符号表。它的构造函数将所有保留字加入符号表。
 */
import java.io.*;  
import java.util.*;  

import symbols.*;  
  
public class Lexer {  
    public static int line = 1;     /* 记录行号 */  
    char peek = ' ';        /* 下一个读入字符 */  
    Hashtable<String, Word> words =   
        new Hashtable<String, Word>();  
    /* 符号表 */  
    private Hashtable<Token, String> table =   
        new Hashtable<Token, String>();  
    /* token序列 */  
    private List<String> tokens =   
        new LinkedList<String> ();  
    /* errors序列 */
    private List<Error> errors =   
            new LinkedList<Error> ();  
    /* 读取文件变量 */  
    BufferedReader reader = null;   
    /* 保存当前是否读取到了文件的结尾  */  
    private Boolean isEnd = false;  
      
    /* 是否读取到文件的结尾 */  
    public Boolean getReaderState() {  
        return this.isEnd;  
    }  
      
    /* 保存存储在table中的 */  
    public void saveSymbolsTable() throws IOException {  
        FileWriter writer = new FileWriter("符号表.txt");  
        writer.write("[符号]          [符号类型信息]\n");  
        writer.write("\r\n");  
          
        Enumeration<Token> e = table.keys();  
        while( e.hasMoreElements() ){  
            Token token = (Token)e.nextElement();  
            String desc = table.get(token);  
              
             /* 写入文件 */ 
            writer.write(token + "\t\t\t" + desc + "\r\n");  
        }  
              
        writer.flush();  
    }  
      
    /* 保存 Errors*/
    public void saveErrors() throws IOException{           //	待写
    	FileWriter ew = new FileWriter("errors.txt");  
       // ew.write("[符号]          [符号类型信息]\n");  
        ew.write("\r\n");  
          
        for(int i = 0; i < errors.size(); ++i) {  
        	//int er = (int)errors.get(i).line;
           // String err = (String)errors.get(i).error;  
            Error error = errors.get(i);
            /* 写入文件 */  
            ew.write(error.writeError() + "\r\n");  
        }     
              
        ew.flush();  
    }
    /* 保存Tokens */  
    public void saveTokens() throws IOException {  
        FileWriter writer = new FileWriter("Tokens表.txt");  
        writer.write("[符号]  \n");  
        writer.write("\r\n");  
          
        for(int i = 0; i < tokens.size(); ++i) {  
            String tok = (String)tokens.get(i);  
              
            /* 写入文件 */  
            writer.write(tok + "\r\n");  
        }     
              
        writer.flush();  
    }  
      
    void reserve(Word w) {  
        words.put(w.lexme, w);  
    }  
      
    /* 
     * 构造函数中将关键字和类型添加到hashtable words中 
     */  
    public Lexer() {  
        /* 初始化读取文件变量 */  
        try {  
            reader = new BufferedReader(new FileReader("输入.txt"));  
        }  
        catch(IOException e) {  
            System.out.print(e);  
        }  
          
          
        /* 关键字 */  
        this.reserve(new Word("if", Tag.IF));  
        this.reserve(new Word("then", Tag.THEN));  
        this.reserve(new Word("else", Tag.ELSE));  
        this.reserve(new Word("while", Tag.WHILE));  
        this.reserve(new Word("do", Tag.DO));  
        //关键字用于分割关键字时使用，需添加相应Tag值
        this.reserve(new Word("begin", Tag.BEGIN));
        this.reserve(new Word("end", Tag.END));
        
        /* 类型 */  
        this.reserve(Word.True);  
        this.reserve(Word.False);  
        this.reserve(Type.Int);  
        this.reserve(Type.Char);  
        this.reserve(Type.Bool);  
        this.reserve(Type.Float);  
    }  
      
    public void readch() throws IOException {  
        /* 这里应该是使用的是 */  
        peek = (char)reader.read();  
        if((int)peek == 0xffff){  
            this.isEnd = true;  
        }  
        // peek = (char)System.in.read();  
        peek = bts(peek);
    }  
      
    public Boolean readch(char ch) throws IOException {  
        readch();  
        if (this.peek != ch) {  
            return false;  
        }  
          
        this.peek = ' ';  
        return true;  
    }  
    public char bts(char p){
    	/* 如果peek是大写字母，则化为小写字母 */
        if(p>='A'&&p<='Z'){
        	p =(char) (p+32);
        }
        return p;
    }
    //判断关键字
    public boolean isKeyWord(Word w){
    	if(w.lexme.equals("if")||w.lexme.equals("then")||w.lexme.equals("while")
    			||w.lexme.equals("do")||w.lexme.equals("else")||w.lexme.equals("end")
    			||w.lexme.equals("begin"))
    		return true;
    	else
    	return false;
    }
    /* 十进制数转为二进制数，二进数的位数减1 */
    public int c10t2(int x){
    	int count = 0 ;
    	while((x=x/2)>0){
    		count++;
    	}
    	return count;
    }
    public Token scan() throws IOException {  
        /* 消除空白 */   
        for( ; ; readch() ) {  
            if(peek == ' ' || peek == '\t')  
                continue;  
             /* 消除注释  */
            else if(peek=='-'&&readch('-')){
            	//[]--bjgnb  
            	for(;!readch('\n');readch()){
            		continue;
            	}
            	line++;
            }
            else if (peek == '\n')   
                line = line + 1;  
            else  
                break;  
        }  
        
        /* 下面开始分割关键字，标识符等信息    */
        switch (peek) {  								
        /* 对于 :=, <>, >, <, >=, <=, 的区分使用状态机实现 */  
        case ':' :  
            if (readch('=')) {  
            	tokens.add("<:=>");  
                return new Token('=');   
            }  
            else {  
                tokens.add("<:>");  
                return new Token(':');  
            }  
        case '>' :  
            if (readch('=')) {  
                tokens.add("<>=>");  
                return Word.ge;  
            }  
            else {  
                tokens.add("<>>");  
                return new Token('>');  
            }  
        case '<' :  								//此处有问题
            if (readch('=')) {  
                tokens.add("<<=>");  
                return Word.le;  
            }  
            else if(readch('>')){
            	tokens.add("<<>>"); 
                return Word.ne; 
            }
            else {  //
                tokens.add("<<>");  
                return new Token('<');  
            }  
        case ';':
        	readch();
        	tokens.add("<;>");
        	return new Token(';');
        case '(':
        	readch();
        	tokens.add("<(>");
        	return new Token('(');
        case ')':
        	readch();
        	tokens.add("<)>");
        	return new Token(')');
        case '-':					//有问题
        	if (!readch('-')){
        	tokens.add("<->");
        	return new Token('-');
        	}
        }
        /* 下面是对数字的识别，根据文法的规定的话，这里的 
         * 数字只要是能够识别整数就行. 
         */  
        if(Character.isDigit(peek)) {  
            int value = 0;  
            /* 整数以“0x”开头,则按三十六进制数处理 */
            if(peek=='0'&&readch('x')){
            	readch();
            	do{
            		value = 36 * value + Character.digit(peek, 36);  
                    readch();
            	}while (Character.isLetterOrDigit(peek));
            }
            else if(peek=='0'){
            	readch();
            	if(!Character.isDigit(peek)){
            		value=0;
            	}
            }
            else {
            	do {  
            		value = 10 * value + Character.digit(peek, 10);  
            		readch();  
            	} while (Character.isDigit(peek));  
            }
            if(c10t2(value)>=24){ 				//value大于Math.pow(2.0,24)报整数越界错误;
            	//
            	Error e = new Error(line,"整数越界");
            	errors.add(e);
            }
            Num n = new Num(value);  			
            tokens.add(n.toString());      
            //table.put(n, "Num");  
            return n;  
        }  
          
        /* 
         * 关键字或者是标识符的识别 
         */  
        if(Character.isLetter(peek)) {  
            StringBuffer sb = new StringBuffer();  
              
            /* 首先得到整个的一个分割 */  
            do {  
                sb.append(peek);  //连接一个字符/字符串到末尾
                readch();  
            } while (Character.isLetterOrDigit(peek));  
              
            /* 判断是关键字还是标识符 */  
            String s = sb.toString();  //将对象转为String型
            Word w = (Word)words.get(s);  
              
            /* 如果是关键字或者是类型的话，w不应该是空的 */  
            /*if(w != null&& w.lexme=="if") {  //
                // table.put(w, "KeyWord or Type");  
                tokens.add(w.keyWordtoString());  
                return w;  说明是关键字 或者是类型名 
            }*/
            if(w != null) {  //
                // table.put(w, "KeyWord or Type");  
            	if(isKeyWord(w))
            		tokens.add(w.keyWordtoString());
            	else
                tokens.add(w.toString());  
                return w;  /*说明是关键字 或者是类型名*/   
            }  
            else{
            /* 否则就是一个标识符id */  
            w = new Word(s, Tag.ID);  
            tokens.add(w.toString());  
            //测试
            //Word we = new Word("bb",Tag.BASIC);
            //table.put(we, "basic");
            //
            table.put(w, "id");  
            words.put(s,  w);    
            return w;  
            }
        }  
          
        /* peek中的任意字符都被认为是词法单元返回 */  
        Token tok  = new Token(peek);  
        // table.put(tok, "Token or Seprator");  
        if ((int)peek != 0xffff )   
            tokens.add(tok.toString());  
        peek = ' ';  
          
        return tok;  
    }  
  /*@zjp调试*/
    public static void main(String[] args) {
		Lexer lexer = new Lexer();
		try {
			lexer.readch();
			//System.out.println(lexer.peek);
			Boolean b = lexer.readch('W');
			if(!b){
				//System.out.println(lexer.peek);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
}  
}