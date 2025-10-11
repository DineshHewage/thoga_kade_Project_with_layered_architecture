package repository;

import java.sql.ResultSet;

public interface CustomerRepository {
    ResultSet searchCustomer(String customerId);
}
