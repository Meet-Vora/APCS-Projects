/**
 * ArrayStack.java
 * 
 * Copyright 2019  < >
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * Simple Stack using ArrayList
 * @author Meet Vora
 * @since  Feb 17, 2019
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E>{
	
	private List<E> elements;
	
	public ArrayStack(){
		elements = new ArrayList<E>();
	}
	
	/**
	 * @return True if stack is empty, vice versa
	 */
	 public boolean isEmpty(){return elements.isEmpty();}
	 
	 /** @return the top element on the stack.
	  * Throws the EmptyStackException*/
	 public E peek() {
		if(elements.isEmpty())
			throw new EmptyStackException();
		return elements.get(elements.size() - 1);
	 }
	 
	 /** @param obj to push onto the top(actually bottom of ArrayList) 
	  * of the stack
	  */
	  public void push(E obj){elements.add(obj);}
	  
	  /**@return the obj removed from the top stack*/
	  public E pop() {
		  if(elements.isEmpty())
			throw new EmptyStackException();
		return elements.remove(elements.size() -1);
	  }

}

