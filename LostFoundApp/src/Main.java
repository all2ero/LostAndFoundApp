import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        LostItemManager manager = new LostItemManager();
        Scanner scanner = new Scanner(System.in);
        manager.loadFromFile("data/lost_items.txt");

        System.out.println(" 유실물 관리 프로그램 ");

        while (true) {
            System.out.println("\n1. 등록하기 | 2. 전체 목록 보기 | 3. 검색하기 | 4. 삭제하기 | 0. 종료");
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

            } else if (choice == 3) {
                System.out.print("검색할 키워드를 입력하세요: ");
                String keyword = scanner.nextLine();
                manager.search(keyword);

            } else if (choice == 4) {
                System.out.print("삭제할 분실물의 ID를 입력하세요: ");
                int deleteId = scanner.nextInt();
                scanner.nextLine();
                manager.deleteById(deleteId);

            } else if (choice == 0) {
                manager.saveToFile("data/lost_items.txt");
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

    public void search(String keyword) {
        boolean found = false;
        System.out.println("검색 결과:");
        for (LostItem item : items) {
            if (item.toString().contains(keyword)) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) {
            System.out.println(" 해당 키워드를 포함한 분실물이 없습니다.");
        }
    }

    public void deleteById(int id) {
        boolean removed = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).toString().contains("[" + id + "]")) {
                System.out.println("삭제된 분실물: " + items.get(i));
                items.remove(i);
                removed = true;
                break;
            }
        }

        if (!removed) {
            System.out.println("해당 ID의 분실물을 찾을 수 없습니다.");
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (LostItem item : items) {
                String line = item.toString();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("분실물 목록이 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("저장 실패: " + e.getMessage());
        }
    }
    public void loadFromFile(String filename) {
        items.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" / ");
                if (parts.length == 4) {
                    String name = parts[0].substring(parts[0].indexOf("]") + 2); // 물건명
                    String place = parts[1].replace("위치: ", "");
                    String date = parts[2].replace("날짜: ", "");
                    String contact = parts[3].replace("연락처: ", "");
                    items.add(new LostItem(name, place, date, contact));
                }
            }
            System.out.println("분실물 목록을 불러왔습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("저장된 파일이 없습니다. 새로 시작합니다.");
        } catch (IOException e) {
            System.out.println("불러오기 실패: " + e.getMessage());
        }
    }

}
