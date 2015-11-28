package lexer;

public class Operation extends Word{
	public Operation(String s, int tag) {  
        super(s, tag);  
    }
	public static final Operation
			And = new Operation("and",Tag.AND),//
			DivInt = new Operation("divInt",Tag.DIVINT),
			Mod = new Operation("mod",Tag.MOD),//
			Not = new Operation("not",Tag.NOT),//
			Or = new Operation("or",Tag.OR),//
			Xor = new Operation("xor",Tag.XOR),//
			Asn = new Operation(":=",Tag.ASN),
			Col = new Operation(":",Tag.COL),
			Plus = new Operation("+",Tag.PLUS),
			//Note = new Operation("--",Tag.NOTE),
			Minus = new Operation("-",Tag.MINUS),
			Mult = new Operation("*",Tag.MULT),
			DivReal = new Operation("/",Tag.DIVREAL),
			Eq = new Operation("=",Tag.EQ),
			Ge = new Operation(">=",Tag.GE),
			Gr = new Operation(">",Tag.GR),
			Le = new Operation("<=",Tag.XOR),
			Ne = new Operation("<>",Tag.XOR),
			Ls = new Operation("<",Tag.XOR);
			
}
