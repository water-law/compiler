package parser;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;
import lexer.Input;

public class Main {

	private static Input input;
	private static LinkedList<String> inputList;
	private static Stack<Integer> statusStack;
	private static Stack<String> signStack;
	private static Action action;
	private static Expr expr;
	private static NextAction nextAction;
	
	public static void init() throws IOException{
		input = new Input();
		inputList = input.getList();// 输入表
		statusStack = new Stack<Integer>();// 状态栈
		signStack = new Stack<String>();// 符号表
		action = Action.getInstance();
		expr = Expr.getInstance();
		statusStack.push(0);
		signStack.push("$");
		System.out.println("状态栈：" + statusStack.toString());
		System.out.println("符号栈：" + signStack.toString());
		System.out.println("输入栈：" + inputList.toString());
		System.out.println("动作：移入" + "\n");

	}
	public static void main(String[] args) throws IOException {

		init();
		
		String inputCur = inputList.pop();
		int stateCur; // 当前状态
		String signCur; // 当前符号
		for (; inputList != null;) {
			stateCur = statusStack.peek();//peek()读取栈顶元素但不抛出它
			signCur = signStack.peek();
			// 从当前状态和输入，得出下一步要做什么
			nextAction = action.get(stateCur, inputCur);
			if (nextAction == null) {
				System.out.println("出错，当前状态为：S" + stateCur);
				break;
			}
			char type = nextAction.getType();
			int nextState = nextAction.getStatusNumberNext();
			String actionPrint = "";
			switch (type) {
			case 's':
				statusStack.push(nextState);
				signStack.push(inputCur);
				actionPrint = "移入";
				break;
			case 'r':
				Rules ruleObject = expr.get(nextState);

				int reduceNum = ruleObject.getRightNum();
				// 归约的右侧可能不止一个，根据返回的个数判断
				for (int i = reduceNum; i > 0; i--) {
					statusStack.pop();
					signStack.pop();
				}
				signStack.push(ruleObject.getLeft());
				String gotoStatement = signStack.peek();
				stateCur = statusStack.peek();
				nextAction = action.get(stateCur, gotoStatement);
				nextState = nextAction.getStatusNumberNext();
				statusStack.push(nextState);
				actionPrint = "归约/" + ruleObject.getLeft() + " -> " + ruleObject.getRight();
				break;
			case 'a':
				System.out.println("accept");
				break;
			default:
				break;
			}
			System.out.println("状态栈：" + statusStack.toString());
			System.out.println("符号栈：" + signStack.toString());
			System.out.println("输入栈：" + inputList.toString());
			System.out.println("动作：" + actionPrint + "\n");

			if (type == 's') {
				inputCur = inputList.pop();
			}
			if (type == 'a') {
				break;
			}
		}

	}
}
