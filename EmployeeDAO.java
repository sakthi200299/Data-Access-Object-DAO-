import java.util.Map;

interface EmployeeDAO {
    int add(int empId,String empName,String createdBy,boolean isActive);
    int modify(int modifiedId,String modifiedname,String modifiedBy);
    int delete(int deleteId);
    Map<String,Map<String,Object>> displayEmployees();
}