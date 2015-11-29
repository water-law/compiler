package lexer;

public class Real extends Token {
	public final double value;

	public Real(double v) {
		super(Tag.NUM);
		value = v;
	}

	public String toString() {
		return  "num, " + value; 
	}
}