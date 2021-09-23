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
				+ "    �׸� �߰�\n\n"
				+ "������ �Է��� �ּ��� ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("�ߺ��� �����Դϴ�!\n");
			return;
		}
		sc.nextLine();
		System.out.print("������ �Է��� �ּ��� ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("�׸��� �߰��Ǿ����ϴ�!\n");
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\n"
				+ "    �׸� ����\n\n"
				+ "�����Ͻ� �׸��� ������ �Է��� �ּ��� ");
		String title = sc.next();
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		System.out.print("�׸��� �����Ǿ����ϴ�!\n");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "    �׸� ����\n\n"
				+ "�ٲٰ� ���� �׸��� ������ �Է��� �ּ��� ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�Է��Ͻ� ������ �������� �ʽ��ϴ�\n");
			return;
		}

		System.out.print("���ο� ������ �Է��� �ּ��� ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�ߺ��� �����Դϴ�!");
			return;
		}
		sc.nextLine();
		System.out.print("���ο� ������ �Է��� �ּ��� ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�׸��� �����Ǿ����ϴ�\n");
			}	
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n   ��ü �׸�");
		for (TodoItem item : l.getList()) {
			System.out.println("����: " + item.getTitle() + "  ����:  " + item.getDesc());
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
			System.out.println("\ntxt ���Ͽ� ����Ǿ����ϴ�.");
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
			System.out.println("���� ���ε尡 �Ϸ�Ǿ����ϴ�");
		}catch (FileNotFoundException e) {
			System.out.println("���ε��� ������ �����ϴ�");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
