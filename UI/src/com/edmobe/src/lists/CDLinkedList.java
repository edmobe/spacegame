package com.edmobe.src.lists;

import java.util.Random;

import com.edmobe.src.objects.Enemy;

/**
 * Circular double linked list
 * 
 * @author edmobe
 *
 * @param <T>
 *            generic object instance
 */
public class CDLinkedList<T> extends LinkedList<T> {

	private Node<T> end; // last node

	public CDLinkedList() {
		head = null;
		end = null;
		length = 0;
	}

	/**
	 * Adds an object at the end of the list
	 */
	@Override
	public void add(T object) {
		Node<T> tmp = new Node<T>(object, null, null); // creates a new node with the object
		if (head == null) {
			/*
			 * creates a new head/end
			 */
			tmp.next = tmp;
			tmp.previous = tmp;
			head = tmp;
			end = head;
		} else {
			/*
			 * sets the new end
			 */
			tmp.previous = end;
			end.next = tmp;
			head.previous = tmp;
			tmp.next = head;
			end = tmp;
		}

		length++; // increases the length of the list
	}

	@Override
	public void addFirst(T object) {

		Node<T> tmp = new Node<T>(object, null, null); // creates a new node with the object

		if (head == null) {
			/*
			 * creates a new head/end
			 */
			tmp.next = tmp;
			tmp.previous = tmp;
			head = tmp;
			end = head;
		} else {
			/*
			 * sets the new head
			 */
			tmp.previous = end;
			end.next = tmp;
			head.previous = tmp;
			tmp.next = head;
			head = tmp;
		}

		length++; // increases the length of the list

	}

	@Override
	public void remove(T object) {

		if (head == null || object == null) {
			throw new RuntimeException("Cannot delete");
		}

		if (head.data.equals(object)) {
			if (length == 1) {
				head = null;
				end = null;
				length = 0;
			} else {
				head = head.next;
				end.next = head;
				head.previous = end;
				length--;
			}

			return;
		}

		if (end.data.equals(object)) {
			end = end.previous;
			head.previous = end;
			end.next = head;
			length--;
			return;
		}

		Node<T> tmp = head;

		while (tmp.next != head && tmp.data != object) {
			tmp = tmp.next;
		}

		if (tmp.data == object) {
			tmp.previous.next = tmp.next;
			tmp.next.previous = tmp.previous;
			length--;
		} else {
			throw new RuntimeException("Cannot delete");
		}
	}

	@Override
	public T get(int pos) {
		int actPos = 0;
		Node<T> tmp = head;

		while (actPos != pos) {
			tmp = tmp.next;
			actPos++;
		}

		return tmp.data;
	}

	@Override
	public void print() { // for debugging
		Node<T> tmp = head;

		if (isEmpty()) {
			System.out.print("");
			return;
		}

		if (tmp.data instanceof Enemy) {

			if (head.next == head) {
				System.out.println(((Enemy) head.data).health + " <-> " + ((Enemy) tmp.data).health);
				return;
			}

			System.out.print(((Enemy) head.data).health + " <-> ");
			tmp = head.next;

			while (tmp.next != head) {
				System.out.print(((Enemy) tmp.data).health + " <-> ");
				tmp = tmp.next;
			}

			System.out.print(((Enemy) tmp.data).health + " <-> ");

			tmp = tmp.next;

			System.out.println(((Enemy) tmp.data).health);

		} else {
			if (head.next == head) {
				System.out.println(head.data + " <-> " + tmp.data);
				return;
			}

			System.out.print(head.data + " <-> ");
			tmp = head.next;

			while (tmp.next != head) {
				System.out.print(tmp.data + " <-> ");
				tmp = tmp.next;
			}

			System.out.print(tmp.data + " <-> ");

			tmp = tmp.next;

			System.out.println(tmp.data);
		}

	}

	@Override
	public void shuffle() {
		CDLinkedList<T> tmpList = new CDLinkedList<T>();

		Random random = new Random();

		while (length != 0) {

			T object = this.get(random.nextInt(length));

			tmpList.add(object);

			this.remove(object);

		}

		Node<T> tmp = tmpList.head;

		while (tmpList.size() != 0) {
			this.add(tmp.data);
			Node<T> tmp2 = tmp;
			tmpList.remove(tmp2.data);
			tmp = tmp2.next;
		}
	}

	@Override
	public void clear() {
		head = null;
		end = null;
		length = 0;
	}

	public void setEnd(Node<T> end) {
		this.end = end;
	}

	public Node<T> getEnd() {
		return end;
	}

}