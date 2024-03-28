import java.util.Map;
interface EmployeeService {
	
	    void add(Map<String, Object> employeeData);
	    void modify(Map<String, Object> modifiedData);
	    void delete(Map<String, Object> deleteData);
	    void displayEmployees();
}
