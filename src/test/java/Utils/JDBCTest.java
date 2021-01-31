package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {

    public static void main(String[] args) throws SQLException {

        /*
        Connection
        Statement
        ResultSet
         */
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@techdatabase.ca6nukd3yeyw.us-east-2.rds.amazonaws.com:1521/ORCL",
                "techDataBase","StepanSraka2020");  // host, username, password

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        resultSet.next();
        System.out.println("first_name first_row: "+resultSet.getString("FIRST_NAME"));
        System.out.println("salary first_row: "+resultSet.getString("SALARY"));
        System.out.println("department_id first_row: "+resultSet.getString("department_id"));

        resultSet.next(); // true /false
        System.out.println(resultSet.getString("First_name"));
        System.out.println(resultSet.getString("department_id"));
        System.out.println("employee_id second_row: "+resultSet.getString("employee_id"));
        System.out.println("row of data: "+resultSet.getRow());

        while(resultSet.next()){
            System.out.println(resultSet.getString("first_name"));
        }


        //*****************************DatabaseMetaData -> to get information about database *****************

        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println("User: "+ metaData.getUserName());
        System.out.println("Product name: "+ metaData.getDatabaseProductName());
        System.out.println("=====: "+metaData.getConnection());

        // *************** resultSetMataData -> to get column names ****************

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnCount());
        System.out.println(resultSetMetaData.getColumnName(1));

        List<Map<String,Object>> data = new ArrayList<>();

        resultSet.first();

        resultSet.beforeFirst();

        while (resultSet.next()){
            Map<String, Object> map = new HashMap<>();
            for (int i=1; resultSetMetaData.getColumnCount()>=i; i++){
                map.put(resultSetMetaData.getColumnName(i), resultSet.getString(resultSetMetaData.getColumnName(i)));
            }
            data.add(map);
        }

        System.out.println(data.get(0).get("FIRST_NAME"));
        System.out.println(data.get(0).get("EMPLOYEE_ID"));
        System.out.println("size of List data is: "+data.size());
        System.out.println("FIRST_NAME OF 102 ROW is: "+data.get(101).get("FIRST_NAME"));

        // Using data list of maps print names who has salary more than 10000

        for (Map<String, Object> salary : data){
            if (Integer.parseInt(salary.get("SALARY").toString())>=10000){
                System.out.println(salary.get("FIRST_NAME"));
            }
        }

        System.out.println("++++++++");

        for (int i=0;i<data.size();i++){
            if(Integer.parseInt(data.get(i).get("SALARY").toString())>=10000){
                System.out.println(data.get(i).get("FIRST_NAME"));
            }
        }


    }
}
