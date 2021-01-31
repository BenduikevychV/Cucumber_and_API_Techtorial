package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtils {

    /*
        methods in utils class are static methods
        establishConnection(); -> establishing connection
        runQuery(String query); -> will return list of maps as data
        getRowsNumber(String query); -> will return number of rows
     */

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void establishConnection() throws SQLException {
        connection = DriverManager.getConnection(ConfigReader.getProperty("databaseURL"),
                ConfigReader.getProperty("databaseUSERNAME"),
                ConfigReader.getProperty("databasePASSWORD"));

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }

    public  static List<Map<String,Object>> runQuery(String query) throws SQLException {
        List<Map<String, Object>> data = new ArrayList<>();
        resultSet = statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        while (resultSet.next()){
            Map<String, Object> map = new HashMap<>();
            for (int i=1; i<=resultSetMetaData.getColumnCount(); i++){
                map.put(resultSetMetaData.getColumnName(i), resultSet.getObject(resultSetMetaData.getColumnName(i)));
            }
            data.add(map);
        }
        return data;
    }

    public static int getRowNumber(String query) throws SQLException {
        resultSet = statement.executeQuery(query);
        resultSet.last();
        return  resultSet.getRow();
    }

    public static void closeConnection() throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if (statement!=null){
            statement.close();
        }
        if (resultSet!=null){
            resultSet.close();
        }
    }




}
