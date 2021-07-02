package management;

import java.util.ArrayList;
import java.util.List;
import obj.Comment;
import obj.MyFunction;
import obj.MyStack;


/**
 *
 * @author DangVTH
 */
public class FunctionManagement {

	private List<MyFunction> funcs;
	private MyStack checkParentheses, checkCurlyBraces;

	/**
	 * Initialization
	 */
	public FunctionManagement() {

	}

	/**
	 * Add function to list
	 *
	 * @param startIndex
	 * @param endIndex
	 * @param content
	 */
	public void addFunction(MyFunction mf) {
		funcs.add(mf);
	}

	/**
	 * Get all of function
	 *
	 * @return fList
	 */
	public List<MyFunction> getList() {
		return funcs;
	}

	/**
	 * Find and get all of function
	 * 
	 * @param lines
	 * @return funcs
	 */
	public List<MyFunction> separateFunction(List<String> lines) {
		funcs = new ArrayList<MyFunction>();
		MyStack stack = new MyStack();
		checkParentheses = new MyStack();
		checkCurlyBraces = new MyStack();

		// Use to save index of start line
		int startIndex;

		// Loop for all of line
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);

			if (!checkSyntax(line)) {
				return null;
			}

			if (isFunction(line)) {
				boolean checkSyntax = true;
				// Use to save content of function
				String content = line;
				startIndex = i + 1;

				while (!line.contains(")") && (i < lines.size() - 1)) {
					if (isFunction(lines.get(i + 1))) {
						checkSyntax = false;
						break;
					}

					line = lines.get(++i);

					if (!checkSyntax(line)) {
						return null;
					}

					content += "\n" + line;
				}

				if (checkSyntax) {

					if (line.contains("{") && !line.contains("\"") && !line.contains("\'")) {
						stack.push("{");
					}

					while (i < lines.size() - 1) {
						line = lines.get(++i);

						if (!checkSyntax(line)) {
							return null;
						}

						content += "\n" + line;

						if (isFunction(line)) {

						} else {
							if (line.contains("{") && !line.contains("\"") && !line.contains("\'")) {
								stack.push("{");
							}

							if (line.contains("}") && !line.contains("\"") && !line.contains("\'")
									&& !stack.isEmpty()) {
								stack.pop();
								if (stack.isEmpty()) {
									MyFunction mf = new MyFunction();

									mf.setStartIndex(startIndex);
									mf.setEndIndex(i + 1);
									mf.setContent(content);

									funcs.add(mf);
									break;
								}
							}
						}
					}
				} else {
					return null;
				}
			}
		}

		if (!stack.isEmpty() || !checkParentheses.isEmpty() || !checkCurlyBraces.isEmpty()) {
			return null;
		}

		return funcs;
	}

	/**
	 * Check syntax error
	 * 
	 * @param line
	 * @return boolean
	 */
	private boolean checkSyntax(String line) {
		if (line.contains("\"") || line.contains("\'")) {
			boolean tmp = false;
			int d = 0;
			for (int i = 0; i < line.length(); i++) {
				if ((line.charAt(i) == '\"' || line.charAt(i) == '\'') && d != 1) {
					tmp = true;
					d++;
				} else if ((line.charAt(i) == '\"' || line.charAt(i) == '\'') && d == 1) {
					tmp = false;
					d = 0;
				}

				if (line.charAt(i) == '(' && !tmp) {
					checkParentheses.push("(");
				}

				if (line.charAt(i) == ')' && !tmp) {
					if (checkParentheses.isEmpty()) {
						return false;
					} else {
						checkParentheses.pop();
					}
				}

				if (line.charAt(i) == '{' && !tmp) {
					checkCurlyBraces.push("{");
				}

				if (line.charAt(i) == '}' && !tmp) {
					if (checkCurlyBraces.isEmpty()) {
						return false;
					} else {
						checkCurlyBraces.pop();
					}
				}
			}
		} else {
			if (line.contains("(")) {
				checkParentheses.push("(");
			}

			if (line.contains(")")) {
				if (checkParentheses.isEmpty()) {
					return false;
				} else {
					checkParentheses.pop();
				}

			}

			if (line.contains("{")) {
				checkCurlyBraces.push("{");
			}

			if (line.contains("}")) {
				if (checkCurlyBraces.isEmpty()) {
					return false;
				} else {
					checkCurlyBraces.pop();
				}
			}
		}

		return true;
	}

	/**
	 * Find the line that contain return type
	 * 
	 * @param line
	 * @return boolean
	 */
	public boolean isContainReturnType(String line) {
		// List of return type for C language
		String[] returnTypes = new String[] { "void", "char", "unsigned char", "int", "unsigned int", "short",
				"unsigned short", "long long", "unsigned long long", "float", "double" };

		for (String rt : returnTypes) {
			if (line.startsWith(rt.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Find the line that contain function definition
	 * 
	 * @param line
	 * @return boolean
	 */
	public boolean isFunction(String line) {
		if (line.startsWith("//") || line.contains(";")) {
			return false;
		}

		if (isContainReturnType(line) && line.contains("(")) {
			return true;
		}
		return false;
	}

	/**
	 * Check and modify whether a function is commented or not
	 * 
	 * @param cmts
	 */
	public void checkFunctionComment(List<Comment> cmts) {
		for (int i = 0; i < funcs.size(); i++) {
			for (int j = 0; j < cmts.size(); j++) {
				if (i == 0) {
					if (funcs.get(i).getStartIndex() > cmts.get(j).getEndLine()
							&& cmts.get(j).getTypeOfComment() == 1) {
						funcs.get(i).setCommented(true);
					}
				} else {
					if (funcs.get(i).getStartIndex() > cmts.get(j).getEndLine()
							&& funcs.get(i - 1).getEndIndex() < cmts.get(j).getStartLine()
							&& cmts.get(j).getTypeOfComment() == 1) {
						funcs.get(i).setCommented(true);
					}
				}
			}
		}
	}

	/**
	 * Get information of function for print
	 * 
	 * @return str
	 */
	public String toString(List<Comment> commentList) {
		String str = "";
		CommentManagement cm = new CommentManagement();
		for (int i = 0; i < funcs.size(); i++) {
			MyFunction mf = funcs.get(i);
			str += "---------------------------------------\nFunction No." + (i + 1);
			str += "\nStart line: " + mf.getStartIndex() + "\nEnd line: " + mf.getEndIndex() + "\nContent:\n"
					+ mf.getContent() + "\nFunction is commented: "
					+ (mf.isCommented() ? "yes\n"
							: "no\n" + "\nPercent of function comment: "
									+ (cm.calculatePercentOfFunctionCmt(mf, commentList) == 0 ? "0%"
											: cm.calculatePercentOfFunctionCmt(mf, commentList) + "%\n"));
		}
		return str;
	}
}