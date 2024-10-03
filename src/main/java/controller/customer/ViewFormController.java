package controller.customer;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewFormController implements Initializable {

    customerService service = new VIewCustomerController();  // Assuming customerService is an interface

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public TableColumn<?, ?> colSalary;
    public TableColumn<?, ?> colCity;
    public TableColumn<?, ?> colProvince;
    public TableColumn<?, ?> colPostalCode;
    public DatePicker dateDob;
    public ComboBox<String> cmbTitle;
    public JFXTextField txtSalary;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;

    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colDob;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableView<Customer> tblCustomers;

    private static ViewFormController instance;

    // Singleton pattern implementation
    public static ViewFormController getInstance() {
        if (instance == null) {
            instance = new ViewFormController();
        }
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles = FXCollections.observableArrayList("Mr", "Mrs");
        cmbTitle.setItems(titles);

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues(newValue);
            }
        }));
    }

    private void setTextToValues(Customer newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtProvince.setText(newValue.getProvince());
        txtCity.setText(newValue.getCity());
        cmbTitle.setValue(newValue.getTitle());
        dateDob.setValue(newValue.getDob());
        txtPostalCode.setText(newValue.getPostalCode());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
    }

    @FXML
    void btnReloadOnAction() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        tblCustomers.setItems(service.getAll());
    }

    public void btnAddOnAction() {
        Customer customer = new Customer(txtId.getText(),
                cmbTitle.getValue(),
                txtName.getText(),
                txtAddress.getText(),
                dateDob.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );
        service.addCustomer(customer);
    }

    public void btnDeleteOnAction() {
        service.deleteCustomer(txtId.getText());
    }

    // Method to retrieve customer IDs as an ObservableList
    public ObservableList<String> getCustomerIds() {
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        List<Customer> customers = service.getAll();  // Assuming service.getAll() returns a List<Customer>
        for (Customer customer : customers) {
            customerIds.add(customer.getId());
        }
        return customerIds;
    }

    public Customer searchCustomer(String customerID) {
        // Assuming service.getAll() returns a List<Customer>
        List<Customer> customers = service.getAll();
        for (Customer customer : customers) {
            if (customer.getId().equals(customerID)) {
                return customer;  // Return the customer if ID matches
            }
        }
        return null;  // Return null if no match is found
    }
}
