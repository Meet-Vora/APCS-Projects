import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - (description)
 *
 *	@author	Meet Vora
 *	@since	April 1st, 2019
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {	head = tail = null; }
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		head = tail = null;
		for(int a = 0; a < size(); a++) {
			add(oldList.get(a).getValue());
		}
	}
	
	/**	Clears the list of elements */
	public void clear() {
		head = tail = null;
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		ListNode<E> newNode = new ListNode<E>(obj);
		if(head == null)
			head = tail = newNode;
		else {
			tail.setNext(newNode);
			tail = newNode;
		}
		return true;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		ListNode<E> newNode = new ListNode<E>(obj);
		
		if(index < 0 || index > size())
			throw new NoSuchElementException();
		
		// if want to add as head
		if(index == 0) {
			newNode.setNext(head);
			head = newNode;
		}
		else if(index == size()) {
			add(obj);
		}
		// else if(index == size() - 1) {
		// 	newNode.setNext();
		// }
		else {
			newNode.setNext(get(index));
			get(index-1).setNext(newNode);
		}
		return true;
	}
		/* 	
		ListNode<E> newNode = new ListNode<E>(obj);
		ListNode<E> temp = head;
		
		//if((head == null && index > 0) || temp.getNext() == null)
			//throw NoSuchElementException;	// is this syntax correct?
		else {
			for(int a = 0; a < index; a++)
				temp.getNext();
			temp.setNext(newNode)
			temp.getNext();
				
			
			 3 4 5 6 8 9
			
			// 1 ----> 3
			// 1 ----> 2 ----> 3
		}
		return false;
	}
	*/
	
	/**	@return the number of elements in this list */
	public int size() {
		ListNode<E> node = head;
		int counter = 0;
		// System.out.println(node);
		while(node != null) {
			counter++;
			node = node.getNext();
		}
		return counter;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		// System.out.println(head);
		ListNode<E> node = head;
		for(int a = 0; a < index; a++) {
			if(node.getNext() == null)
				throw new NoSuchElementException();
			node = node.getNext();
		}
		return node;
	}
		
		/*
		int counter = 0;
		// if(head == null)
			// return null;
		
		while(node.getNext() != null) {
			if(index == counter)
				return node;
			counter++;
			node = node.getNext();
		}
		throw new NoSuchElementException("ERROR: No element found at index " + counter);
	}
	* */


		// for(int a = 0; a < index; a++) {
		// 	if(node.getNext() == null)
		// 		throw new NoSuchElementException("ERROR: No element found at index " + a);
		// 	else
		// 		node = node.getNext();	
		// }	
		// return node;
	// }
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		E value = get(index).getValue();
		get(index).setValue(obj);	
		return value;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		ListNode<E> node = head;
		if(index < 0 || index >= size() || size() == 0)
			throw new NoSuchElementException();
		// ListNode<E> current = get(index);
		
		// if removing the head
		if(index == 0) {
			E value = head.getValue();
			if(size() > 1)
				head = get(index+1);
			else
				head = null;
			return value;
		}
		node = get(index-1);
		E value = node.getNext().getValue();
		node.setNext(node.getNext().getNext());
		
		return value;
	}



	// 		E value = get(index).getValue();
	// 		// get(index).setNext(null);
	// 		if(size() > 1)
	// 			head = get(index+1);
	// 		else
	// 			head = null;
	// 	}


	// 	// if removing the tail
	// 	else if(index == size() - 1) {
	// 		get(index-1).setNext(null);	
	// 		tail = get(index-1);
	// 	}
	// 	// if removing any other node
	// 	else {
	// 		get(index-1).setNext(get(index+1));
	// 		get(index).setNext(null);
	// 	}
	// 	return value;  
	// }
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		// return size() == 0;
		return head == null;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		return indexOf(object) != -1;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		for(int a = 0; a < size(); a++) {
			if(get(a).getValue().equals(element))		// not sure if '==' works... Maybe '.equals()'?
				return a;
		}
		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	

}