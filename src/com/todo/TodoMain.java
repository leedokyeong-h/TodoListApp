package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l,"todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				System.out.printf("���� ������ �����˴ϴ�");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.printf("���� �������� �����˴ϴ�");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.printf("�Է� ������ �����˴ϴ�");
				isList = true;
				break;

			case "exit":
				quit = true;
				System.out.printf("���α׷��� ����˴ϴ�");
				break;
			
			case "help":
				Menu.displaymenu();
				break;

			default:
				System.out.println("�ش� ��ɾ �����ϴ�. �ٽ� �Է��� �ּ���! [ ��� ���� ( help ) ]");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l,"todolist.txt");
	}
}
