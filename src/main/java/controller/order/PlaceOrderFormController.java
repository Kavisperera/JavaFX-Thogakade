package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.customer.ViewFormController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.util.Duration;
import model.Customer;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    @FXML
    private JFXTextField TxtCustAddress;

    @FXML
    private JFXTextField TxtCustName;

    @FXML
    private JFXTextField TxtDescription;

    @FXML
    private JFXTextField TxtQty;

    @FXML
    private JFXTextField TxtStock;

    @FXML
    private JFXTextField TxtUnitPrice;

    // Set JFXComboBox to handle Strings (customer IDs)
    @FXML
    private JFXComboBox<String> cmbCustomerID;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> coldescription;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOid;

    @FXML
    private Label lblTime;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        // Add to cart logic here
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        // Place order logic here
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadCustomerIds();  // Load customer IDs on initialization

        // Add listener to detect customer ID selection and update customer details
        cmbCustomerID.setOnAction(event -> {
            String selectedCustomerID = cmbCustomerID.getValue();
            if (selectedCustomerID != null) {
                searchCustomer(selectedCustomerID);  // Search and display customer details when selected
            }
        });
    }

    // Method to load the current date and time
    public void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);
        lblDate.setText(dateNow);

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }), new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // Method to load customer IDs into the combo box
    private void loadCustomerIds() {
        // Make sure the combo box is populated with customer IDs from ViewFormController
        cmbCustomerID.setItems(ViewFormController.getInstance().getCustomerIds());
    }

    // Method to search and display customer details based on the selected customer ID
    private void searchCustomer(String customerID) {
        Customer customer = ViewFormController.getInstance().searchCustomer(customerID);
        if (customer != null) {
            TxtCustName.setText(customer.getName());
            TxtCustAddress.setText(customer.getAddress());
        } else {
            // Clear fields if customer is not found
            TxtCustName.clear();
            TxtCustAddress.clear();
        }
    }
}
