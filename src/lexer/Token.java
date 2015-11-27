package lexer;
/*
 * 描述所有记号的基类，它有一个数据成员tag以区分不同记号，构造函数赋tag值.
 */
public class Token {
	public final int tag;  
    
    public Token(int t) {  
        this.tag = t;  
    }  
      
    public String toString() {  
        return "" + (char)tag;  
    }  
    public static void main(String[] args) {  
        Token tok = new Token('a');  
        System.out.println(tok);  
    }  
}
