package Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestUtils {

    public static void main(String[] args) throws SQLException, IOException {

        JDBCUtils.establishConnection();
        List<Map<String, Object>> data = JDBCUtils.runQuery("select salary, first_name, last_name from employees");
        for (int i = 0; data.size() > i; i++) {
            if (data.get(i).get("FIRST_NAME").equals("Steven")) {
                System.out.println(data.get(i).get("SALARY") + " /first name is: " + data.get(i).get("FIRST_NAME") +
                        " /Last name is: " + data.get(i).get("LAST_NAME"));
            }
        }

        int numberOfRow = JDBCUtils.getRowNumber("select * from employees");
        System.out.println(numberOfRow);
        JDBCUtils.closeConnection();

        ExcelUtils.openExcel("TestLastLesson", "Sheet1");
        System.out.println(ExcelUtils.getValue(1, 1));
        ExcelUtils.setValue("Norris", 8, 8);

        ExcelUtils.setValue("vinner", 5, 1);

        ExcelUtils.getNumberOfRows(5);


    }

}
