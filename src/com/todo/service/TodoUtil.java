package com.todo.service;

import java.io.*;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String category,title, desc,due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "    �׸� �߰�\n\n"
				+ "ī�װ��� �Է��� �ּ��� ");
		category = sc.next();
		
		System.out.print("������ �Է��� �ּ��� ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("�ߺ��� �����Դϴ�!\n");
			return;
		}
		sc.nextLine();
		System.out.print("������ �Է��� �ּ��� ");
		desc = sc.nextLine();
		System.out.print("�������� �Է��� �ּ��� (��/��/��)");
		due_date = sc.next();
		TodoItem t = new TodoItem(title, desc,category,due_date);
		list.addItem(t);
		System.out.println("�׸��� �߰��Ǿ����ϴ�!\n");
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\n"
				+ "    �׸� ����\n\n"
				+ "�����Ͻ� �׸��� ��ȣ�� �Է��� �ּ��� ");
		int index = sc.nextInt();
		if(l.size()+1<index) {
			System.out.print("�Է��Ͻ� �׸��� ��ȣ�� �������� �ʽ��ϴ�.\n");
			return;
		}
		else {
			for (TodoItem item : l.getList()) {
				if (l.indexOf(item) == (index-1)) {
					System.out.println(index+". ["+item.getCategory()+ "] - " + item.getTitle() + " - " + item.getDesc() +" - "+item.getDue_date()+" - "+item.getCurrent_date());
					System.out.print("�� �׸��� �����ϰڽ��ϱ�? (y/n) ");
					String answer = sc.next();
					if(answer.equals("y")) {
						l.deleteItem(item);
						break;}
					else {
						System.out.print("�׸� ������ ����ϼ̽��ϴ�!\n");
						return;
				}
			}
			
			}
		}
		
		System.out.print("�׸��� �����Ǿ����ϴ�!\n");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "    �׸� ����\n\n"
				+ "�����Ͻ� �׸��� ��ȣ�� �Է��� �ּ��� ");
		int index = sc.nextInt();
		if(l.size()+1<index) {
			System.out.print("�Է��Ͻ� �׸��� ��ȣ�� �������� �ʽ��ϴ�.\n");
			return;
		}
		for (TodoItem item : l.getList()) {
			if (l.indexOf(item) == (index-1)) {
				System.out.println(index+". ["+item.getCategory()+ "] - " + item.getTitle() + " - " + item.getDesc() +" - "+item.getDue_date()+" - "+item.getCurrent_date());}}
		
		System.out.print("���ο� ī�װ��� �Է��� �ּ��� ");
		String new_category = sc.next();
		
		System.out.print("���ο� ������ �Է��� �ּ��� ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�ߺ��� �����Դϴ�!");
			return;
		}
		sc.nextLine();
		System.out.print("���ο� ������ �Է��� �ּ��� ");
		String new_description = sc.nextLine().trim();
		System.out.print("���ο� �������� �Է��� �ּ��� (��/��/��)");
		String new_due_date = sc.next();
		for (TodoItem item : l.getList()) {
			if (l.indexOf(item) == (index-1)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description,new_category,new_due_date);
				l.addItem(t);
				System.out.println("�׸��� �����Ǿ����ϴ�\n");
				break;
			}	
		}
		
	}
	public static void listcate(TodoList l) {
		Set<String> cate = new HashSet<String>();
		int count=0;
		for (TodoItem item : l.getList()) {
			cate.add(item.getCategory());
		}
		Iterator it = cate.iterator();
		while(it.hasNext()) {
			count++;
			String str = (String)it.next();
			if(it.hasNext()) {
				System.out.print(str+" / ");
			}
			else {
				System.out.print(str);
			}
		}
		System.out.print("�� "+count+"���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
		
	}

	public static void listAll(TodoList l) {
		System.out.println("\n   ��ü �׸�, �� "+ l.size()+"��");
		for (TodoItem item : l.getList()) {
			System.out.println((l.indexOf(item)+1)+". ["+item.getCategory()+ "] - " + item.getTitle() + " - " + item.getDesc() +" - "+item.getDue_date()+" - "+item.getCurrent_date());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
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
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(category,title, desc,due_date,current_date);
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
	public static void findList(TodoList l,String keyword) {
		int count=0;
		for (TodoItem item : l.getList()) {
			if(item.getTitle().contains(keyword)||item.getDesc().contains(keyword)) {
				System.out.println((l.indexOf(item)+1)+". ["+item.getCategory()+ "] - " + item.getTitle() + " - " + item.getDesc() +" - "+item.getDue_date()+" - "+item.getCurrent_date());
				count++;
			}
		}
		System.out.println("�� "+count+"���� �׸��� ã�ҽ��ϴ�.\n");
	}
	public static void findcateList(TodoList l,String keyword) {
		int count=0;
		for (TodoItem item : l.getList()) {
			if(item.getCategory().contains(keyword)) {
				System.out.println((l.indexOf(item)+1)+". ["+item.getCategory()+ "] - " + item.getTitle() + " - " + item.getDesc() +" - "+item.getDue_date()+" - "+item.getCurrent_date());
				count++;
			}
		}
		System.out.println("�� "+count+"���� �׸��� ã�ҽ��ϴ�.\n");
	}
}
