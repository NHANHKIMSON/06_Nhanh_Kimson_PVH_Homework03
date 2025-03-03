
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Utils {
    static String option;
    static String reset = "\u001B[0m";
    static String blue = "\u001B[34m";
    static String cyan = "\u001B[36m";
    static String red = "\u001B[31m";
    static String green = "\u001B[32m";
    static String yellow = "\u001B[33m";
    static String magenta = "\u001B[35m";
    public static Scanner sc = new Scanner(System.in);
    public static String status = null;
    public static int i = 0;

    public static void displayEmployee(List<StaffMember> employees) {
        final int PAGE_SIZE = 3; // Number of employees per page
        Scanner scanner = new Scanner(System.in);
        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) employees.size() / PAGE_SIZE);

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            Table t = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);

            t.addCell("      DISPLAY EMPLOYEE (Page: " + currentPage + "/" + totalPages + ")      ", cellStyle, 9);
            t.addCell("      TYPE      ", cellStyle);
            t.addCell("      ID        ", cellStyle);
            t.addCell("      NAME      ", cellStyle);
            t.addCell("      ADDRESS      ", cellStyle);
            t.addCell("      SALARY      ", cellStyle);
            t.addCell("      BONUS      ", cellStyle);
            t.addCell("      HOUR      ", cellStyle);
            t.addCell("      RATE      ", cellStyle);
            t.addCell("      PAYMENT      ", cellStyle);

            int startIndex = (currentPage - 1) * PAGE_SIZE;
            int endIndex = Math.min(startIndex + PAGE_SIZE, employees.size());

            for (int i = startIndex; i < endIndex; i++) {
                StaffMember emp = employees.get(i);
                String type = "Unknown";
                if (emp instanceof Volunteer) {
                    Volunteer v = (Volunteer) emp;
                    type = "Volunteer";
                    t.addCell(type, cellStyle);
                    t.addCell(String.valueOf(emp.getId()), cellStyle);
                    t.addCell(emp.getName(), cellStyle);
                    t.addCell(emp.getAddress(), cellStyle);
                    t.addCell(String.valueOf(v.getSalary()), cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell(String.valueOf(v.Pay()), cellStyle);
                } else if (emp instanceof SalariedEmployee) {
                    SalariedEmployee s = (SalariedEmployee) emp;
                    type = "Salaried Employee";
                    t.addCell(type, cellStyle);
                    t.addCell(String.valueOf(emp.getId()), cellStyle);
                    t.addCell(emp.getName(), cellStyle);
                    t.addCell(emp.getAddress(), cellStyle);
                    t.addCell(String.valueOf(s.getSalary()), cellStyle);
                    t.addCell(String.valueOf(s.getBonus()), cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell(String.valueOf(s.Pay()), cellStyle);
                } else if (emp instanceof HourlySalaryEmployee) {
                    HourlySalaryEmployee h = (HourlySalaryEmployee) emp;
                    type = "Hourly Employee";
                    t.addCell(type, cellStyle);
                    t.addCell(String.valueOf(emp.getId()), cellStyle);
                    t.addCell(emp.getName(), cellStyle);
                    t.addCell(emp.getAddress(), cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell(String.valueOf(h.getHourWorked()), cellStyle);
                    t.addCell(String.valueOf(h.getRate()), cellStyle);
                    t.addCell(String.valueOf(h.Pay()), cellStyle);
                }
            }
            System.out.println(blue +  t.render() + reset);
            System.out.println("Pages: " + currentPage + "/" + 3);
            System.out.println("1. First Page \t2. Next Page \t3. Previous Page \t4. Last Page  \t5. Quit");
            String choice;
            while (true) {
                System.out.print("Enter your choice (1-5): ");
                if ((choice = scanner.nextLine().trim()).matches("[1-5]")) break;
                System.out.println(red + "❌ Invalid input! Please enter a number between 1 and 5." + reset);
            }
            switch (choice) {
                case "1":
                    currentPage = 1;
                    break;
                case "2":
                    if (currentPage < totalPages) currentPage++;
                    break;
                case "3":
                    if (currentPage > 1) currentPage--;
                    break;
                case "4":
                    currentPage = totalPages;
                    break;
                case "5":
                    return; // Exit function
                default:
                    System.out.println(red + "Invalid input. Please try again." + reset);
            }
        }
    }
    static public void Menu(){
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        t.addCell("           1. Insert Employee                    ", cellStyle);
        t.addCell("           2. Update Employee ((Except ID)       ", cellStyle);
        t.addCell("           3. Display Employee                   ", cellStyle);
        t.addCell("           4. Remove Employee                    ", cellStyle);
        t.addCell("           5. Exit                               ", cellStyle);
        System.out.println(blue + t.render() + reset);
        System.out.print(green + "Choose an option: " + reset);
    }

    public static void insertEmployee(List<StaffMember> StaffMembers){
        String type = "Unknown";
        System.out.println("1. Volunteer\t2. Salaried\t3. HourWork\t \t4. Back");
        while (true) {
            System.out.print("Enter the type of Employee: ");
            if ((type = sc.nextLine().trim()).matches("[1-4]")) break;
            System.out.println(red + "❌ Invalid input! Please enter a number between 1 and 3." + reset);
        }
        if (type.equals("1")) {
            System.out.println("Display Volunteer");
            StaffMembers.add(insertVolunteer());
            status = green +  "Your Are Add New Volunteer Employee Successfully" + reset;

        }else if (type.equals("2")) {
            System.out.println("Insert Salaried");
            StaffMembers.add(insertSalariedEmployee());
            status = green +  "Your Are Add New Salaried Employee Successfully" + reset;

        }else if (type.equals("3")){
            System.out.println("Display HourWork");
            StaffMembers.add(insertHourWorked());
            status = green +  "Your Are Add New HourWork Employee Successfully" + reset;
        }else if (type.equals("4")){
            return;
        }
        System.out.println(status);
    }
    private static StaffMember insertVolunteer() {
        System.out.println("Id: " + StaffMember.getCouter());
        String name;
        while(true) {
            System.out.print("Enter name: ");
            name = sc.nextLine();
            if(name.matches("[a-zA-Z ]*")){
                break;
            }else {
                System.out.println(red + "Invalid name. Please try again." + reset);
            }
        }
        String address;
        while(true) {
            System.out.print("Enter address: ");
            address = sc.nextLine();
            if(address.matches("[a-zA-Z ]*")){
                break;
            }else {
                System.out.println(red + "Invalid address. Please try again." + reset);
            }
        }
        String salary;
        while(true) {
            System.out.print("Enter salary: ");
            salary = sc.nextLine();
            if(salary.matches("^[1-9][0-9]*$")) break;
            System.out.println(red + "Invalid salary. Please try again." + reset);
        }
        return new Volunteer(name, address, Double.parseDouble(salary));
    }
    private static StaffMember insertSalariedEmployee(){
        System.out.println("Id: " + StaffMember.getCouter());
        String name;
        while(true) {
            System.out.print("Enter name: ");
            name = sc.nextLine();
            if(name.matches("[a-zA-Z ]*")){
                break;
            }else {
                System.out.println(red + "Invalid name. Please try again." + reset);
            }
        }
        String address;
        while(true) {
            System.out.print("Enter address: ");
            address = sc.nextLine();
            if(address.matches("[a-zA-Z ]*")){
                break;
            }else {
                System.out.println(red + "Invalid address. Please try again." + reset);
            }
        }
        String bonus;
        while(true) {
            System.out.print("Enter Bonus: ");
            bonus = sc.nextLine();
            if(bonus.matches("^[1-9][0-9]*$")){
                break;
            }else {
                System.out.println(red + "Invalid bonus. Please try again." + reset);
            }
        }
        String salary;
        while(true) {
            System.out.print("Enter Salary: ");
            salary = sc.nextLine();
            if(salary.matches("^[1-9][0-9]*$")){
                break;
            }else {
                System.out.println(red + "Invalid salary. Please try again." + reset);
            }
        }
        return new SalariedEmployee(name, address, Double.parseDouble(bonus), Double.parseDouble(salary));
    }
    public static StaffMember insertHourWorked(){
        System.out.println("Id: " + StaffMember.getCouter());
        String name;
        while(true) {
            System.out.print("Enter name: ");
            name = sc.nextLine();
            if(name.matches("[a-zA-Z ]*")){
                break;
            }else {
                System.out.println(red + "Invalid name. Please try again." + reset);
            }
        }
        String address;
        while(true) {
            System.out.print("Enter address: ");
            address = sc.nextLine();
            if(address.matches("[a-zA-Z ]*")){
                break;
            }else {
                System.out.println(red + "Invalid address. Please try again." + reset);
            }
        }
        String hours;
        while(true) {
            System.out.print("Enter Hour work: ");
            hours = sc.nextLine();
            if(hours.matches("^[1-9][0-9]*$")){
                break;
            }else {
                System.out.println(red + "Invalid hours. Please try again." + reset);
            }
        }
        String rate;
        while(true) {
            System.out.print("Enter Rate: ");
            rate = sc.nextLine();
            if(rate.matches("^[1-9][0-9]*$")){
                break;
            }else {
                System.out.println(red + "Invalid rate. Please try again." + reset);
            }
        }
        return new HourlySalaryEmployee(name, address,Integer.parseInt(hours), Double.parseDouble(rate));
    }
    public static void DeleteEmployee(List<StaffMember> StaffMembers){
        System.out.print("Enter id to delete: ");
        String id = sc.nextLine();
        while (true) {
            if(id.matches("[0-9]*")){
                break;
            }else {
                System.out.println(red + "Invalid id. Please try again." + reset);
            }
        }
        Iterator<StaffMember> interator = StaffMembers.iterator();
        while(interator.hasNext()){
            StaffMember staffMember = interator.next();
            if(staffMember.getId() == Integer.parseInt(id)){
                StaffMembers.remove(staffMember);
                status = green + "Employee: " + red + "[ " + id + " ]" + blue + " Has Been Deleted Successfully" + reset;
                break;
            }else{
                 status = yellow + "Not found" + red + "[ " + id + " ]" + blue + " Please try again." + reset;
            }
        }
        for(i = 0; i<StaffMembers.size(); i++ ){
            StaffMember staffMember = StaffMembers.get(i);
        }
        System.out.println(status);
    }
    public static void Exit(){
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        t.addCell("          THANK YOU FOR USE THIS SYSTEM          ");
        System.out.println(t.render());
    }
    public static void UpdateEmployee(List<StaffMember> employees) {
        System.out.print("Enter id of Employee to update: ");
        String id = sc.nextLine();
        while (true) {
            if(id.matches("[0-9]*")){
                break;
            }else System.out.println(red + "Invalid id. Please try again." + reset);
        }
        int id_update = Integer.parseInt(id);
        String status = "";
        for (i = 0; i < employees.size(); i++){
            StaffMember emp = employees.get(i);
            if (emp.getId() == id_update){
                if(emp instanceof Volunteer){
                    do {
                        Volunteer volunteer = (Volunteer) emp;
                        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                        Table t = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                        t.addCell("          UPDATE VOLUNTEER         ",cellStyle, 6);
                        t.addCell("Type",cellStyle);
                        t.addCell("   Id   ", cellStyle);
                        t.addCell("  Name  ", cellStyle);
                        t.addCell("  Address  ", cellStyle);
                        t.addCell("  Salary   ", cellStyle);
                        t.addCell("  Payment  ", cellStyle);
                        t.addCell("  Volunteer ", cellStyle);
                        t.addCell(String.valueOf(volunteer.getID()), cellStyle);
                        t.addCell(volunteer.getName(), cellStyle);
                        t.addCell(volunteer.getAddress(), cellStyle);
                        t.addCell(String.valueOf(volunteer.getSalary()) + "$", cellStyle);
                        t.addCell(String.valueOf(volunteer.Pay()) + "$", cellStyle);
                        System.out.println(blue + t.render() + reset);


                        CellStyle cellStyleUpdate = new CellStyle(CellStyle.HorizontalAlign.center);
                        Table t1 = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                        t1.addCell("  1 Update Name  ",cellStyleUpdate);
                        t1.addCell("  2 Update Address  ",cellStyle);
                        t1.addCell("  3 Update Salary  ",cellStyle);
                        t1.addCell("  0 Cancel  ",cellStyle);
                        System.out.println(blue +  t1.render() + reset);
                        System.out.print("Enter column to update: ");
                        String column = sc.nextLine();
                        switch (column) {
                            case "1":
                                while (true){
                                    System.out.print(blue + "Enter New Name: " + reset);
                                    String newName = sc.nextLine();
                                    if(newName.matches("^[A-Za-z ]+$")){
                                        volunteer.setName(newName);
                                        break;
                                    }else System.out.println(red + "Invalid name. Please try again." + reset);
                                }
                                break;
                            case "2":
                                while (true){
                                    System.out.print(blue + "Enter New Address: " + reset);
                                    String newAddress = sc.nextLine();
                                    if(newAddress.matches("[A-Z]*")){
                                        volunteer.setAddress(newAddress);
                                        break;
                                    }else {
                                        System.out.println("Invalid Address. Please try again." + reset);
                                    }
                                }
                                break;
                            case "3":
                                while (true){
                                    System.out.print(blue + "Enter New Salary: " + reset);
                                    String newSalary = sc.nextLine();
                                    if(newSalary.matches("[0-9]*")){
                                        volunteer.setSalary(Double.parseDouble(newSalary));
                                        break;
                                    }else System.out.println(red + "Invalid salary. Please try again." + reset);
                                }
                                break;
                            case "0":
                                return;
                            default:
                                System.out.println(red +"Invalid column" + reset);
                                break;
                        }
                    } while (true);
                }else if (emp instanceof SalariedEmployee){
                    do {
                        SalariedEmployee salariedEmployee = (SalariedEmployee) emp;
                        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                        Table t = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                        t.addCell("       UPDATE SALARIED EMPLOYEE     ", cellStyle, 7);
                        t.addCell("   Type   ", cellStyle);
                        t.addCell("   Id   ", cellStyle);
                        t.addCell("  Name  ", cellStyle);
                        t.addCell("  Address  ", cellStyle);
                        t.addCell("  Salary   ", cellStyle);
                        t.addCell("  Bonus ", cellStyle);
                        t.addCell("  Payment  ", cellStyle);
                        t.addCell("  Salaried Employees ", cellStyle);
                        t.addCell(String.valueOf(salariedEmployee.getID()), cellStyle);
                        t.addCell(salariedEmployee.getName(), cellStyle);
                        t.addCell(salariedEmployee.getAddress(), cellStyle);
                        t.addCell(String.valueOf(salariedEmployee.getSalary()) + "$", cellStyle);
                        t.addCell(String.valueOf(salariedEmployee.getBonus()) + "$", cellStyle);
                        t.addCell(String.valueOf(salariedEmployee.Pay()) + "$", cellStyle);
                        System.out.println(blue +  t.render() + reset);
                        CellStyle cellStyleUpdate = new CellStyle(CellStyle.HorizontalAlign.center);
                        Table t1 = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                        t1.addCell("  1 Update Name  ",cellStyleUpdate);
                        t1.addCell("  2 Update Address  ",cellStyleUpdate);
                        t1.addCell("  3 Update Salary  ",cellStyleUpdate);
                        t1.addCell("  4 Update Bonus  ",cellStyleUpdate);
                        t1.addCell("  0 Cancel  ",cellStyleUpdate);
                        System.out.println(blue +  t1.render() + reset);
                        System.out.print("Enter column to update: ");
                        String column = sc.nextLine();
                        switch (column) {
                            case "1":
                                while (true){
                                    System.out.print(blue + "Enter New Name: " + reset);
                                    String newName = sc.nextLine();
                                    if(newName.matches("^[A-Za-z ]+$")){
                                        salariedEmployee.setName(newName);
                                        break;
                                    }else System.out.println(red + "Invalid name. Please try again." + reset);
                                }
                                break;
                            case "2":
                                while (true){
                                    System.out.print(blue + "Enter New Address: " + reset);
                                    String newAddress = sc.nextLine();
                                    if(newAddress.matches("^[A-Za-z ]+$")){
                                        salariedEmployee.setAddress(newAddress);
                                        break;
                                    }else System.out.println(red + "Invalid address. Please try again." + reset);
                                }
                                break;
                            case "3":
                                while (true){
                                    System.out.print(blue + "Enter New Salary: " + reset);
                                    String newSalary = sc.nextLine();
                                    if(newSalary.matches("[0-9]*")){
                                        salariedEmployee.setSalary(Double.parseDouble(newSalary));
                                        break;
                                    }else System.out.println(red + "Invalid salary. Please try again." + reset);
                                }
                                break;
                            case "4":
                                while (true){
                                    System.out.print(blue + "Enter New Bonus: " + reset);
                                    String newBonus = sc.nextLine();
                                    if(newBonus.matches("[0-9]*")){
                                        salariedEmployee.setBonus(Double.parseDouble(newBonus));
                                        break;
                                    }else System.out.println(red + "Invalid bonus. Please try again." + reset);
                                }
                                break;
                            case "0":
                                return;
                            default:
                                System.out.println(red + "Invalid column" + reset);
                                break;
                        }
                    } while (true);
                }else if (emp instanceof HourlySalaryEmployee){
                    do{
                        HourlySalaryEmployee hourlyEmployee = (HourlySalaryEmployee) emp;
                        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                        Table t = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                        t.addCell("       Update HourlyEmployee     ", cellStyle, 7);
                        t.addCell("  Type   ", cellStyle);
                        t.addCell("  Id       ", cellStyle);
                        t.addCell("  Name     ", cellStyle);
                        t.addCell("  Address  ", cellStyle);
                        t.addCell("  Hour     ", cellStyle);
                        t.addCell("  Rate     ", cellStyle);
                        t.addCell("  Payment  ", cellStyle);
                        t.addCell("  Hourly Employees ", cellStyle);
                        t.addCell(String.valueOf(hourlyEmployee.getId()), cellStyle);
                        t.addCell(hourlyEmployee.getName(), cellStyle);
                        t.addCell(hourlyEmployee.getAddress(), cellStyle);
                        t.addCell(String.valueOf(hourlyEmployee.getHourWorked()) + "h", cellStyle);
                        t.addCell(String.valueOf(hourlyEmployee.getRate()) + "$", cellStyle);
                        t.addCell(String.valueOf(hourlyEmployee.Pay()) + "$", cellStyle);
                        System.out.println(blue +  t.render() + reset);
                        CellStyle cellStyleUpdate = new CellStyle(CellStyle.HorizontalAlign.center);
                        Table t1 = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                        t1.addCell("  1 Update Name  ", cellStyleUpdate);
                        t1.addCell("  2 Update Address  ", cellStyleUpdate);
                        t1.addCell("  3 Update Hour  ", cellStyleUpdate);
                        t1.addCell("  4 Update Rate  ", cellStyleUpdate);
                        t1.addCell("  0 Cancel  ", cellStyleUpdate);
                        System.out.println(blue +  t1.render() + reset);
                        System.out.print("Enter column to update: ");
                        String column = sc.nextLine();
                        switch (column) {
                            case "1":
                                while (true){
                                    System.out.print(blue + "Enter New Name: " + reset);
                                    String newName = sc.nextLine();
                                    if(newName.matches("^[A-Za-z ]+$")){
                                    hourlyEmployee.setName(newName);
                                    break;
                                    }else System.out.println(red + "Invalid name. Please try again." + reset);
                                }
                                break;
                            case "2":
                                while (true){
                                    System.out.print(blue + "Enter New Address: " + reset);
                                    String newAddress = sc.nextLine();
                                    if(newAddress.matches("^[A-Za-z ]+$")){
                                        hourlyEmployee.setAddress(newAddress);
                                        break;
                                    }else System.out.println(red + "Invalid address. Please try again." + reset);
                                }
                                break;
                            case "3":
                                while (true){
                                    System.out.print(blue + "Enter New Hour: " + reset);
                                    String newHour = sc.nextLine();
                                    if(newHour.matches("[0-9]*")){
                                        hourlyEmployee.setHourWorked(Integer.parseInt(newHour));
                                        break;
                                    }else System.out.println(red + "Invalid hour. Please try again." + reset);
                                }
                                break;
                            case "4":
                                while (true){
                                    System.out.print(blue + "Enter New Rate: " + reset);
                                    String newRate = sc.nextLine();
                                    if(newRate.matches("[0-9]*")){
                                        hourlyEmployee.setRate(Double.parseDouble(newRate));
                                        break;
                                    }else System.out.println(red + "Invalid rate. Please try again." + reset);
                                }
                                break;
                            case "0":
                                return;
                            default:
                                System.out.println(red + "Invalid column" + reset);
                                break;
                        }
                    }while (true);
                }
            }else {
                status = "Id not found. Please try again.";
            }
        }
        System.out.println(red + status + reset);
    }
}