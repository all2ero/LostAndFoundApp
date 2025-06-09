import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class LostItemManagerTest {

    @Test
    public void testRegisterAndShowAll() {
        LostItemManager manager = new LostItemManager();
        manager.register("우산", "도서관", "2025-06-10", "010-1234-5678");


        assertFalse(manager.getItems().isEmpty());
    }

    @Test
    public void testSearchFound() {
        LostItemManager manager = new LostItemManager();
        manager.register("핸드폰", "카페", "2025-06-09", "010-0000-0000");


        boolean found = manager.getItems().stream()
                .anyMatch(item -> item.toString().contains("핸드폰"));
        assertTrue(found);
    }

    @Test
    public void testDeleteById() {
        LostItemManager manager = new LostItemManager();
        manager.register("책", "도서관", "2025-06-01", "010-1111-2222");
        int id = manager.getItems().get(0).getId();
        manager.deleteById(id);
        assertTrue(manager.getItems().isEmpty());
    }

    @Test
    public void testSaveAndLoad() throws IOException {
        String file = "data/test_items.txt";
        LostItemManager manager = new LostItemManager();
        manager.register("가방", "강의실", "2025-06-02", "010-3333-4444");
        manager.saveToFile(file);

        LostItemManager newManager = new LostItemManager();
        newManager.loadFromFile(file);
        assertFalse(newManager.getItems().isEmpty());


        new File(file).delete();
    }

}

