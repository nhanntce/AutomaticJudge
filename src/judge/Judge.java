package judge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import management.FunctionManagement;
import obj.Plagiarism;
import utility.Formatter;
import utility.Plagiarismer;

/**
 *
 * @author Nguyen Vuong Khang Hy Email: khanghy3004@gmail.com Automatic Judger
 */
public class Judge {

	frmJudge parent;
	FileHandle fhandle;
	String error;
	long maxMemory;
	private boolean checkFormat; // is formatted
	private double checkCommet; // percent commented
	private ArrayList<Plagiarism> listPlagiarisms;

	/**
	 * Construct a Judge DangVTH
	 *
	 * @param parent Judge Frame
	 */
	public Judge(frmJudge parent) {
		this.parent = parent;
		this.fhandle = new FileHandle();
		this.error = "";
		this.maxMemory = 0;
	}

	/**
	 * Judging and writing a log file DangVTH
	 *
	 * @param folderPath List of Path to the submission folder
	 * @param fileName   List of file name
	 * @param auto       True if auto judge, false if not auto judge
	 */
	public void judge(ArrayList<String> folderPath, ArrayList<String> fileName, boolean auto) {
		int point;
		long maxTime;
		String name; // Store the name of the solution including name extension DangVTH
		String tenbai; // Store the name of the solution DangVTH
		String stuclass; // Store the name of class DangVTH
		String user; // Store the name of user DangVTH
		String problem; // Store the name of the problem DangVTH
		String type; // Store the type of the solution DangVTH
		FileWriter writer = null;

		// scan workspace DangVTH
		for (int i = 0; i < folderPath.size(); ++i) {
			try {
				name = fileName.get(i);
				// Get name of solution DangVTH
				tenbai = name.split("\\.")[0];
				// Get class of student DangVTH
				stuclass = getStuClass(name);
				// Get username of student DangVTH
				user = getUser(name);
				// Get name of problem DangVTH
				problem = getProblem(name);
				// Get type of solution DangVTH
				type = getType(name);

				// copy file from submission DangVTH
				fhandle.copyFile(folderPath.get(i), name);

				// Create log file DangVTH
				writer = new FileWriter(parent.folderNopbaiPath + "/Logs/" + stuclass + "/" + name + ".log");

				parent.pro.getTxtCompileRun().setText("Compiling " + problem);
				// if comile error DangVTH
				if (!compile(tenbai, problem, type, stuclass)) {
					writer.write("Compile Error\n" + error);
					setPoint(stuclass, "Compile Error", parent.hmStuIndex.get(user + stuclass),
							parent.hmTable.get(stuclass).getColumn(problem).getModelIndex());
					setPoint(stuclass, getTotalPoint(stuclass, user), parent.hmStuIndex.get(user + stuclass),
							parent.hmTable.get(stuclass).getColumnCount() - 1);
					Files.deleteIfExists(Paths.get(name));
					continue;
				}
				// if runtime error DangVTH
				if (!error.equals("")) {
					writer.write("Run Time Error\n" + error);
					setPoint(stuclass, "Run Time Error", parent.hmStuIndex.get(user + stuclass),
							parent.hmTable.get(stuclass).getColumn(problem).getModelIndex());
					setPoint(stuclass, getTotalPoint(stuclass, user), parent.hmStuIndex.get(user + stuclass),
							parent.hmTable.get(stuclass).getColumnCount() - 1);

					Files.deleteIfExists(Paths.get(name));
					Files.deleteIfExists(Paths.get(tenbai));
					Files.deleteIfExists(Paths.get(tenbai + ".exe"));
					Files.deleteIfExists(Paths.get(tenbai + ".pyc"));
					// delete file java NhanNT
					Files.deleteIfExists(Paths.get(tenbai + ".class"));
					continue;
				}

				boolean time = true;
				// Loop all of problem DangVTH
				for (int j = 0; j < parent.listProbName.size(); ++j) {

					StringBuilder result = new StringBuilder();
					// if problem exist on folder
					if (parent.listProbName.get(j).equalsIgnoreCase(problem)
							&& parent.listProbPath.get(j).contains("\\" + stuclass + "\\")) {
						File[] testPath = new File(parent.listProbPath.get(j)).listFiles(File::isDirectory);
						int k = 0;
						// Browse the testcase
						point = 0;
						maxTime = 0;
						for (File test : testPath) {
							// set status for process bar DangVTH
							parent.pro.getPrgJudging().setValue(k += 100 / testPath.length);
							// get all file of testcase DangVTH
							File[] inout = new File(test.getAbsolutePath()).listFiles(File::isFile);
							Arrays.sort(inout);
							// Loop all file of testcase DangVTH
							for (File file : inout) {
								// If it's an input file DangVTH
								if (file.getName().split("\\.")[1].equals("inp")) {
									// Copy file from submission DangVTH
									fhandle.copyFile(file.getPath(), file.getName());
								} else {// If it's an output file DangVTH
									try {
										if (time) {
											long start = System.currentTimeMillis();
											// Execute the solution
											time = run(tenbai, problem, type);
											String distance = String.valueOf(
													(double) (System.currentTimeMillis() - start) / 1000) + "s";
											// Calculate the time it takes to run out of testcase DangVTH
											maxTime = Math.max(maxTime, System.currentTimeMillis() - start);
											parent.pro.getTxtCompileRun().setText("Judging " + problem);
											// check time limit or memory limit
											if (!time) {
												result.append(test.getName()).append(": ").append(error).append("\n");
												setPoint(stuclass, getTotalPoint(stuclass, user),
														parent.hmStuIndex.get(user + stuclass),
														parent.hmTable.get(stuclass).getColumnCount() - 1);
												break;
											}
											// If the output is correct DangVTH
											if (fhandle.compareTwoFiles(file.getName(), file.getAbsolutePath())) {
												result.append(test.getName()).append(": Accepted. Time: ")
														.append(distance).append("\n");
												parent.pro.getTxtContent().setText(test.getName() + ": Accepted\n");
												point++;
											} else { // If the output is incorrect DangVTH
												result.append(test.getName()).append(": Wrong Answer. Time: ")
														.append(distance).append("\n");
												parent.pro.getTxtContent().setText(test.getName() + ": Wrong Answer\n");
											}
										}
									} catch (IOException ex) {
										Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
									}
								}
							}
						}

						// write the result
						DecimalFormat newFormat = new DecimalFormat("#.#");
						double points = Double.valueOf(newFormat.format((double) point / testPath.length * 10));
						writer.write(points + "\n");
						writer.write("Max time: " + maxTime + "\n");
						writer.write("Max memory: " + maxMemory + "\n");
						if (parent.checkFormat) {
							writer.write("Format: " + checkFormat + "\n");
						} else {
							writer.write("Format: null\n");
						}
						if (parent.checkCmt) {
							writer.write("Comment: " + checkCommet + "\n");
						} else {
							writer.write("Comment: -1\n");
						}
						if (true) {
							if (listPlagiarisms != null && listPlagiarisms.size() > 0) {
								writer.write(
										"Plagiarism: " + listPlagiarisms.get(0).getPercentageOfPlagiarism() + "\n");
								writer.write(listPlagiarisms.get(0).getNameOfFileHasPlagiarism() + "\n");
								writer.write((listPlagiarisms.size() - 1) + "\n");
								for (int idx = 1; idx < listPlagiarisms.size(); idx++) {
									writer.write(listPlagiarisms.get(idx).getOrigianal() + "\n");
									writer.write(listPlagiarisms.get(idx).getCompareWith() + "\n");
								}
							} else {
								writer.write("Plagiarism: 0\n");
							}
						}
						writer.write(result.toString());

						// Set point for problem column
						setPoint(stuclass, String.valueOf(points), parent.hmStuIndex.get(user + stuclass),
								parent.hmTable.get(stuclass).getColumn(parent.listProbName.get(j)).getModelIndex());
						// Set total point for user
						setPoint(stuclass, getTotalPoint(stuclass, user), parent.hmStuIndex.get(user + stuclass),
								parent.hmTable.get(stuclass).getColumnCount() - 1);
						break;
					}
				}
				// Delete excuted file

				Files.deleteIfExists(Paths.get(tenbai));
//                Files.deleteIfExists(Paths.get(tenbai + "." + type));
//                Files.deleteIfExists(Paths.get(tenbai + "." + type + ".orig"));
				Files.deleteIfExists(Paths.get(tenbai + ".exe"));
				Files.deleteIfExists(Paths.get(tenbai + ".pyc"));
				Files.deleteIfExists(Paths.get(tenbai + ".java"));
				Files.deleteIfExists(Paths.get(problem + ".inp"));
				Files.deleteIfExists(Paths.get(problem + ".out"));
				// if auto judging, delete solution in submissions folder DangVTH
				if (auto) {
					Files.deleteIfExists(Paths.get(folderPath.get(i)));
				}
			} catch (IOException ex) {
				Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					writer.close();
				} catch (IOException ex) {
					Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	/**
	 * Set points into table
	 *
	 * @param stuclass Name of contest
	 * @param point    Point
	 * @param i        Index of row
	 * @param j        Index of column
	 */
	private void setPoint(String stuclass, String point, int i, int j) {
		parent.hmTable.get(stuclass).setValueAt(point, i, j);
	}

	/**
	 * Get total point for user
	 *
	 * @param stuclass Name of contest
	 * @param user     Name of user
	 * @return Total point type of String
	 */
	private String getTotalPoint(String stuclass, String user) {
		double totalpoint = 0;
		// Set totalpoint for sum column
		for (int t = 1; t < parent.hmTable.get(stuclass).getColumnCount() - 1; ++t) {
			if (isNumeric(
					parent.hmTable.get(stuclass).getValueAt(parent.hmStuIndex.get(user + stuclass), t).toString())) {
				totalpoint += Double.parseDouble(
						parent.hmTable.get(stuclass).getValueAt(parent.hmStuIndex.get(user + stuclass), t).toString());
			}
		}
		return String.valueOf(totalpoint);
	}

	/**
	 * Check if string is numeric
	 *
	 * @param str String to check
	 * @return True if it's numeric, false if it isn't numeric
	 */
	public boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Compile submission file
	 *
	 * @param fileName Name of file without file extension
	 * @param problem  Name of problem
	 * @param type     File extension
	 * @param stuClass Name of contest
	 * @return True if compile successfully, false if compile not successfully
	 */
	public boolean compile(String fileName, String problem, String type, String stuClass) {
		error = "";
		String cmd = "";
		int exitCode = 0;
		String pathToSettingConfig = parent.problemDir + "\\" + stuClass + "\\config.txt";
		try {
			List<String> lines = Collections.emptyList();
			lines = Files.readAllLines(Paths.get(pathToSettingConfig), StandardCharsets.UTF_8);
			// set setting content to interface
			parent.timeLimit = Integer.parseInt(lines.get(0).split("=")[1]);
			parent.memoryLimit = Integer.parseInt(lines.get(1).split("=")[1]);
			parent.checkFormat = Boolean.parseBoolean(lines.get(2).split("=")[1]);
			parent.checkCmt = Boolean.parseBoolean(lines.get(3).split("=")[1]);
			parent.checkPlagiarism = Boolean.parseBoolean(lines.get(4).split("=")[1]);

			// Check format
			if (parent.checkFormat) {
				// Generate formatted file
				Formatter.Format(fileName + "." + type, type);
				// check file is exist
				if (Files.exists(Paths.get(fileName + "." + type + ".orig"))) { // 2 files are different, then generate
																				// new file.orig
                    fhandle.copyFile(fileName + "." + type + ".orig", fileName + "." + type);
                    Files.deleteIfExists(Paths.get(fileName + "." + type + ".orig"));
                    
                    String elseType = "";
                    if (type.equals("c") || type.equals("cpp")) {
						elseType = "java";
					} else {
						elseType = "cpp";
					}
                    Formatter.Format(fileName + "." + type, elseType);
                    if (Files.exists(Paths.get(fileName + "." + type + ".orig"))) {
						checkFormat = false;
					} else {
						checkFormat = true;
					}
				} else { // 2 files are the same
					checkFormat = true;
				}

			}
			// Calculate the percent of comments
			if (parent.checkCmt) {
				FunctionManagement fm = new FunctionManagement();
				checkCommet = fm.calculatePercentOfAllFunctionCmt(fileName + "." + type);

			}
			// check plagiarism
			if (parent.checkPlagiarism) {
				File[] listWorkspaceStudent = new File(parent.studentDir + "\\" + stuClass)
						.listFiles(File::isDirectory);
				ArrayList<String> listSolutionToCompare = new ArrayList<>();
				String currentSolutionToCompare = "";
				for (File f : listWorkspaceStudent) {
					File[] listStudentSolution = new File(f.getAbsolutePath()).listFiles(File::isFile);
					for (File solution : listStudentSolution) {
						currentSolutionToCompare = solution.getAbsolutePath();
						if (currentSolutionToCompare.contains(problem)) {
							listSolutionToCompare.add(currentSolutionToCompare);
						}
					}
				}
				listPlagiarisms = Plagiarismer.plagiarism(
						parent.studentDir + "\\" + stuClass + "\\" + getUser(fileName) + "\\" + fileName,
						listSolutionToCompare, type, parent);
			}
		} catch (IOException ex) {
			Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (!checkHack(problem, type, fileName + "." + type, parent.checkFormat, parent.checkCmt)) {
			return true;
		}
		if (type.equals("sql")) {
			Scanner scanner = null;
			try {
				scanner = new Scanner(new File(fileName + ".sql"));
				DBManagement db = new DBManagement();
				Connection conn = db.getConnection();
				Statement st = conn.createStatement();
				String bailam = scanner.useDelimiter("\\A").next();
				scanner.close();
				return runSql(bailam, st);
			} catch (IOException | SQLException | NoSuchElementException ex) {
				scanner.close();
				error = "SQL syntax: Empty command";
				return false;
			}
		} else {
			switch (type) {
			// get time
			case "cpp":
				cmd = compileCMD(fileName, type);
				break;
			case "c":
				cmd = compileCMD(fileName, type);
				break;
			case "py":
				cmd = parent.typepy + " -m compileall " + fileName + ".py -q -b";
				break;
			// judge Java NhanNT
			case "java":
				Runtime rTmp = Runtime.getRuntime();
				try {
					Process p = rTmp.exec("javac " + fileName + ".java");
					BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					BufferedReader br1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String s;
					boolean hasError = false;
					while ((s = br.readLine()) != null || (s = br1.readLine()) != null) {
						error += s + "\n";
						hasError = true;
					}
					if (hasError) {
						return false;
					}
					while (p.isAlive()) {
					}
				} catch (IOException ex) {
					Logger.getLogger(Judge.class.getName()).log(Level.SEVERE, null, ex);
				}
				cmd = "java " + fileName;
			}
			try {
				Runtime r = Runtime.getRuntime();
				Process p = r.exec(cmd);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				BufferedReader br1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String s;
				// get time
				while ((s = br.readLine()) != null || (s = br1.readLine()) != null) {
					error += s + "\n";
				}
				exitCode = p.waitFor();

			} catch (IOException | InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		return exitCode == 0;
	}

	/**
	 * Get String compile command
	 *
	 * @param problem
	 * @param type
	 * @return
	 */
	public String compileCMD(String problem, String type) {
		String compilecmd = "";
		String Wall = "";
		if (parent.checkWall) {
			Wall = " -Wall";
		}
		switch (parent.ostype) {
		case Windows:
			if (type.equals("cpp")) {
				compilecmd = parent.typecpp + Wall + " -o " + problem + ".exe " + problem + ".cpp";
			} else {
				compilecmd = parent.typec + Wall + " -o " + problem + ".exe " + problem + ".c";
			}
			break;
		case MacOS:
			break;
		case Linux:
			if (type.equals("cpp")) {
				compilecmd = parent.typecpp + " -Wall -o " + problem + " " + problem + ".cpp";
			} else {
				compilecmd = parent.typec + " -Wall -o " + problem + " " + problem + ".c";
			}
			break;
		case Other:
			break;
		}
		return compilecmd;
	}

	/**
	 * Run executable file
	 *
	 * @param tenbai
	 * @param problem
	 * @param type
	 * @return
	 */
	public boolean run(String tenbai, String problem, String type) {
		String cmd = "";
		if (type.equals("sql")) {
			Scanner scanner = null;
			try {
				scanner = new Scanner(new File(problem + ".inp"));
				String inp = scanner.useDelimiter("\\A").next();
				scanner.close();

				DBManagement db = new DBManagement();
				Connection conn = db.getConnection();
				Statement st = conn.createStatement();
				String[] array = inp.split("\\;", -1);
				String sql = insertString(array[0], "TEMPORARY ", 6);
				st.executeUpdate(sql);
				sql = array[1];
				st.executeUpdate(sql);
				scanner = new Scanner(new File(tenbai + ".sql"));
				String bailam = scanner.useDelimiter("\\A").next();
				scanner.close();

				return runSql(bailam, st);
			} catch (IOException | SQLException | NoSuchElementException ex) {
				scanner.close();
				return false;
			}
		} else {
			switch (type) {
			case "cpp":
			case "c":
				cmd = runCMD(tenbai);
				break;
			case "py":
				cmd = parent.typepy + " " + tenbai + ".py";
				break;
			}

			try {
				Runtime r = Runtime.getRuntime();
				Process p = r.exec(cmd);
				boolean exitCode = p.waitFor(parent.timeLimit, TimeUnit.MILLISECONDS);
				if (!exitCode) {
					p.destroyForcibly();
					error = "Time Limit Exceeded";
					return false;
				}
				if (!checkMemory(r, parent.memoryLimit)) {
					p.destroyForcibly();
					error = "Memory Limit Exceeded";
					return false;
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				BufferedReader br1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String s;
				while ((s = br.readLine()) != null || (s = br1.readLine()) != null) {
					error += s + "\n";
				}
				if (error.length() > 0) {
					return false;
				}
			} catch (IOException | InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}

		return true;
	}

	/**
	 * Get String executable file
	 *
	 * @param problem
	 * @return
	 */
	public String runCMD(String problem) {
		String runcmd = "";
		switch (parent.ostype) {
		case Windows:
			runcmd = problem + ".exe";
			break;
		case MacOS:
			break;
		case Linux:
			runcmd = "./" + problem;
			break;
		case Other:
			break;
		}
		return runcmd;
	}

	/**
	 * Get student class
	 *
	 * @param s
	 * @return
	 */
	public String getStuClass(String s) {
		s = s.substring(1);
		try {
			String pattern = "\\]\\[";
			String[] lsuser = s.split(pattern);
			String user = lsuser[lsuser.length - 3];
			return user;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return "";
	}

	/**
	 * Get username
	 *
	 * @param s
	 * @return
	 */
	public String getUser(String s) {
		try {
			String pattern = "\\]\\[";
			String[] lsuser = s.split(pattern);
			String user = lsuser[lsuser.length - 2];
			return user;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return "";
	}

	/**
	 * Get problem
	 *
	 * @param s
	 * @return
	 */
	public String getProblem(String s) {
		try {
			String pattern = "\\]\\[";
			String[] lsprob = s.split(pattern);
			String[] lsprob1 = lsprob[lsprob.length - 1].split("\\]\\.");
			String prob = lsprob1[0];
			return prob;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return "";
	}

	/**
	 * get type submission file
	 *
	 * @param s
	 * @return
	 */
	public String getType(String s) {
		return s.split("\\.")[1];
	}

	/**
	 * Excute Judge DangVTH
	 *
	 * @param NopbaiPath
	 * @param NopbaiName
	 * @param auto
	 */
	public void foo(ArrayList<String> NopbaiPath, ArrayList<String> NopbaiName, boolean auto) {
		if (NopbaiPath.isEmpty() || NopbaiName.isEmpty()) {
			return;
		}
		parent.pro = new frmProcess(parent);
		parent.pro.setVisible(true);
		parent.judge = new Thread() {
			@Override
			public void run() {
				parent.btnJudgeAContest.setEnabled(false);
				parent.btnUpdateOnline.setEnabled(false);
				judge(NopbaiPath, NopbaiName, auto);
				parent.pro.setVisible(false);
				parent.btnJudgeAContest.setEnabled(true);
				parent.btnUpdateOnline.setEnabled(true);
			}
		};
		parent.timer = new Thread() {
			@Override
			public void run() {
				int timecnt = 0;
				while (true) {
					if (!parent.judge.isAlive()) {
						stop();
						break;
					}
					try {
						parent.pro.getTxtTime().setText(secondtoTime(timecnt++));
						sleep(1000);
					} catch (InterruptedException ex) {
						Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}

		};
		parent.judge.start();
		parent.timer.start();
	}

	/**
	 * Convert second to formatted time
	 *
	 * @param second
	 * @return formatted time
	 */
	private String secondtoTime(int second) {
		int h = second / 3600;
		int m = (second / 60) % 60;
		int s = second % 60;
		return String.format("%02d:%02d:%02d", h, m, s);
	}

	/**
	 * Check hack
	 *
	 * @param problem
	 * @param type
	 * @param file
	 * @param checkFunc
	 * @param checkCmt
	 * @return
	 */
	private boolean checkHack(String problem, String type, String file, boolean checkFormat, boolean checkCmt) {
		FileReader fr = null;
//        int cntFunc = 0;
		int cntCmt = 0;
		try {
			File f = new File(file); // creates a new file instance
			fr = new FileReader(f); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			String line;
			String string = "";
			boolean flag = false;
			while ((line = br.readLine()) != null) {
				if (line.contains("fopen")) {
					fr.close();
					error = "Function \"fopen\" is not allow";
					return false;
				}
				if (line.contains("system")) {
					fr.close();
					error = "Function \"system\" is not allow";
					return false;
				}
				if (containsInorgeCase(line, "DROP")) {
					fr.close();
					error = "Function \"DROP\" is not allow";
					return false;
				}
				if (containsInorgeCase(line, "CREATE")) {
					fr.close();
					error = "Function \"CREATE\" is not allow";
					return false;
				}
//                if (checkFunc) {
//                    if (isFunction(line)) {
//                        cntFunc++;
//                    }
//                }

//                if (checkCmt) {
//                    if (isCmt(line)) {
//                        cntCmt++;
//                    }
//                }
				string += line + "\n";
				if (line.contains("main")) {
					if (line.contains("{")) {
						string += "\n    freopen(\"" + problem + ".inp\", \"r\", stdin);\n" + "    freopen(\"" + problem
								+ ".out\", \"w\", stdout);";
					} else {
						flag = true;
					}
				}
				if (flag && line.contains("{")) {
					string += "\n    freopen(\"" + problem + ".inp\", \"r\", stdin);\n" + "    freopen(\"" + problem
							+ ".out\", \"w\", stdout);";
					flag = false;
				}
			}
			fr.close();
			FileWriter writer = new FileWriter(file);
			writer.write(string);
			writer.close();
		} catch (IOException ex) {
			Logger.getLogger(Judge.class.getName()).log(Level.SEVERE, null, ex);
		}
//        if (checkFunc && cntFunc < 1) {
//            error = "You don't have any function in code";
//            return false;
//        }
//        if (checkCmt && cntCmt < 1) {
//            error = "You don't have any comment in code";
//            return false;
//        }
		return true;
	}

	/**
	 * Check if it's a function DangVTH
	 *
	 * @param line
	 * @return
	 */
	private boolean isFunction(String line) {
		String[] arr = new String[] { "boolean", "char", "double", "float", "int", "long", "string", "void" };
		return Arrays.stream(arr).anyMatch(line::contains) && !line.endsWith(";") && !line.contains("main");
	}

	/**
	 * Check if it's a comment DangVTH
	 *
	 * @param line
	 * @return
	 */
	private boolean isCmt(String line) {
		String[] arr = new String[] { "//", "/*" };
		return Arrays.stream(arr).anyMatch(line::contains) && !line.endsWith(";");
	}

	/**
	 * Check if there is a memory overflow DangVTH
	 *
	 * @param r
	 * @param mem
	 * @return
	 */
	private boolean checkMemory(Runtime r, int mem) {
		maxMemory = Math.max(maxMemory, ((r.totalMemory() - r.freeMemory()) / 1024 / 1024));
		return ((r.totalMemory() - r.freeMemory()) / 1024 / 1024) <= mem;
	}

	/**
	 * Insert String to other String by index DangVTH
	 *
	 * @param originalString
	 * @param stringToBeInserted
	 * @param index
	 * @return
	 */
	private String insertString(String originalString, String stringToBeInserted, int index) {
		// Create a new string
		String newString = originalString.substring(0, index + 1) + stringToBeInserted
				+ originalString.substring(index + 1);
		// return the modified String
		return newString;
	}

	/**
	 * Check if the string contains a different string DangVTH
	 *
	 * @param original
	 * @param tobeChecked
	 * @return
	 */
	private boolean containsInorgeCase(String original, String tobeChecked) {
		return original.toLowerCase().contains(tobeChecked.toLowerCase());
	}

	/**
	 * Excute SQL statement DangVTH
	 *
	 * @param sql
	 * @param st
	 * @return
	 */
	public boolean runSql(String sql, Statement st) {
		long start = System.currentTimeMillis();
		try {
			String[] array = sql.split("\\;", -1);
			ResultSet rs = null;
			for (int i = 0; i < array.length; ++i) {
				sql = array[i];
				if (containsInorgeCase(sql, "select")) {
					rs = st.executeQuery(sql);
					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();
					FileWriter writer = new FileWriter("A.out");

					for (int j = 1; j <= columnCount; j++) {
						String col_name = metaData.getColumnName(j);
						writer.write(col_name);
					}
					while (rs.next()) {
						for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
							Object object = rs.getObject(columnIndex);
							writer.write(object == null ? "NULL" : object.toString());
						}
					}
					writer.close();
				} else {
					st.executeUpdate(sql);
				}
			}
		} catch (SQLException | IOException ex) {
			error = ex.getMessage();
		}
		if (error.contains("syntax")) {
			return false;
		} else if ((System.currentTimeMillis() - start) > parent.timeLimit) {
			error = "Time Limit Exceeded";
			return false;
		}
		error = "";

		return true;
	}
}
