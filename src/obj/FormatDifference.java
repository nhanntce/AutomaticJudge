package obj;

public class FormatDifference {

	private int lineStart;
	private int lineEnd;
	private String content1;
	private String content2;
	
	
	/**
	 * Constructor with 3 params
	 * @param lineStart
	 * @param lineEnd
	 * @param content
	 */
	
	public FormatDifference() {
		super();
	}

	public FormatDifference(int lineStart, String content1, String content2) {
		super();
		this.lineStart = lineStart;
		this.content1 = content1;
		this.content2 = content2;
	}

	public FormatDifference(String content1, String content2) {
		super();
		this.content1 = content1;
		this.content2 = content2;
	}

	public FormatDifference(int lineStart, int lineEnd, String content1, String content2) {
		super();
		this.lineStart = lineStart;
		this.lineEnd = lineEnd;
		this.content1 = content1;
		this.content2 = content2;
	}

	public FormatDifference(int lineStart, int lineEnd) {
		super();
		this.lineStart = lineStart;
		this.lineEnd = lineEnd;
	}
	
	
	
	public FormatDifference(int lineEnd) {
		super();
		this.lineEnd = lineEnd;
	}

	public int getLineStart() {
		return lineStart;
	}

	public void setLineStart(int lineStart) {
		this.lineStart = lineStart;
	}

	public int getLineEnd() {
		return lineEnd;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public void setLineEnd(int lineEnd) {
		this.lineEnd = lineEnd;
	}

	


	
}
