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
				+ "    항목 추가\n\n"
				+ "카테고리를 입력해 주세요 ");
		category = sc.next();
		
		System.out.print("제목을 입력해 주세요 ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("중복된 제목입니다!\n");
			return;
		}
		sc.nextLine();
		System.out.print("설명을 입력해 주세요 ");
		desc = sc.nextLine();
		System.out.print("마감일을 입력해 주세요 (년/월/일)");
		due_date = sc.next();
		TodoItem t = new TodoItem(title, desc,category,due_date);
		list.addItem(t);
		System.out.println("항목이 추가되었습니다!\n");
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\n"
				+ "    항목 삭제\n\n"
				+ "삭제하실 항목의 번호를 입력해 주세요 ");
		int index = sc.nextInt();
		if(l.size()+1<index) {
			System.out.print("입력하신 항목의 번호는 존재하지 않습니다.\n");
			return;
		}
		else {
			for (TodoItem item : l.getList()) {
				if (l.indexOf(item) == (index-1)) {
					System.out.println(index+". ["+item.getCategory()+ "] - " + item.getTitle() + " - " + item.getDesc() +" - "+item.getDue_date()+" - "+item.getCurrent_date());
					System.out.print("위 항목을 삭제하겠습니까? (y/n) ");
					String answer = sc.next();
					if(answer.equals("y")) {
						l.deleteItem(item);
						break;}
					else {
						System.out.print("항목 삭제를 취소하셨습니다!\n");
						return;
				}
			}
			
			}
		}
		
		System.out.print("항목이 삭제되었습니다!\n");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "    항목 편집\n\n"
				+ "편집하실 항목의 번호를 입력해 주세요 ");
		int index = sc.nextInt();
		if(l.size()+1<index) {
			System.out.print("입력하신 항목의 번호는 존재하지 않습니다.\n");
			return;
		}
		for (TodoItem item : l.getList()) {
			if (l.indexOf(item) == (index-1)) {
				System.out.println(index+". ["+item.getCategory()+ "] - " + item.getTitle() + " - " + item.getDesc() +" - "+item.getDue_date()+" - "+item.getCurrent_date());}}
		
		System.out.print("새로운 카테고리를 입력해 주세요 ");
		String new_category = sc.next();
		
		System.out.print("새로운 제목을 입력해 주세요 ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("중복된 제목입니다!");
			return;
		}
		sc.nextLine();
		System.out.print("새로운 설명을 입력해 주세요 ");
		String new_description = sc.nextLine().trim();
		System.out.print("새로운 마감일을 입력해 주세요 (년/월/일)");
		String new_due_date = sc.next();
		for (TodoItem item : l.getList()) {
			if (l.indexOf(item) == (index-1)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description,new_category,new_due_date);
				l.addItem(t);
				System.out.println("항목이 편집되었습니다\n");
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
		System.out.print("총 "+count+"개의 카테고리가 등록되어 있습니다.");
		
	}

	public static void listAll(TodoList l) {
		System.out.println("\n   전체 항목, 총 "+ l.size()+"개");
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
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(category,title, desc,due_date,current_date);
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
	public static void findList(TodoList l,String keyword) {
		int count=0;
		for (TodoItem item : l.getList()) {
			if(item.getTitle().contains(keyword)||item.getDesc().contains(keyword)) {
				System.out.println((l.indexOf(item)+1)+". ["+item.getCategory()+ "] - " + item.getTitle() + " - " + item.getDesc() +" - "+item.getDue_date()+" - "+item.getCurrent_date());
				count++;
			}
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.\n");
	}
	public static void findcateList(TodoList l,String keyword) {
		int count=0;
		for (TodoItem item : l.getList()) {
			if(item.getCategory().contains(keyword)) {
				System.out.println((l.indexOf(item)+1)+". ["+item.getCategory()+ "] - " + item.getTitle() + " - " + item.getDesc() +" - "+item.getDue_date()+" - "+item.getCurrent_date());
				count++;
			}
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.\n");
	}
}
