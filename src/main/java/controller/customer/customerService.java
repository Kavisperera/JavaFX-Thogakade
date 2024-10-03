package controller.customer;

import javafx.collections.ObservableList;
import model.Customer;
import model.Item;

public interface customerService {
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(String id);
    ObservableList<Customer> getAll();
    boolean updateCustomer(Customer customer);
    ObservableList<String> SearchCustomer();

    ObservableList<String> getCustomerIds();

    Customer SearchCustomer(String id);
}
