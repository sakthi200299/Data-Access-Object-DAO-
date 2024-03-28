import java.util.Scanner;
import java.util.Map;
import java.util.HashMap; 
public class Employee {
    private static EmployeeService employeeService;

    public static void main(String[] args) {
        employeeService = new EmployeeServiceImpl();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1) Add");
            System.out.println("2) Modify");
            System.out.println("3) Delete");
            System.out.println("4) Display");
            System.out.println("5) Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Map<String, Object> addArgs = new HashMap<>();
                    System.out.println("Enter employee ID:");
                    addArgs.put("empId", scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter employee name:");
                    addArgs.put("empName", scanner.nextLine());
                    System.out.println("Enter created by:");
                    addArgs.put("createdBy", scanner.nextLine());
                    System.out.println("Is employee active? (true or false):");
                    addArgs.put("isActive", scanner.nextBoolean());
                    scanner.nextLine(); // Consume newline
                    employeeService.add(addArgs);

                    break;
                case 2:
                    Map<String, Object> modifyArgs = new HashMap<>();
                    System.out.println("Enter employee ID to modify:");
                    modifyArgs.put("empId", scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter name to Modify:");
                    modifyArgs.put("empName", scanner.nextLine());
                    System.out.println("Enter modified by:");
                    modifyArgs.put("modifiedBy", scanner.nextLine());
                    employeeService.modify(modifyArgs);

                    break;
               
                case 3:
                  Map<String, Object> deleteArgs = new HashMap<>();
                 System.out.println("Enter Employee ID to Delete");
                 deleteArgs.put("empId", scanner.nextInt());
                 employeeService.delete(deleteArgs);

                   break;
                case 4:
                    employeeService.displayEmployees();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
