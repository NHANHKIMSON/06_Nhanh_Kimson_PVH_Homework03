
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
    public static void dsiplayEmployee(List<StaffMember> employees) {
        Table t = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        t.addCell("      DISPLAY EMPLOYEE      ", cellStyle, 9);
        t.addCell("      TYPE      ", cellStyle);
        t.addCell("      ID        ", cellStyle);
        t.addCell("      NAME      ", cellStyle);
        t.addCell("      ADDRESS      ", cellStyle);
        t.addCell("      SALARY      ", cellStyle);
        t.addCell("      BONUS      ", cellStyle);
        t.addCell("      HOUR      ", cellStyle);
        t.addCell("      RATE      ", cellStyle);
        t.addCell("      PAYMENT      ", cellStyle);
        for (int i = 0; i < employees.size(); i++) {
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
    private static void displayVolunteer(Volunteer volunteer) {
        System.out.println("id: " + volunteer.getID());
        System.out.println("name: " + volunteer.getName());
        System.out.println("address: " + volunteer.getAddress());
        System.out.println("Salary: " + volunteer.getSalary() + "$");
        System.out.println("Payment: " + volunteer.Pay() + "$");
    }
    private static void displaySalariedEmployee(SalariedEmployee salariedEmployee){
        System.out.println("id: " + salariedEmployee.getID());
        System.out.println("name: " + salariedEmployee.getName());
        System.out.println("address: " + salariedEmployee.getAddress());
        System.out.println("Bonus: " + salariedEmployee.getBonus() + "$");
        System.out.println("Salary: " + salariedEmployee.getSalary() + "$");
        System.out.println("Payment: " + salariedEmployee.Pay() + "$");
    }
    public static void displayHourWorked(HourlySalaryEmployee hourlySalaryEmployee){
        System.out.println("id: " + hourlySalaryEmployee.getId());
        System.out.println("name: " + hourlySalaryEmployee.getName());
        System.out.println("address: " + hourlySalaryEmployee.getAddress());
        System.out.println("Hour work: " + hourlySalaryEmployee.getHourWorked() + "h");
        System.out.println("Rate: " + hourlySalaryEmployee.getRate() + "$");
        System.out.println("Payment: " + hourlySalaryEmployee.Pay() + "$");
    }
    public static void insertEmployee(List<StaffMember> StaffMembers){
        String type = "Unknown";
        System.out.println("1. Volunteer\t Salaried\t HourWork\t");
        System.out.print("Enter the type of Employee: ");
        type = sc.nextLine();
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
        }
        System.out.println(status);
    }
    private static StaffMember insertVolunteer() {
        System.out.println("Id: " + StaffMember.getCouter());
        System.out.print("name: ");
        String name = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Salary:$ ");
        double salary = sc.nextDouble();
        sc.nextLine();
        return new Volunteer(name, address, salary);
    }
    private static StaffMember insertSalariedEmployee(){
        System.out.println("Id: " + StaffMember.getCouter());
        System.out.print("name: ");
        String name = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter Bonus: ");
        double bonus = sc.nextDouble();
        System.out.print("Enter Salary: ");
        double slalary  = sc.nextDouble();
        sc.nextLine();
        return new SalariedEmployee(name, address, bonus, slalary);
    }
    public static StaffMember insertHourWorked(){
        System.out.println("Id: " + StaffMember.getCouter());
        System.out.print("name: ");
        String name = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter Hour work: ");
        int hours = sc.nextInt();
        System.out.print("Enter Rate: ");
        int rate = sc.nextInt();
        sc.nextLine();
        return new HourlySalaryEmployee(name, address, hours, rate);
    }
    public static void DeleteEmployee(List<StaffMember> StaffMembers){
        System.out.print("Enter id to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        Iterator<StaffMember> interator = StaffMembers.iterator();
        while(interator.hasNext()){
            StaffMember staffMember = interator.next();
            if(staffMember.getId() == id){
                StaffMembers.remove(staffMember);
                status = green + "Employee: " + red + "[" + id + "]" + blue + " Has Been Deleted Successfully" + reset;
                break;
            }else{
                System.out.println("Not found");
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
        int id_update = Integer.parseInt(sc.nextLine());
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
                                System.out.print(blue + "Enter New Name: " + reset);
                                volunteer.setName(sc.nextLine());
                                break;
                            case "2":
                                System.out.print(blue + "Enter New Address: " + reset);
                                volunteer.setAddress(sc.nextLine());
                                break;
                            case "3":
                                System.out.print(blue + "Enter New Salary: " + reset);
                                volunteer.setSalary(sc.nextDouble());
                                sc.nextLine();
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
                                System.out.print(blue + "Enter New Name: " + reset);
                                salariedEmployee.setName(sc.nextLine());
                                break;
                            case "2":
                                System.out.print(blue + "Enter New Address: " + reset);
                                salariedEmployee.setAddress(sc.nextLine());
                                break;
                            case "3":
                                System.out.print(blue + "Enter New Salary: " + reset);
                                salariedEmployee.setSalary(sc.nextDouble());
                                sc.nextLine();
                                break;
                            case "4":
                                System.out.print(blue + "Enter Bonus: " + reset);
                                salariedEmployee.setBonus(sc.nextDouble());
                                sc.nextLine();
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
                                System.out.print(blue + "Enter New Name: " + reset);
                                hourlyEmployee.setName(sc.nextLine());
                                break;
                            case "2":
                                System.out.print(blue + "Enter New Address: " + reset);
                                hourlyEmployee.setAddress(sc.nextLine());
                                break;
                            case "3":
                                System.out.print(blue + "Enter New Salary: " + reset);
                                hourlyEmployee.setHourWorked(sc.nextInt());
                                sc.nextLine();
                                break;
                            case "4":
                                System.out.print(blue + "Enter New Rate: " + reset);
                                hourlyEmployee.setRate(sc.nextDouble());
                                sc.nextLine();
                                break;
                            case "5":
                                return;
                            default:
                                System.out.println(red + "Invalid column" + reset);
                                break;
                        }
                    }while (true);
                }
            }
        }
    }
}