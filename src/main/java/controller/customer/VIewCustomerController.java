package controller.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VIewCustomerController implements customerService{

    @Override
    public boolean addCustomer(Customer customer) {
        try {
            String SQL="INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)";
            return CrudUtil.execute(SQL,
                    customer.getId(),
                    customer.getTitle(),
                    customer.getName(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()
                    );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteCustomer(String id) {
        String SQL="DELETE FROM customer WHERE CustID='" +id+"'";
        try {
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Customer> getAll() {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM customer";
            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()){
                          customerObservableList.add(new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getString("CustAddress"),
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("postalCode")

                ));

           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerObservableList;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public ObservableList<String> SearchCustomer() {
        return null;
    }

    @Override
    public ObservableList<String> getCustomerIds() {
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        ObservableList<Customer> customerObservableList = getAll();
        customerObservableList.forEach(customer -> {
            customerIds.add(customer.getId());
        });

        return customerIds;

    }


    @Override
    public Customer SearchCustomer(String id) {
        String SQl = "SELECT * FROM customer WHERE CustID=?";

        try {
            ResultSet resultSet = CrudUtil.execute(SQl, id);

            while (resultSet.next()) {
                return new Customer(

                                resultSet.getString("CustID"),
                                resultSet.getString("CustTitle"),
                                resultSet.getString("CustName"),
                                resultSet.getString("CustAddress"),
                                resultSet.getDate("DOB").toLocalDate(),
                                resultSet.getDouble("salary"),
                                resultSet.getString("City"),
                                resultSet.getString("Province"),
                                resultSet.getString("postalCode"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


}
