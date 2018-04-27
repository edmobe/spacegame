package com.edmobe.src.lists;

import java.util.Random;

/**
 * Doubly linked list
 * 
 * @author edmobe
 *
 * @param <T>
 *            generic object instance
 */
public class LinkedList<T> {
	protected Node<T> head; // the first node of the list.
	protected int length; // length of the list.

	public LinkedList() {
		head = null;
		length = 0;
	}

	/**
	 * Indicates if the list is empty
	 * 
	 * @return boolean that is true if the list is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Adds a node at the end of the list
	 * 
	 * @param object
	 *            data of the node that will be added
	 */
	public void add(T object) {
		if (head == null) {
			addFirst(object);
		} // if head is null, the list has no objects.
		else {
			Node<T> tmp = head; // a temporal node will be the head.
			while (tmp.next != null) {
				tmp = tmp.next;
			} // goes to the end of the list.
			tmp.next = new Node<T>(object, null, tmp); // at the end of the list, a new node is created.

			length++; // increases by 1 the length of the list.
		}
	}

	/**
	 * Adds a node at the start of the list
	 * 
	 * @param object
	 *            data of the node that will be added
	 */
	public void addFirst(T object) {
		head = new Node<T>(object, head, null); // the head will be a new node.
		length = 1; // the list will now have one object.
	}

	/**
	 * Removes the specified object of the list
	 * 
	 * @param object
	 *            data of the object that has to be deleted
	 */
	public void remove(T object) {
		if (head == null || object == null) {
			throw new RuntimeException("Cannot delete");
		} // when the list has no objects or the object is null, there is nothing to
			// remove.

		if (head.data.equals(object)) { // if the node to be removed is the head.
			head = head.next; // the head will be the second object of the list.
			length--; // the length will decrease by 1.
			return; // stops.
		}

		Node<T> cur = head; // current temporal node.

		while (cur != null && !cur.data.equals(object)) { // while the data of the node is different
			// from the object that has to be deleted, and the current temporal node is not
			// null
			// (which means the object was not found).
			cur = cur.next;
		}

		if (cur == null) {
			throw new RuntimeException("Cannot delete");
		} // if the object was not found.

		cur.previous.next = cur.next; // skips current node which, deletes it.

		if (cur.next != null) {
			cur.next.previous = cur.previous;
		}

		length--; // the length decreases by 1.
	}

	/**
	 * Gets the object at the specified index of the list
	 * 
	 * @param pos
	 *            specified index
	 * @return object at index
	 */
	public T get(int pos) {
		if (head == null)
			throw new IndexOutOfBoundsException(); // if the list if empty.

		Node<T> tmp = head; // goes through the list.
		for (int i = 0; i < pos; i++) {
			tmp = tmp.next;
		} // goes to the node that corresponds to
			// the specified position.

		if (tmp == null)
			throw new IndexOutOfBoundsException(); // if the position is out of
		// bounds.

		return tmp.data; // returns the object in the specified position.
	}

	/**
	 * Prints the doubly linked list
	 */
	public void print() { // for debugging
		if (head == null) {
			System.out.println("EMPTY");
		} else {
			Node<T> tmp = head;

			while (tmp != null) {
				System.out.print(tmp.data + "->");
				tmp = tmp.next;
			}
		}
	}

	/**
	 * Shuffles the list
	 */
	public void shuffle() {
		LinkedList<T> tmpList = new LinkedList<T>(); // temporal linked list

		Random random = new Random(); // random object

		while (length != 0) { // while the list is not empty

			T object = this.get(random.nextInt(length)); // temporal object

			tmpList.add(object); // adds a random object from the list to the temporal list

			this.remove(object); // removes the temporal object from the list

		}

		Node<T> tmp = tmpList.head; // temporal node

		while (tmp != null) { // moves through the temporal list
			this.add(tmp.data); // adds every object of the temporal list
			tmp = tmp.next;
		}

	}

	/**
	 * Gets the size of the list
	 * 
	 * @return length of the list
	 */
	public int size() {
		return length; // returns the size of the list
	}

	/**
	 * Clears the list
	 */
	public void clear() {
		head = null;
		length = 0;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public Node<T> getHead() {
		return head;
	}
}
