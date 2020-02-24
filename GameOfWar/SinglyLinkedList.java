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
		// if list empty, make head and tail the new node
		if(head == null)
			head = tail = newNode;
		// or else add at end of list and assign tail to new node
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
		
		// if index not within bounds
		if(index < 0 || index > size())
			throw new NoSuchElementException();
		
		// if want to add as head
		if(index == 0) {
			newNode.setNext(head);
			head = newNode;
		}
		// if index is at end, can just use previous add method to add
		// at the end of the list
		else if(index == size()) {
			add(obj);
		}
		// adds in the middle of the list
		else {
			newNode.setNext(get(index));
			get(index-1).setNext(newNode);
		}
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		ListNode<E> node = head;
		int counter = 0;
		// count number of nodes until end is reached
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
		ListNode<E> node = head;
		// cycle through nodes until node at index is reached
		for(int a = 0; a < index; a++) {
			if(node.getNext() == null)
				throw new NoSuchElementException();
			node = node.getNext();
		}
		return node;
	}
	
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
		// make sure index is in bounds
		if(index < 0 || index >= size() || size() == 0)
			throw new NoSuchElementException();
		
		// if removing the head
		if(index == 0) {
			// save value of current head
			E value = head.getValue();
			if(size() > 1)
				head = get(index+1);
			else
				head = null;
			return value;
		}
		// make node equal to the previous node of the one to remove,
		// and then set node equal to the next to next node, removing
		// the node at the index specified
		node = get(index-1);
		E value = node.getNext().getValue();
		node.setNext(node.getNext().getNext());
		
		return value;
	}

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