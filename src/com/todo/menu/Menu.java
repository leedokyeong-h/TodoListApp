package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("\n    ���α׷� ���");
        System.out.println("�׸� �߰��ϱ� ( add )");
        System.out.println("�׸� �����ϱ� ( del )");
        System.out.println("�׸� �����ϱ�  ( edit )");
        System.out.println("��ü �׸񺸱� ( ls )");
        System.out.println("���� ������ �����ϱ� ( ls_name_asc )");
        System.out.println("���� �������� �����ϱ� ( ls_name_desc )");
        System.out.println("�Է� ������ �����ϱ� ( ls_date )");
        System.out.println("������ ( exit )\n");
    }
    public static void prompt() {
        System.out.print("��ɾ �Է��� �ּ��� > ");
    }
}
