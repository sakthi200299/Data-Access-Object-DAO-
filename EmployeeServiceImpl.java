import java.util.Map;

class EmployeeServiceImpl implements EmployeeService {
     private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public void add(Map<String, Object> employeeData) {
        if (employeeData == null) {
            System.out.println("Error: employeeData is null");
            return;
        }

        Integer empId = (Integer) employeeData.get("empId");
        String empName = (String) employeeData.get("empName");
        String createdBy = (String) employeeData.get("createdBy");
        Boolean isActive = (Boolean) employeeData.get("isActive");
        int addresult1=employeeDAO.add(empId, empName, createdBy, isActive);
		if(addresult1>0)
		{
			System.out.println("Insert the Employee Details successfully");
		}
		else
		{
			System.out.println(" Employee Details not Insert");
		}
    }

    
    public void modify(Map<String, Object> modifiedData) {
        if (modifiedData == null) {
            System.out.println("Error: modifiedData is null");
            return;
        }

        Integer modifiedId = (Integer) modifiedData.get("empId");
        if (modifiedId == null) {
            System.out.println("Error: modifiedId is null");
            return;
        }

        String modifiedName = (String) modifiedData.get("empName");
        String modifiedBy = (String) modifiedData.get("modifiedBy");
        int modifyresult1=employeeDAO.modify(modifiedId, modifiedName, modifiedBy);
		if( modifyresult1>0)
		{
			System.out.println("Modified the Employee Details successfully");
		}
		else
		{
			System.out.println(" Employee Details not Modified");
		}
    }
    @Override
    public void delete(Map<String, Object> deleteData) {
        if (deleteData == null) {
            System.out.println("Error: deleteData is null");
            return;
        }

        Integer deleteId = (Integer) deleteData.get("empId");
        if (deleteId == null) {
            System.out.println("Error: deleteId is null");
            return;
        }

        int deleteresult1=employeeDAO.delete(deleteId);
		if( deleteresult1>0)
		{
			System.out.println("Delete the Employee Details successfully");
		}
		else
		{
			System.out.println(" Employee Details not Delete");
		}
    }

    
    public void displayEmployees()
	{
        Map<String, Map<String, Object>> employeesMap = employeeDAO.displayEmployees();

     for (Map.Entry<String, Map<String, Object>> entry : employeesMap.entrySet()) {
          String employeeId = entry.getKey();
          Map<String, Object> employeeDetails = entry.getValue();

    for (Map.Entry<String, Object> detailEntry : employeeDetails.entrySet()) {
        String employeeKey = detailEntry.getKey();
        Object employeeValue = detailEntry.getValue();
        System.out.println(employeeKey + "   :  " + employeeValue);
    }

    System.out.println();
	 }
    }
}
