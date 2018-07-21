package parser;

import java.util.HashMap;

public class Expr {
	private static HashMap<Integer, Rules> map = new HashMap<Integer, Rules>();
	private static Expr expr = null;

	private Expr() {
		init();
	}

	public static Expr getInstance() {
		if (expr != null) {
			return expr;
		} else {
			return new Expr();
		}
	}

	public Rules get(int key) {
		return map.get(key);
	}

	public void init() {
		map.put(0, new Rules("S'", "S", 1));
		map.put(1, new Rules("S", "program id ( id_lists );  compound_stmt .", 8));
		map.put(2, new Rules("id_lists", "id", 1));
		map.put(3, new Rules("id_lists", "id_lists , id", 3));
		map.put(4, new Rules("compound_stmt", "begin optional_stmts end", 3));
		map.put(5, new Rules("optional_stmts", "stmts", 1));
		map.put(6, new Rules("optional_stmts", "ε", 0));
		map.put(7, new Rules("stmts", "stmt", 1));
		map.put(8, new Rules("stmts", "stmts; stmt", 3));
		map.put(9, new Rules("stmt", "id := expr", 3));
		map.put(10, new Rules("stmt", "compound_stmt", 1));
		map.put(11, new Rules("stmt", "if bool then stmt", 4));
		map.put(12, new Rules("stmt", "if bool then stmt else stmt", 6));
		map.put(13, new Rules("stmt", "while bool  do stmt", 4));
		map.put(14, new Rules("bool", "expr < expr", 3));
		map.put(15, new Rules("expr", "expr + term", 3));
		map.put(16, new Rules("expr", "term", 1));
		map.put(17, new Rules("term", "term * factor", 3));
		map.put(18, new Rules("term", "factor", 1));
		map.put(19, new Rules("factor", "id", 1));
		map.put(20, new Rules("factor", "num", 1));
	}
}
