package lexer;

import lexer.*;

/*
 * 描述保留字、标识符和各种复合运算符，
 * 派生于Token，增加了一个数据成员lexeme，它的构造函数有两个参数（前面是词素，后面是记号值）。
 */
public class Word extends Token {
	public String lexme = "";

	public Word(String s, int t) {
		super(t);
		this.lexme = s;
	}

	public String toString() {
		return this.lexme;
	}

	public String getString(boolean isKey) {
		if (!isKey)
			return "id";
		else
			return this.lexme;
	}

	public static final Word Absolute = new Word("absolute", Tag.ABSOLUTE),
			Abstract = new Word("abstract", Tag.ABSTRACT), Array = new Word(
					"array", Tag.ARRAY), As = new Word("as", Tag.AS),
			Asm = new Word("asm", Tag.ASM), Assembler = new Word("assembler",
					Tag.ASSEMBLER), At = new Word("at", Tag.AT),
			Automated = new Word("automated", Tag.AUTOMATED), Begin = new Word(
					"begin", Tag.BEGIN), Case = new Word("case", Tag.CASE),
			Cdecl = new Word("cdecl", Tag.CDECL), Class = new Word("class",
					Tag.CLASS), Const = new Word("const", Tag.CONST),
			Constructor = new Word("constructor", Tag.CONSTRUCTOR),
			Contains = new Word("contains", Tag.CONTAINS), Default = new Word(
					"default", Tag.DEFAULT), Destructor = new Word(
					"destructor", Tag.DESTRUCTOR), Dispid = new Word("dispid",
					Tag.DISPID), Dispinterface = new Word("dispinterface",
					Tag.DISPINTERFACE), Do = new Word("do", Tag.DO),
			Downto = new Word("downto", Tag.DOWNTO), Dynamic = new Word(
					"dynamic", Tag.DYNAMIC), Else = new Word("else", Tag.ELSE),
			End = new Word("end", Tag.END), Except = new Word("except",
					Tag.EXCEPT), Export = new Word("export", Tag.EXPORT),
			Exports = new Word("exports", Tag.EXPORTS), External = new Word(
					"external", Tag.EXTERNAL), Far = new Word("far", Tag.FAR),
			File = new Word("file", Tag.FILE), Finalization = new Word(
					"finalization", Tag.FINALIZATION), Finally = new Word(
					"finally", Tag.FINALLY), For = new Word("for", Tag.FOR),
			Forward = new Word("forward", Tag.FORWARD), Function = new Word(
					"function", Tag.FUNCTION),
			Goto = new Word("goto", Tag.GOTO), If = new Word("if", Tag.IF),
			Implementation = new Word("Implementation", Tag.IMPLEMENTATION),
			Implements = new Word("Implements", Tag.IMPLEMENTS), In = new Word(
					"in", Tag.IN), Index = new Word("index", Tag.INDEX),
			Inherited = new Word("inherited", Tag.INHERITED),
			Initialization = new Word("initialization", Tag.INITIALIZATION),
			Inline = new Word("inline", Tag.INLINE), Interface = new Word(
					"interface", Tag.INTERFACE), Is = new Word("is", Tag.IS),
			Label = new Word("label", Tag.LABEL), Library = new Word("library",
					Tag.LIBRARY), Message = new Word("message", Tag.MESSAGE),
			Name = new Word("name", Tag.NAME),
			Near = new Word("near", Tag.NEAR), Nil = new Word("nil", Tag.NIL),
			Nodefault = new Word("nodefault", Tag.NODEFAULT),
			Object = new Word("object", Tag.OBJECT),
			Of = new Word("of", Tag.OF), On = new Word("on", Tag.ON),
			Out = new Word("out", Tag.OUT), Overload = new Word("overload",
					Tag.OVERLOAD),
			Override = new Word("override", Tag.OVERRIDE), Package = new Word(
					"package", Tag.PACKAGE), Packed = new Word("packed",
					Tag.PACKED), Pascal = new Word("pascal", Tag.PASCAL),
			Private = new Word("private", Tag.PRIVATE), Procedure = new Word(
					"procedure", Tag.PROCEDURE), Program = new Word("program",
					Tag.PROGRAM),
			Property = new Word("property", Tag.PROPERTY),
			Protected = new Word("protected", Tag.PROTECTED),
			Public = new Word("public", Tag.PUBLIC), Published = new Word(
					"published", Tag.PUBLISHED), Raise = new Word("raise",
					Tag.RAISE), Read = new Word("read", Tag.READ),
			Readonly = new Word("readonly", Tag.READONLY), Record = new Word(
					"record", Tag.RECORD), Register = new Word("register",
					Tag.REGISTER), Reintroduce = new Word("reintroduce",
					Tag.REINTRODUCE), Repeat = new Word("repeat", Tag.REPEAT),
			Requires = new Word("requires", Tag.REQUIRES), Resident = new Word(
					"resident", Tag.RESIDENT), Resourcestring = new Word(
					"resourcestring", Tag.RESOURCESTRING), Safecall = new Word(
					"safecall", Tag.SAFECALL), Set = new Word("set", Tag.SET),
			Shl = new Word("shl", Tag.SHL), Shr = new Word("shr", Tag.SHR),
			Stdcall = new Word("stdcall", Tag.STDCALL), Stored = new Word(
					"stored", Tag.STORED), String = new Word("string",
					Tag.STRING), Then = new Word("then", Tag.THEN),
			Threadvar = new Word("threadvar", Tag.THREADVAR), To = new Word(
					"to", Tag.TO), Try = new Word("try", Tag.TRY),
			Type = new Word("type", Tag.TYPE),
			Unit = new Word("unit", Tag.UNIT), Until = new Word("until",
					Tag.UNTIL), Uses = new Word("uses", Tag.USES),
			Var = new Word("var", Tag.VAR), Virtual = new Word("virtual",
					Tag.VIRTUAL), While = new Word("while", Tag.WHILE),
			With = new Word("with", Tag.WITH), Write = new Word("write",
					Tag.WRITE),
			Writeonly = new Word("writeonly", Tag.WRITEONLY);
			// Inf = new Word("整数越界",Tag.INF),
			// Note = new Word("注释", Tag.NOTE);

}
