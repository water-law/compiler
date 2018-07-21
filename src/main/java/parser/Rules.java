package parser;

public class Rules {
	private String left;
	private int rightNum;
	private String right;

	public Rules(String left, String right, int rightNum) {
		super();
		this.left = left;
		this.rightNum = rightNum;
		this.right = right;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public int getRightNum() {
		return rightNum;
	}

	public void setRightNum(int rightNum) {
		this.rightNum = rightNum;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

}
