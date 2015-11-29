package lexer;
/*
 * 描述整数的类，派生于Token，增加了一个数据成员value（词素，注意它的类型），
 * 它的构造函数在利用基类构造函数初始化tag值后，还给value赋了值。
 */
public class Num extends Token{  
    public final int value;  
    
    public Num(int v) {  
        super(Tag.NUM);  
        this.value = v;  
    }  
    public String toString() {  
        return  "num, " + value;  
    }  
}  