package com.edmobe.src.lists;

public class Node<T> {
	public T data;
	public Node<T> next;
	
	public Node(T data, Node next){
		this.data = data;
		this.next = next;
	}
}
