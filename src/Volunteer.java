public class Volunteer extends StaffMember{
    private double salary;
    public Volunteer(String name, String address, double salary){
        super(name, address);
        this.salary = salary;
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
    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public String toString(){
        return  "id: " +  id + " name: " + name  + " address" + address + " salary: " + salary;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    @Override
    double Pay(){
        return salary;
    }
}
