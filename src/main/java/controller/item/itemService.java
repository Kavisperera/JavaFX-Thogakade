package controller.item;

import javafx.collections.ObservableList;
import model.Item;

public interface itemService {
    boolean addItem(Item item);
    boolean deleteItem(String itemCode);
    ObservableList<Item> getAll();
    boolean updateItem(Item item);
    }
