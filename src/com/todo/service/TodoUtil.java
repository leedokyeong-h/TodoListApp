package com.todo.service;

import java.io.*;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "    항목 추가\n\n"
				+ "제목을 입력해 주세요 ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("중복된 제목입니다!\n");
			return;
		}
		sc.nextLine();
		System.out.print("설명을 입력해 주세요 ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("항목이 추가되었습니다!\n");
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\n"
				+ "    항목 삭제\n\n"
				+ "삭제하실 항목의 제목을 입력해 주세요 ");
		String title = sc.next();
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		System.out.print("항목이 삭제되었습니다!\n");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "    항목 편집\n\n"
				+ "바꾸고 싶은 항목의 제목을 입력해 주세요 ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("입력하신 제목은 존재하지 않습니다\n");
			return;
		}

		System.out.print("새로운 제목을 입력해 주세요 ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("중복된 제목입니다!");
			return;
		}
		sc.nextLine();
		System.out.print("새로운 설명을 입력해 주세요 ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 편집되었습니다\n");
			}	
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n   전체 항목");
		for (TodoItem item : l.getList()) {
			System.out.println("제목: " + item.getTitle() + "  설명:  " + item.getDesc());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				System.out.println(item.toSaveString());
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("\ntxt 파일에 저장되었습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			while((oneline = br.readLine()) !=null) {
				StringTokenizer st = new StringTokenizer(oneline,"##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(title, desc,current_date);
				l.addItem(t);
			}
			br.close();
			System.out.println("파일 업로드가 완료되었습니다");
		}catch (FileNotFoundException e) {
			System.out.println("업로드할 파일이 없습니다");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
