package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@ToString
public class Customer {
    private String id;
    private String  title;
    private String  name;
    private String address;
    private LocalDate dob;
    private Double salary;
    private String city;
    private String province;
    private String postalCode;

    public Customer(String id, String title, String name, String address, LocalDate dob, double salary, String city, String province, String postalCode) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.salary = salary;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }
}
