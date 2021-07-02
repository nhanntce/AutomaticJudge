package management;

import java.util.ArrayList;
import java.util.List;
import obj.Comment;
import obj.MyFunction;
import utility.Tool;



/**
 * 
 * @author NhanNT
 *
 */
public class CommentManagement {

	/**
	 * separating comment
	 * 
	 * @param lines
	 * @return a list comment object
	 */
	public List<Comment> separateComment(List<String> lines) {
		List<Comment> cmts = new ArrayList<Comment>();
		boolean checkOpenCmt = false;
		boolean checkStartComment = false;
		String comment = "";
		int lineStart = 0;

		for (int i = 0; i < lines.size(); i++) {
			if ((Tool.checkBlankLine(lines.get(i))
					|| (lines.get(i).indexOf("//") == -1 && lines.get(i).indexOf("/*") == -1)) && !checkOpenCmt) {
//					System.out.println("case 2 " + checkStartComment);
				if (checkStartComment) {
//						System.out.println("add CMT");
					checkStartComment = false;
					cmts.add(new Comment(comment, lineStart, i));
					comment = "";
				}
			} else {
				if (checkOpenCmt) {
					if (lines.get(i).indexOf("*/") == -1) {
//							System.out.println("First" + lines.get(i));
						comment += lines.get(i).trim() + "\n";
					} else {
//							System.out.println("Second" + lines.get(i));
						checkOpenCmt = !checkOpenCmt;
						comment += lines.get(i).trim() + "\n";
						checkStartComment = false;
						cmts.add(new Comment(comment, lineStart, i + 1));
						comment = "";
					}
				} else {
					if (lines.get(i).indexOf("//") != -1 && lines.get(i).indexOf("/*") != -1) {
						if (lines.get(i).indexOf("//") < lines.get(i).indexOf("/*")) {

//								System.out.println("Case cmt single 1: \n" + checkStartComment);
							if (Tool.getindexOfCodeLine(lines.get(i)) < lines.get(i).indexOf("//")
									&& !checkStartComment) {
								cmts.add(new Comment(
										lines.get(i).substring(lines.get(i).indexOf("//"), lines.get(i).length())
												+ "\n",
										i + 1, i + 1));
							} else if (Tool.getindexOfCodeLine(lines.get(i)) < lines.get(i).indexOf("//")
									&& checkStartComment) {
//									System.out.println("add CMT Here");
								checkStartComment = false;
								cmts.add(new Comment(comment, lineStart, i));
								comment = "";
								cmts.add(new Comment(
										lines.get(i).substring(lines.get(i).indexOf("//"), lines.get(i).length())
												+ "\n",
										i + 1, i + 1));
							} else {
								comment += lines.get(i).substring(lines.get(i).indexOf("//"), lines.get(i).length())
										+ "\n";
								if (!checkStartComment)
									lineStart = i + 1;
								checkStartComment = true;
//									System.out.println("Case cmt single 2: \n" + lines.get(i) + checkStartComment);
							}
						} else {

//								System.out.println("Case cmt block: \n" + lines.get(i));
							checkOpenCmt = !checkOpenCmt;

						}
					} else if (lines.get(i).indexOf("//") != -1) {
						if (Tool.getindexOfCodeLine(lines.get(i)) < lines.get(i).indexOf("//") && !checkStartComment) {
							cmts.add(new Comment(
									lines.get(i).substring(lines.get(i).indexOf("//"), lines.get(i).length()) + "\n",
									i + 1, i + 1));
						} else if (Tool.getindexOfCodeLine(lines.get(i)) < lines.get(i).indexOf("//")
								&& checkStartComment) {
//								System.out.println("add CMT Here");
							checkStartComment = false;
							cmts.add(new Comment(comment, lineStart, i));
							comment = "";
							cmts.add(new Comment(
									lines.get(i).substring(lines.get(i).indexOf("//"), lines.get(i).length()) + "\n",
									i + 1, i + 1));
						} else {
							comment += lines.get(i).substring(lines.get(i).indexOf("//"), lines.get(i).length()) + "\n";
							if (!checkStartComment)
								lineStart = i + 1;
							checkStartComment = true;
//								System.out.println("Case cmt single 2: \n" + lines.get(i) + checkStartComment);
						}
					} else if (lines.get(i).indexOf("/*") != -1) {
						if (lines.get(i).indexOf("*/") != -1
								&& lines.get(i).indexOf("*/") > lines.get(i).indexOf("/*")) {
//								System.out.println("Case cmt block inline: \n" + lines.get(i));
							cmts.add(new Comment(
									lines.get(i).substring(lines.get(i).indexOf("/*"), lines.get(i).indexOf("*/") + 2)
											+ "\n",
									i + 1, i + 1));

						} else {
//								System.out.println("Case cmt block: \n" + lines.get(i));
							checkOpenCmt = !checkOpenCmt;
							comment += lines.get(i) + "\n";
							if (!checkStartComment)
								lineStart = i + 1;
							checkStartComment = true;
						}
					}
				}
			}
		}
		return cmts;
	}

