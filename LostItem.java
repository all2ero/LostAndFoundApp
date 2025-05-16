#LostItem 클래스 생성 
public class LostItem {
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
