package obj;

/**
 *
 * @author DangVTH
 */
public class MyFunction {

	private int startIndex;
	private int endIndex;
	private String content;
	private boolean isCommented;
	private int lineOfCodes;
	

	/**
	 * Constructor
	 */
	public MyFunction() {
		
	}

	/**
	 * Get index of start
	 *
	 * @return startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * Set index of start
	 *
	 * @param startIndex
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Get index of end
	 *
	 * @return endIndex
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * Set index of end
	 *
	 * @param endIndex
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * Get content of function
	 *
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Set content of function
	 *
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCommented() {
		return isCommented;
	}

	public void setCommented(boolean isCommented) {
		this.isCommented = isCommented;
	}

	public int getLineOfCodes() {
		return lineOfCodes;
	}

	public void setLineOfCodes(int lineOfCodes) {
		this.lineOfCodes = lineOfCodes;
	}

	
	
	
}
