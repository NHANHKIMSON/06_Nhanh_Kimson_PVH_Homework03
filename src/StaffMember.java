abstract class StaffMember{
    static int couter = 1;
    protected int id = 0;
    protected String name;
    protected String address;
    protected double payment;

    public static int getCouter() {
        return couter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static void setCouter(int couter) {
        StaffMember.couter = couter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StaffMember(String name, String address){
        this.id = couter++;
        this.name = name;
        this.address = address;
    }
    public double getPayment() {
        return payment;
    }
    public void setPayment(double payment) {
        this.payment = payment;
    }
    public String toString(){
        return id + " " + name  + " " + address + " " + payment ;
    }

    abstract double Pay();
}