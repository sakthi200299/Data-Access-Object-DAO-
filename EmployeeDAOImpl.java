import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Map;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap; 

class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public int add(int empId, String empName, String createdBy, boolean isActive) {
        Connection connection = null;
        Statement statement = null;
		int addresult=0;
        try {
            connection = new DBConnection().getConnection(); // corrected the reference to DBConnection
            LocalDateTime currentDateTime = LocalDateTime.now();
            Timestamp timestamp1 = Timestamp.valueOf(currentDateTime);
            statement = connection.createStatement();
            String query = "INSERT INTO employees (employee_id, employee_name, created_by, created_date_time, modified_by, modified_date_time, is_active) " +
                    "VALUES (" + empId + ", '" + empName + "', '" + createdBy + "', '" + timestamp1 + "', '" + createdBy + "', '" + timestamp1 + "', " + isActive + ")";
             addresult=statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in finally block
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		return addresult;
    }

    @Override
    public int modify(int modifiedId, String modifiedName, String modifiedBy) {
        Connection connection = null;
        Statement statement = null;
		int modifiedresult=0;
        try {
            connection = new DBConnection().getConnection(); // corrected the reference to DBConnection
            LocalDateTime currentDateTime = LocalDateTime.now();
            Timestamp timestamp2 = Timestamp.valueOf(currentDateTime);
            statement = connection.createStatement();
            String query = "UPDATE employees SET modified_by = '" + modifiedBy + "', employee_name = '" + modifiedName + "', modified_date_time = '" + timestamp2 + "' WHERE employee_id = " + modifiedId;
            modifiedresult=statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                                               // Close resources in finally block
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		return modifiedresult;

    }

    public int  delete(int deleteId) {
        Connection connection = null;
        Statement statement = null;
		int  deleteresult=0; 
        try {
            connection = new DBConnection().getConnection(); // corrected the reference to DBConnection
            statement = connection.createStatement();
            // Example ID to delete, replace it with your actual ID
            String query = "DELETE FROM employees WHERE employee_id = " + deleteId;// corrected variable name
              deleteresult = statement.executeUpdate(query);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in finally block
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		return  deleteresult;

    }

    @Override
    public Map<String, Map<String, Object>> displayEmployees() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Map<String, Object>> employeesMap = new HashMap<>();
        try {
            connection = new DBConnection().getConnection(); // corrected the reference to DBConnection
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees");

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String employeeName = resultSet.getString("employee_name");
                String createdBy = resultSet.getString("created_by");
                Timestamp createdDateTime = resultSet.getTimestamp("created_date_time");
                String modifiedBy = resultSet.getString("modified_by");
                Timestamp modifiedDateTime = resultSet.getTimestamp("modified_date_time");
                int isActive = resultSet.getInt("is_active");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
                String createdDateTimeStr = createdDateTime.toLocalDateTime().format(formatter);
                String modifiedDateTimeStr = modifiedDateTime.toLocalDateTime().format(formatter);

                Map<String, Object> employeeDetails = new HashMap<>();
				employeeDetails.put("Employee ID",employeeId);
                employeeDetails.put("Employee Name", employeeName);
                employeeDetails.put("Created By", createdBy);
                employeeDetails.put("Created Date Time", createdDateTimeStr);
                employeeDetails.put("Modified By", modifiedBy);
                employeeDetails.put("Modified Date Time", modifiedDateTimeStr);
                employeeDetails.put("Is Active", isActive);

                employeesMap.put("Employee ID: " + employeeId, employeeDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeesMap;
    }
}
