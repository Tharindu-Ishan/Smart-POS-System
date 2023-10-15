package lk.ijse.dep11.pos.db;

import lk.ijse.dep11.pos.tm.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataAccess {
    private static final PreparedStatement STM_GET_ALL;
    private static final PreparedStatement STM_GET_LAST_ID;



    static {
        try {
            Connection connection = SingleConnectionDataSource.getInstance().getConnection();
            STM_GET_ALL = connection.prepareStatement("SELECT * FROM customer ORDER BY id");
            STM_GET_LAST_ID= connection.prepareStatement("SELECT id FROM customer ORDER BY id DESC FETCH FIRST ROWS ONLY ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Customer> getAllCustomers() throws SQLException{
        List<Customer> customerList = new ArrayList<>();
        ResultSet rst = STM_GET_ALL.executeQuery();
        while (rst.next()){
            String id = rst.getString("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            customerList.add(new Customer(id,name,address));
        }
        return customerList;
    }
    public static String getLastCustomerId() throws SQLException{
        ResultSet rst = STM_GET_LAST_ID.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        else {
            return null;
        }

    }
}
