package lexer;
/*
 * 描述保留字、标识符和各种复合运算符，
 * 派生于Token，增加了一个数据成员lexeme，它的构造函数有两个参数（前面是词素，后面是记号值）。
 */
public class Word extends Token {  
    public String lexme = "";  
      
    public Word (String s, int t) {  
        super(t);  
        this.lexme = s;  
    }  
      
    public String toString() {  
        return "<id,"+this.lexme+">";  
    }  
      
    public String keyWordtoString() {  
        return "<"+this.lexme+">";  
    }  
    public static final Word   
        and = new Word("&&", Tag.AND),  
        or = new Word("||", Tag.OR),  
        eq = new Word ("==", Tag.EQ),  
        ne = new Word("<>", Tag.NE),  
        le = new Word("<=", Tag.LE),  
        ge = new Word(">=", Tag.GE),  
        minus = new Word("minus", Tag.MINUS),  
        True = new Word("true", Tag.TRUE),  
        False = new Word("false", Tag.FALSE),  
        temp = new Word("t", Tag.TEMP);  
}  
