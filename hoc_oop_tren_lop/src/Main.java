import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student st = new Student("Khanh",23,"Ha Noi",9);
        Teacher t = new Teacher("Tung",30,"Ha Noi",1700);
        System.out.println(st);
        System.out.println(t);
    }
}