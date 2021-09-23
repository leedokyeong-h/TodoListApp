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
				System.out.printf("제목 순으로 나열됩니다");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.printf("제목 역순으로 나열됩니다");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.printf("입력 순으로 나열됩니다");
				isList = true;
				break;

			case "exit":
				quit = true;
				System.out.printf("프로그램이 종료됩니다");
				break;
			
			case "help":
				Menu.displaymenu();
				break;

			default:
				System.out.println("해당 명령어가 없습니다. 다시 입력해 주세요! [ 기능 보기 ( help ) ]");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l,"todolist.txt");
	}
}
