package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Action {
	private static ArrayList<HashMap<String, NextAction>> actionMapList;
	private static Action action = null;

	private Action() {
		init();
	}

	public static Action getInstance() {
		if (action != null) {
			return action;
		} else {
			return new Action();
		}
	}

	public NextAction get(int stateCur, String key) {
		return actionMapList.get(stateCur).get(key);
	}

	public void print() {
		int i = 0;
		for (HashMap<String, NextAction> map : actionMapList) {
			Iterator iter = map.keySet().iterator();
			System.out.println("\nS" + String.valueOf(i));
			i++;
			while (iter.hasNext()) {
				Object key = iter.next();
				NextAction val = map.get(key);
				System.out.println(key.toString() + " " + val.toString());
			}
		}
	}

	private void init() {

		actionMapList = new ArrayList<HashMap<String, NextAction>>();
		for (int index = 0; index < 46; index++) {
			actionMapList.add(new HashMap<String, NextAction>());
		}

		// DiyString的type ： 's'为status 'r'为reduce（归约） 'a'为accept 'g'为goto
		HashMap<String, NextAction> mapCur;

		// S0
		mapCur = actionMapList.get(0);
		mapCur.put("program", new NextAction('s', 2));
		mapCur.put("S", new NextAction('g', 1));

		// S1
		mapCur = actionMapList.get(1);
		mapCur.put("$", new NextAction('a', 0));

		// S2
		mapCur = actionMapList.get(2);
		mapCur.put("id", new NextAction('s', 3));

		// S3
		mapCur = actionMapList.get(3);
		mapCur.put("(", new NextAction('s', 4));

		// S4
		mapCur = actionMapList.get(4);
		mapCur.put("id", new NextAction('s', 6));
		mapCur.put("id_lists", new NextAction('g', 5));

		// S5
		mapCur = actionMapList.get(5);
		mapCur.put(")", new NextAction('s', 7));
		mapCur.put(",", new NextAction('s', 8));

		// S6
		mapCur = actionMapList.get(6);
		mapCur.put(")", new NextAction('r', 2));
		mapCur.put(",", new NextAction('r', 2));

		// S7
		mapCur = actionMapList.get(7);
		mapCur.put(";", new NextAction('s', 9));

		// S8
		mapCur = actionMapList.get(8);
		mapCur.put("id", new NextAction('s', 10));

		// S9
		mapCur = actionMapList.get(9);
		mapCur.put("begin", new NextAction('s', 12));
		mapCur.put("compound_stmt", new NextAction('g', 11));

		// S10
		mapCur = actionMapList.get(10);
		mapCur.put(")", new NextAction('r', 3));
		mapCur.put(",", new NextAction('r', 3));

		// S11
		mapCur = actionMapList.get(11);
		mapCur.put(".", new NextAction('s', 13));

		// S12
		mapCur = actionMapList.get(12);
		mapCur.put("id", new NextAction('s', 17));
		mapCur.put("begin", new NextAction('s', 12));
		mapCur.put("end", new NextAction('r', 6));
		mapCur.put("if", new NextAction('s', 19));
		mapCur.put("while", new NextAction('s', 20));
		mapCur.put("compound_stmt", new NextAction('g', 18));
		mapCur.put("optional_stmts", new NextAction('g', 14));
		mapCur.put("stmts", new NextAction('g', 15));
		mapCur.put("stmt", new NextAction('g', 16));

		// S13
		mapCur = actionMapList.get(13);
		mapCur.put("$", new NextAction('r', 1));

		// S14
		mapCur = actionMapList.get(14);
		mapCur.put("end", new NextAction('s', 21));

		// S15
		mapCur = actionMapList.get(15);
		mapCur.put(";", new NextAction('s', 22));
		mapCur.put("end", new NextAction('r', 5));

		// S16
		mapCur = actionMapList.get(16);
		mapCur.put(";", new NextAction('r', 7));
		mapCur.put("end", new NextAction('r', 7));

		// S17
		mapCur = actionMapList.get(17);
		mapCur.put(":=", new NextAction('s', 23));

		// S18
		mapCur = actionMapList.get(18);
		mapCur.put(";", new NextAction('r', 10));
		mapCur.put("end", new NextAction('r', 10));
		mapCur.put("else", new NextAction('r', 10));

		// S19
		mapCur = actionMapList.get(19);
		mapCur.put("id", new NextAction('s', 28));
		mapCur.put("num", new NextAction('s', 29));
		mapCur.put("bool", new NextAction('g', 24));
		mapCur.put("expr", new NextAction('g', 25));
		mapCur.put("term", new NextAction('g', 26));
		mapCur.put("factor", new NextAction('g', 27));

		// S20
		mapCur = actionMapList.get(20);
		mapCur.put("id", new NextAction('s', 28));
		mapCur.put("num", new NextAction('s', 29));
		mapCur.put("bool", new NextAction('g', 30));
		mapCur.put("expr", new NextAction('g', 25));
		mapCur.put("term", new NextAction('g', 26));
		mapCur.put("factor", new NextAction('g', 27));

		// S21
		mapCur = actionMapList.get(21);
		mapCur.put(";", new NextAction('r', 4));
		mapCur.put(".", new NextAction('r', 4));
		mapCur.put("end", new NextAction('r', 4));
		mapCur.put("else", new NextAction('r', 4));

		// S22
		mapCur = actionMapList.get(22);
		mapCur.put("id", new NextAction('s', 17));
		mapCur.put("begin", new NextAction('s', 12));
		mapCur.put("if", new NextAction('s', 19));
		mapCur.put("while", new NextAction('s', 20));
		mapCur.put("compound_stmt", new NextAction('g', 18));
		mapCur.put("stmt", new NextAction('g', 31));

		// S23
		mapCur = actionMapList.get(23);
		mapCur.put("id", new NextAction('s', 28));
		mapCur.put("num", new NextAction('s', 29));
		mapCur.put("expr", new NextAction('g', 32));
		mapCur.put("term", new NextAction('g', 26));
		mapCur.put("factor", new NextAction('g', 27));

		// S24
		mapCur = actionMapList.get(24);
		mapCur.put("then", new NextAction('s', 33));

		// S25
		mapCur = actionMapList.get(25);
		mapCur.put("<", new NextAction('s', 34));
		mapCur.put("+", new NextAction('s', 35));

		// S26
		mapCur = actionMapList.get(26);
		mapCur.put(";", new NextAction('r', 16));
		mapCur.put("end", new NextAction('r', 16));
		mapCur.put("then", new NextAction('r', 16));
		mapCur.put("else", new NextAction('r', 16));
		mapCur.put("do", new NextAction('r', 16));
		mapCur.put("<", new NextAction('r', 16));
		mapCur.put("+", new NextAction('r', 16));
		mapCur.put("*", new NextAction('s', 36));

		// S27
		mapCur = actionMapList.get(27);
		mapCur.put(";", new NextAction('r', 18));
		mapCur.put("end", new NextAction('r', 18));
		mapCur.put("then", new NextAction('r', 18));
		mapCur.put("else", new NextAction('r', 18));
		mapCur.put("do", new NextAction('r', 18));
		mapCur.put("<", new NextAction('r', 18));
		mapCur.put("+", new NextAction('r', 18));
		mapCur.put("*", new NextAction('r', 18));

		// S28
		mapCur = actionMapList.get(28);
		mapCur.put(";", new NextAction('r', 19));
		mapCur.put("end", new NextAction('r', 19));
		mapCur.put("then", new NextAction('r', 19));
		mapCur.put("else", new NextAction('r', 19));
		mapCur.put("do", new NextAction('r', 19));
		mapCur.put("<", new NextAction('r', 19));
		mapCur.put("+", new NextAction('r', 19));
		mapCur.put("*", new NextAction('r', 19));

		// S29
		mapCur = actionMapList.get(29);
		mapCur.put(";", new NextAction('r', 20));
		mapCur.put("end", new NextAction('r', 20));
		mapCur.put("then", new NextAction('r', 20));
		mapCur.put("else", new NextAction('r', 20));
		mapCur.put("do", new NextAction('r', 20));
		mapCur.put("<", new NextAction('r', 20));
		mapCur.put("+", new NextAction('r', 20));
		mapCur.put("*", new NextAction('r', 20));

		// S30
		mapCur = actionMapList.get(30);
		mapCur.put("do", new NextAction('s', 37));

		// S31
		mapCur = actionMapList.get(31);
		mapCur.put(";", new NextAction('r', 8));
		mapCur.put("end", new NextAction('r', 8));

		// S32
		mapCur = actionMapList.get(32);
		mapCur.put(";", new NextAction('r', 9));
		mapCur.put("end", new NextAction('r', 9));
		mapCur.put("else", new NextAction('r', 9));
		mapCur.put("+", new NextAction('s', 35));

		// S33
		mapCur = actionMapList.get(33);
		mapCur.put("id", new NextAction('s', 17));
		mapCur.put("begin", new NextAction('s', 12));
		mapCur.put("if", new NextAction('s', 19));
		mapCur.put("while", new NextAction('s', 20));
		mapCur.put("compound_stmt", new NextAction('g', 18));
		mapCur.put("stmt", new NextAction('g', 38));

		// S34
		mapCur = actionMapList.get(34);
		mapCur.put("id", new NextAction('s', 28));
		mapCur.put("num", new NextAction('s', 29));
		mapCur.put("expr", new NextAction('g', 39));
		mapCur.put("term", new NextAction('g', 26));
		mapCur.put("factor", new NextAction('g', 27));

		// S35
		mapCur = actionMapList.get(35);
		mapCur.put("id", new NextAction('s', 28));
		mapCur.put("num", new NextAction('s', 29));
		mapCur.put("term", new NextAction('g', 40));
		mapCur.put("factor", new NextAction('g', 27));

		// S36
		mapCur = actionMapList.get(36);
		mapCur.put("id", new NextAction('s', 28));
		mapCur.put("num", new NextAction('s', 29));
		mapCur.put("factor", new NextAction('g', 41));

		// S37
		mapCur = actionMapList.get(37);
		mapCur.put("id", new NextAction('s', 17));
		mapCur.put("begin", new NextAction('s', 12));
		mapCur.put("if", new NextAction('s', 19));
		mapCur.put("while", new NextAction('s', 20));
		mapCur.put("compound_stmt", new NextAction('g', 18));
		mapCur.put("stmt", new NextAction('g', 42));

		// S38
		mapCur = actionMapList.get(38);
		mapCur.put(";", new NextAction('r', 11));
		mapCur.put("end", new NextAction('r', 11));
		mapCur.put("else", new NextAction('s', 43));

		// S39
		mapCur = actionMapList.get(39);
		mapCur.put("then", new NextAction('r', 14));
		mapCur.put("do", new NextAction('r', 14));
		mapCur.put("+", new NextAction('s', 44));

		// S40
		mapCur = actionMapList.get(40);
		mapCur.put(";", new NextAction('r', 15));
		mapCur.put("end", new NextAction('r', 15));
		mapCur.put("then", new NextAction('r', 15));
		mapCur.put("else", new NextAction('r', 15));
		mapCur.put("do", new NextAction('r', 15));
		mapCur.put("<", new NextAction('r', 15));
		mapCur.put("+", new NextAction('r', 15));
		mapCur.put("*", new NextAction('s', 36));

		// S41
		mapCur = actionMapList.get(41);
		mapCur.put(";", new NextAction('r', 17));
		mapCur.put("end", new NextAction('r', 17));
		mapCur.put("then", new NextAction('r', 17));
		mapCur.put("else", new NextAction('r', 17));
		mapCur.put("do", new NextAction('r', 17));
		mapCur.put("<", new NextAction('r', 17));
		mapCur.put("+", new NextAction('r', 17));
		mapCur.put("*", new NextAction('r', 17));

		// S42
		mapCur = actionMapList.get(42);
		mapCur.put(";", new NextAction('r', 13));
		mapCur.put("end", new NextAction('r', 13));
		mapCur.put("else", new NextAction('r', 13));

		// S43
		mapCur = actionMapList.get(43);
		mapCur.put("id", new NextAction('s', 17));
		mapCur.put("begin", new NextAction('s', 12));
		mapCur.put("if", new NextAction('s', 19));
		mapCur.put("while", new NextAction('s', 20));
		mapCur.put("compound_stmt", new NextAction('g', 18));
		mapCur.put("stmt", new NextAction('g', 45));

		// S44
		mapCur = actionMapList.get(44);
		mapCur.put("id", new NextAction('s', 28));
		mapCur.put("num", new NextAction('s', 29));
		mapCur.put("term", new NextAction('g', 40));
		mapCur.put("factor", new NextAction('g', 27));

		// S45
		mapCur = actionMapList.get(45);
		mapCur.put(";", new NextAction('r', 12));
		mapCur.put("end", new NextAction('r', 12));
		mapCur.put("else", new NextAction('r', 12));
	}
	
}
