/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.util.ArrayList;

/**
 *
 * @author DangVTH
 */
public class MyStack {

	private ArrayList<String> stackArray;
	private int top;

	/**
	 * Initialization
	 */
	public MyStack() {
		stackArray = new ArrayList<>();
		top = -1;
	}

	/**
	 * add item to stack
	 *
	 * @param j
	 */
	public void push(String j) {
		try {
			stackArray.set(++top, j);
		} catch (Exception e) {
			stackArray.add(j);
		}
	}

	/**
	 * delete item from stack
	 *
	 * @return String
	 */
	public String pop() {
		return stackArray.get(top--);
	}

	/**
	 * Get item on the top of stack
	 *
	 * @return String
	 */
	public String peek() {
		return stackArray.get(top);
	}

	/**
	 * Check status of stack
	 *
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (top == -1);
	}

}
