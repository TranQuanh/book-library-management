public class Person {
    private String name;
    private int age;
    private String adress;

    public Person(String name, int age, String address) {
        this.name = name;
        setAge(age);
        this.adress = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age >0) this.age = age;
        else System.out.println("invalid");
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    public String toString() {
        return "Name: "+name+"\nAge: "+age+"\nAdress: "+adress;
    }
}
