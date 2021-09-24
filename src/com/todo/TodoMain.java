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
			String str = sc.nextLine();
			String[] choice = str.split(" ");
			switch (choice[0]) {

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
				System.out.println("제목 순으로 나열됩니다");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목 역순으로 나열됩니다");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("입력 순으로 나열됩니다");
				isList = true;
				break;

			case "exit":
				quit = true;
				System.out.print("프로그램이 종료됩니다\n");
				break;
			
			case "help":
				Menu.displaymenu();
				break;
			
			case "find":
				TodoUtil.findList(l,choice[1]);
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("제목 역순으로 나열됩니다");
				isList = true;
				break;
				
			case "find_cate":
				TodoUtil.findcateList(l,choice[1]);
				break;
				
			case "ls_cate":
				
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
