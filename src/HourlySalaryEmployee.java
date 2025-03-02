public class HourlySalaryEmployee extends StaffMember {
    private int HourWorked;
    private double rate;
    double payment = 0.0;
    HourlySalaryEmployee(String name, String address, int hourWorked, double rate){
        super(name, address);
        this.HourWorked = hourWorked;
        this.rate = rate;
    }
    public void setName(){
        this.name = name;
    }
    public void setAddress(){
        this.address = address;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public double getHourWorked(){
        return HourWorked;
    }
    public double getRate(){
        return rate;
    }
    public double getPayment(){
        return payment;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setHourWorked(int hourWorked){
        HourWorked = hourWorked;
    }
    public void setRate(double rate){
        this.rate = rate;
    }
    public void setPayment(double payment){
        this.payment = payment;
    }
    public String toString(){
        return "id: " +  id + " name: " + name  + " address: " + address + " HourWorked: " + HourWorked + " Rate: " + rate;
    }

    @Override
    double Pay(){
        return HourWorked * rate;
    }
}
