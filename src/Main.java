import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<StaffMember> StaffMembers = new ArrayList<>();
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
                    Utils.dsiplayEmployee(StaffMembers);
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
