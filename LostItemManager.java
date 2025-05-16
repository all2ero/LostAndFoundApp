import java.util.ArrayList;

public class LostItemManager {
    private ArrayList<LostItem> items = new ArrayList<>();

    public void register(String name, String place, String date, String contact) {
        LostItem newItem = new LostItem(name, place, date, contact);
        items.add(newItem);
        System.out.println("등록 완료: " + newItem);
    }
}
