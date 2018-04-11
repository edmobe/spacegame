package com.edmobe.src;

import com.edmobe.src.lists.CDLinkedList;
import com.edmobe.src.lists.LinkedList;

public class Test {

	public static void main(String[] args) {
		
		/*
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.print();
		
		System.out.println("");
		l.shuffle();
		*/
		CDLinkedList<Integer> l = new CDLinkedList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.print();
		System.out.println("");
		
		//l.remove(3);
		l.shuffle();
		l.print();
		
		String a = "hola";
		
	}

}
