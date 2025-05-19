import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LostItemManager manager = new LostItemManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println(" 유실물 관리 프로그램 ");

        while (true) {
            System.out.println("\n1. 등록하기 | 0. 종료");
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
