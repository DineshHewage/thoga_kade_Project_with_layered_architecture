package service.impl;

import model.dto.Customer;
import repository.CustomerRepository;
import repository.impl.CustomerRepositoryImpl;
import service.CustomerService;

import java.sql.*;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public Customer getCustomer(String customerId){
        ResultSet resultSet = customerRepository.searchCustomer(customerId);
        try {
            resultSet.next();
            return new Customer(
                    resultSet.getString("CustID"),
                    resultSet.getString("CustTitle"),
                    resultSet.getString("CustName"),
                    resultSet.getDate("DOB").toLocalDate(),
                    resultSet.getDouble("salary"),
                    resultSet.getString("CustAddress"),
                    resultSet.getString("City"),
                    resultSet.getString("Province"),
                    resultSet.getString("PostalCode")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
