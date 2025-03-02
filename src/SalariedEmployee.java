public class SalariedEmployee extends StaffMember {
    private double salary;
    private double bonus;
    public SalariedEmployee(String name, String address, double salary,double bonus){
        super(name, address);
        this.salary = salary;
        this.bonus = bonus;
    }

    public int getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public double getSalary(){
        return salary;
    }
    public double getBonus(){
        return bonus;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public void setBonus(double bonus){
        this.bonus = bonus;
    }
    public String toString(){
        return "id: " +  id + " name: " + name  + " address: " + address + " Salary: " + salary + " Bonus: " + bonus;
    }
    @Override
    double Pay(){
        return salary + bonus;
    }
}
