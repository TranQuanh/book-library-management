public class Student extends Person {
    private double gpa;

    public Student(String name, int age, String adress, double gpa) {
        super(name, age, adress);
        setGpa(gpa);
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if(gpa<0||gpa>10)this.gpa = 0;
        else this.gpa = gpa;
    }
    @Override
    public String toString() {
        return super.toString() + "\nGPA: " + gpa;
    }
}
