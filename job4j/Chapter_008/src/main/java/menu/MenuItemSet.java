package menu;

import java.util.List;

public interface MenuItemSet {
    List<MenuItem> getItems();

    void formattedPrint(String marker, int times);
}
