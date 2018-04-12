package com.edmobe.src.lists;

public class Node<T> {
	public T data;
	public Node<T> next;
	public Node<T> previous;

	public Node(T data, Node<T> next, Node<T> previous) {
		this.data = data;
		this.next = next;
		this.previous = previous;
	}
	
}
