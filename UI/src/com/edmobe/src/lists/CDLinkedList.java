package com.edmobe.src.lists;

import java.util.Random;

public class CDLinkedList<T> extends LinkedList<T>{

	private Node<T> end;

	public CDLinkedList() {
		head = null;
		end = null;
		length = 0;
	}

	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void add(T object) {
		Node<T> tmp = new Node<T>(object, null, null);
		if (head == null) {
			tmp.next = tmp;
			tmp.previous = tmp;
			head = tmp;
			end = head;
		} else {
			tmp.previous = end;
			end.next = tmp;
			head.previous = tmp;
			tmp.next = head;
			end = tmp;
		}
		length++;
	}

	@Override
	public void addFirst(T object) {

		Node<T> tmp = new Node<T>(object, null, null);

		if (head == null) {
			tmp.next = tmp;
			tmp.previous = tmp;
			head = tmp;
			end = head;
		} else {
			tmp.previous = end;
			end.next = tmp;
			head.previous = tmp;
			tmp.next = head;
			head = tmp;
		}

		length++;

	}
	
	public void addSecond (T object) { // validations are not required because is an internal method.
		Node<T> tmp = new Node<T>(object, head.next, head);
		head.next = tmp;
		tmp.next.previous = tmp;
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
		
		while(tmp.next != head && tmp.data != object) {
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
	public void print() {
		Node<T> tmp = head;

		if (isEmpty()) {
			System.out.print("");
			return;
		}

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
	
	public void setHead(Node<T> head) {
		this.head = head;
	}
	
	public Node<T> getHead() {
		return head;
	}
	
	public Node<T> getEnd() {
		return end;
	}

}