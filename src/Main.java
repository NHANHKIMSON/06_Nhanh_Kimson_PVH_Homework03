import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<StaffMember> StaffMembers = new ArrayList<>();
        StaffMembers.add(new Volunteer("John Doe", "New York", 500));
        StaffMembers.add(new Volunteer("Alice Smith", "London", 450));
        StaffMembers.add(new Volunteer("Bob Johnson", "Sydney", 600));
        StaffMembers.add(new Volunteer("Sarah Brown", "Tokyo", 550));
        StaffMembers.add(new Volunteer("Tom Wilson", "Paris", 480));
        StaffMembers.add(new Volunteer("Emma Taylor", "Berlin", 490));

        // Adding 6 Salaried Employees
        StaffMembers.add(new SalariedEmployee("Emily Davis", "Texas", 100, 3000));
        StaffMembers.add(new SalariedEmployee("Michael Clark", "California", 200, 3200));
        StaffMembers.add(new SalariedEmployee("Sophia Martinez", "Florida", 150, 2900));
        StaffMembers.add(new SalariedEmployee("Daniel White", "Nevada", 180, 3100));
        StaffMembers.add(new SalariedEmployee("Olivia Green", "Arizona", 130, 2800));
        StaffMembers.add(new SalariedEmployee("James Anderson", "Washington", 170, 3300));

        // Adding 6 Hourly Salary Employees
        StaffMembers.add(new HourlySalaryEmployee("Robert Miller", "Boston", 40, 15));
        StaffMembers.add(new HourlySalaryEmployee("Jessica Wilson", "Chicago", 35, 12));
        StaffMembers.add(new HourlySalaryEmployee("William Lee", "Denver", 45, 14));
        StaffMembers.add(new HourlySalaryEmployee("Isabella Harris", "Seattle", 38, 13));
        StaffMembers.add(new HourlySalaryEmployee("Benjamin Scott", "Miami", 42, 16));
        StaffMembers.add(new HourlySalaryEmployee("Charlotte Moore", "Atlanta", 37, 11));
        StaffMembers.add(new HourlySalaryEmployee("John Son", "Atlanta", 30, 11));
        while(true){
            Utils.Menu();
            Utils.option = sc.nextLine();
            switch(Utils.option){
                case "1":
                    Utils.insertEmployee(StaffMembers);
                    break;
                case "2":
                    Utils.UpdateEmployee(StaffMembers);
                    break;
                case "3":
                    Utils.displayEmployee(StaffMembers);
                    break;
                case "4":
                    Utils.DeleteEmployee(StaffMembers);
                    break;
                case "5":
                    Utils.Exit();
                    System.exit(0);
                default:
                    System.out.println(Utils.red + "Invalid option" + Utils.reset);
            }
        }
    }

}
