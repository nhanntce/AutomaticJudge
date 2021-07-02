package obj;

/**
 * 
 * @author NhanNT
 *
 */
public class Comment {
	private String content;			//content of comment
	private int startLine;			//line start of comment
	private int endLine;			//line end of comment
	private int numberOfLines;		//number of lines comment
	private int typeOfComment;		//type of comment

	/**
	 * constructor
	 */
	public Comment() {
		super();
	}
	
	/**
	 * Constructor with three params
	 * @param content
	 * @param startLine
	 * @param endLine
	 */
	public Comment(String content, int startLine, int endLine) {
		super();
		this.content = content;
		this.startLine = startLine;
		this.endLine = endLine;
	}

	public Comment(String content, int startLine, int endLine, int numberOfLines) {
		super();
		this.content = content;
		this.startLine = startLine;
		this.endLine = endLine;
		this.numberOfLines = numberOfLines;
	}

	public Comment(String content, int startLine, int endLine, int numberOfLines, int typeOfComment) {
		super();
		this.content = content;
		this.startLine = startLine;
		this.endLine = endLine;
		this.numberOfLines = numberOfLines;
		this.typeOfComment = typeOfComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public int getNumberOfLines() {
		return endLine - startLine + 1;
	}

	private void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	/**
	 * 
	 * @return	if a comment is function comment, return 1
	 * 			if a comment is block comment, return 2
	 * 			if a comment is single comment, return 3
	 */
	public int getTypeOfComment() {
		String tmpContent = content.trim();
		if (tmpContent.startsWith("/**"))
			return 1;
		else if (tmpContent.startsWith("/*"))
			return 2;
		else
			return 3;
	}

	private void setTypeOfComment(int typeOfComment) {
		this.typeOfComment = typeOfComment;
	}
}
