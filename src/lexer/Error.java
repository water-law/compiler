package lexer;

public class Error {
	public int line;
	public String error;

	public Error(int l, String e) {
		this.line = l;
		this.error = e;
	}

	public String writeError() {
		return "at " + line + " line,  error: " + this.error + ";";
	}

	public static void main(String[] args) {
		Error e = new Error(1, "数组越界");
		System.out.println(e.writeError());
	}
}
