package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("\n    프로그램 기능");
        System.out.println("항목 추가하기 ( add )");
        System.out.println("항목 제거하기 ( del )");
        System.out.println("항목 편집하기  ( edit )");
        System.out.println("전체 항목보기 ( ls )");
        System.out.println("제목 순으로 나열하기 ( ls_name_asc )");
        System.out.println("제목 역순으로 나열하기 ( ls_name_desc )");
        System.out.println("입력 순으로 나열하기 ( ls_date )");
        System.out.println("나가기 ( exit )\n");
    }
    public static void prompt() {
        System.out.print("명령어를 입력해 주세요 > ");
    }
}
