public class Teacher extends Person{
    private int salary;

    public Teacher(String name, int age, String adress, int salary) {
        super(name, age, adress);
        setSalary(salary);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary < 0) this.salary = 0;
        else this.salary = salary;
    }
    @Override
    public String toString() {
        return super.toString() + "\nSalary: " + salary;
    }
}
