package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import model.Customer;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbTitle;

    @FXML
    private DatePicker dateDob;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("MR.");
        titles.add("Miss");



        cmbTitle.setItems(titles);
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
       // List<Customer> customerList = DBConnection.getInstance().getConnection();
//        customerList.add(
////                new Customer(
////                        txtId.getText(),
////                        txtName.getText(),
////                        txtAddress.getText(),
////                        cmbTitle.getValue(),
////                        dateDob.getValue()
////                )
//        );
    }


}
