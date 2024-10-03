package controller.item;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import util.CrudUtil;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewItemController implements itemService {

    @Override
    public boolean addItem(Item item) {
        try {
            String SQL="INSERT INTO item VALUES (?,?,?,?,?)";

        return  CrudUtil.execute(SQL,
            item.getItemCode(),
            item.getDescription(),
            item.getPackSize(),
            item.getUnitPrice(),
            item.getQtyOnHand()
                );
             } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean deleteItem(String itemCode) {
        try {
            String sql   = " DELETE FROM item WHERE ItemCode='" +itemCode+"'";
          return   CrudUtil.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        try {
            String sql="SELECT * FROM item";
          ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()){
                itemList.add(new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                ));
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  itemList;
    }

    @Override
    public boolean updateItem(Item item) {
        try {
            String sql = "UPDATE item SET Description=? , PackSize=? ,UnitPrice=?,QtyOnHand=? WHERE ItemCode = ?";
        return CrudUtil.execute(sql,
                item.getDescription(),
                item.getPackSize(),
                item.getUnitPrice(),
                item.getQtyOnHand(),
                item.getItemCode()
                );

              } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
