package lexer;

public class Type extends lexer.Word {

	public Type(String s, int tag) {
		super(s, tag);
	}

	public static final lexer.Type Shortint = new Type("shortint", Tag.BASIC),
			Integer = new Type("integer", Tag.BASIC), Longint = new Type(
					"longint", Tag.BASIC), Byte = new Type("byte", Tag.BASIC),
			Word = new Type("word", Tag.BASIC), Dword = new Type("dword",
					Tag.BASIC), Int64 = new Type("int64", Tag.BASIC),
			Qword = new Type("qword", Tag.BASIC), Singer = new Type("singer",
					Tag.BASIC), Real = new Type("real", Tag.BASIC),
			Double = new Type("double", Tag.BASIC), Extended = new Type(
					"extended", Tag.BASIC), Comp = new Type("comp", Tag.BASIC),
			Char = new Type("char", Tag.BASIC), Boolean = new Type("boolean",
					Tag.BASIC);

}