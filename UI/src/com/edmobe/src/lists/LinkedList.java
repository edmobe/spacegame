package com.edmobe.src.lists;

import java.util.Random;

public class LinkedList<T> {
	protected Node<T> head; // the first node of the list.
	protected int length; // length of the list.

	public LinkedList() {
		head = null;
		length = 0;
	}

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

	public void addFirst(T object) {
		head = new Node<T>(object, head, null); // the head will be a new node.
		length = 1; // the list will now have one object.
	}

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

	public void shuffle() {
		LinkedList<T> tmpList = new LinkedList<T>();

		Random random = new Random();

		while (length != 0) {

			T object = this.get(random.nextInt(length));

			tmpList.add(object);

			this.remove(object);

		}

		Node<T> tmp = tmpList.head;

		while (tmp != null) {
			this.add(tmp.data);
			tmp = tmp.next;
		}

	}

	public int size() {
		return length; // returns the size of the list
	}

	public void clear() {
		head = null;
		length = 0;
	}
}