	/**
	 * separate comments from source code and delete comment from List preference 
	 * Watch out: the list which is parameter will be change value
	 * 
	 * @param lines List line by line of source code
	 * @return List comment objects
	 */
	public List<Comment> separateComments(List<String> lines) {
		List<Comment> cmts = new ArrayList<Comment>();
		String comment = "";
		int lineStart = 0;
		int numberOfLines = lines.size();
		String currentLine = "";
		String tmpStr = "";
		char[] chOfcurrentLine;
		boolean cmtBlockMode = false;
		boolean cmtInLineMode = false;
		boolean singleQuote = false;
		boolean doubleQuote = false;
		boolean isDelete = false;
		int sourceIndex;
		int charIndex;
		int numberOfChar;
		for (sourceIndex = 0; sourceIndex < numberOfLines; sourceIndex++) {
			currentLine = lines.get(sourceIndex);
			if (cmtInLineMode) {
				if (currentLine.trim().startsWith("//")) {
					comment += ("\n" + currentLine.trim());
					// delete comment
					lines.set(sourceIndex, "");
					continue;
				} else {
					cmts.add(new Comment(comment, lineStart, sourceIndex));
					comment = "";
					cmtInLineMode = false;
				}
			}
			if ((currentLine.indexOf("//") == -1 && currentLine.indexOf("/*") == -1) && !cmtBlockMode) {
				continue;
			}
			if (cmtBlockMode) {
				comment += '\n';
				if (currentLine.indexOf("*/") == -1) {
					comment += currentLine.trim();
					// delete comment
					lines.set(sourceIndex, "");
					continue;
				}
			}

			chOfcurrentLine = currentLine.toCharArray();
			numberOfChar = chOfcurrentLine.length;
			for (charIndex = 1; charIndex < numberOfChar; charIndex++) {
				if (!cmtBlockMode) {
					if ((singleQuote || doubleQuote) && chOfcurrentLine[charIndex - 1] == '\\') {
						charIndex += 1;
						continue;
					} else if (!singleQuote && chOfcurrentLine[charIndex - 1] == '\"') {
						doubleQuote = !doubleQuote;
					} else if (!doubleQuote && chOfcurrentLine[charIndex - 1] == '\'') {
						singleQuote = !singleQuote;
					} else if (!singleQuote && !doubleQuote && !cmtBlockMode && chOfcurrentLine[charIndex - 1] == '/'
							&& chOfcurrentLine[charIndex] == '/') {
						if (!currentLine.trim().startsWith("//")) {
							cmts.add(new Comment(currentLine.substring(charIndex - 1), sourceIndex + 1,
									sourceIndex + 1));
							//delete comment
							lines.set(sourceIndex, currentLine.substring(0, charIndex - 1));
							break;
						} else {
							comment += currentLine.substring(charIndex - 1);
							// delete comment
							lines.set(sourceIndex, currentLine.substring(0, charIndex - 1));
							lineStart = sourceIndex + 1;
							cmtInLineMode = true;
							break;
						}
					} else if (!singleQuote && !doubleQuote && chOfcurrentLine[charIndex - 1] == '/'
							&& chOfcurrentLine[charIndex] == '*') {
						comment += chOfcurrentLine[charIndex - 1];
						comment += chOfcurrentLine[charIndex];
						lineStart = sourceIndex + 1;
						tmpStr = currentLine.substring(0, charIndex - 1);
						if(currentLine.indexOf("*/", charIndex) != -1) {
							isDelete = true;
							tmpStr += currentLine.substring(currentLine.indexOf("*/", charIndex) + 2);
						}
						// delete comment
						lines.set(sourceIndex, tmpStr);
						cmtBlockMode = true;
					}

				} else if (chOfcurrentLine[charIndex - 1] == '*' && chOfcurrentLine[charIndex] == '/') {
					comment += chOfcurrentLine[charIndex - 1];
					comment += chOfcurrentLine[charIndex];
					cmts.add(new Comment(comment, lineStart, sourceIndex + 1));
					tmpStr = currentLine.substring(charIndex + 1);
					//delete comment
					if(isDelete) {
						isDelete = false;
					} else {
						lines.set(sourceIndex, tmpStr);
					}
					comment = "";
					cmtBlockMode = false;
				} else {
					comment += chOfcurrentLine[charIndex - 1];
				}

			}
		}
		return cmts;
	}

	/**
	 * 
	 * @param func
	 * @param cmtList
	 * @return
	 */
	public double calculatePercentOfFunctionCmt(MyFunction func, List<Comment> cmtList) {
		int countCmt = 0;
		int totalLine = Tool.countLines(func.getContent());
		for (Comment comment : cmtList) {
			if ((comment.getStartLine() > func.getStartIndex() + 1) && (comment.getEndLine() < func.getEndIndex())) {
				countCmt += 1;
			}
		}
		double resut = (countCmt / (double) totalLine) * 100;
		return resut != 0 ? Math.round(resut * 100.0) / 100.0 : 0;
	}

}
