import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LostItemManager manager = new LostItemManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println(" 유실물 관리 프로그램 ");

        while (true) {
            System.out.println("\n1. 등록하기 | 2. 전체 목록 보기 | 0. 종료");
            System.out.print("번호를 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("물건 이름: ");
                String name = scanner.nextLine();

                System.out.print("분실 장소: ");
                String place = scanner.nextLine();

                System.out.print("분실 날짜: ");
                String date = scanner.nextLine();

                System.out.print("연락처: ");
                String contact = scanner.nextLine();

                manager.register(name, place, date, contact);
            } else if (choice == 2) {
                manager.showAll();
            } else if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }

        scanner.close();
    }
}

class LostItem {
    private static int count = 1;
    private int id;
    private String name;
    private String place;
    private String date;
    private String contact;

    public LostItem(String name, String place, String date, String contact) {
        this.id = count++;
        this.name = name;
        this.place = place;
        this.date = date;
        this.contact = contact;
    }

    public String toString() {
        return "[" + id + "] " + name + " / 위치: " + place + " / 날짜: " + date + " / 연락처: " + contact;
    }
}

class LostItemManager {
    private ArrayList<LostItem> items = new ArrayList<>();

    public void register(String name, String place, String date, String contact) {
        LostItem newItem = new LostItem(name, place, date, contact);
        items.add(newItem);
        System.out.println("등록 완료: " + newItem);
    }
    public void showAll() {
        if (items.isEmpty()) {
            System.out.println(" 등록된 분실물이 없습니다.");
        } else {
            System.out.println(" 전체 분실물 목록:");
            for (LostItem item : items) {
                System.out.println(item);
            }
        }
    }

}