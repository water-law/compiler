package parser;

/**
 * @author Schaepher
 *
 */
public class NextAction {
	private char type;
	private int stateNumberNext;

	public NextAction(char type, int statusNumber) {
		super();
		this.type = type;
		this.stateNumberNext = statusNumber;
	}

	// 's'为status  'r'为reduce（归约） 'a'为accept 'g'为goto
	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public int getStatusNumberNext() {
		return stateNumberNext;
	}

	public void setStatusNumberNext(int statusNumber) {
		this.stateNumberNext = statusNumber;
	}

	public String toString() {
		return this.type + String.valueOf(this.stateNumberNext);
	}

}
