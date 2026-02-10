import java.util.*;
import java.util.stream.*;

class Employee{
    String name;
    int age;
    String gender;
    int salary;
    String designation;
    String department;

    public Employee(String name, int age, String gender, int salary, String designation, String department){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
    }

    public String getName(){
        return name;
    }

    public int getSalary(){
        return salary;
    }

    public int getAge(){
        return age;
    }

    public String getDesig(){
        return designation;
    }

    public void hike(){
        salary = (int) (salary*1.2);
    }
}

class Assign{
    public static void main(String[] args) {
        List<Employee> listnew = new ArrayList<Employee>();

        listnew.add(new Employee("Rakesh", 22,"M", 22000,"Employee", "Tester"));
        listnew.add(new Employee("Ramesh", 32,"F", 32000,"Employee", "Programmer"));
        listnew.add(new Employee("Rajesh", 28,"M", 28000,"Manager", "Programmer"));
        listnew.add(new Employee("Suresh", 49,"F", 45000,"Employee", "Programmer"));
        listnew.add(new Employee("Dinesh", 33,"M", 33000,"Employee", "Programmer"));
        listnew.add(new Employee("Veeresh", 24,"F", 24000,"Manager", "Tester"));
        listnew.add(new Employee("Mangesh", 28,"M", 28000,"Employee", "Tester"));
        listnew.add(new Employee("Mahesh", 36,"M", 36000,"Employee", "Tester"));
        listnew.add(new Employee("Ganesh", 31,"M", 31000,"Manager", "Programmer"));
        listnew.add(new Employee("Mahantesh", 48,"F", 48000,"Manager", "Programmer"));
        listnew.add(new Employee("Jignesh", 27,"M", 27000,"Employee", "Programmer"));
        listnew.add(new Employee("Mukesh", 21,"M", 21000,"Employee", "Tester"));

        System.out.println("Highest Paid Employee:");
        Optional<Employee> highestPaid = listnew.stream().max(Comparator.comparingInt(e->e.salary));
        highestPaid.ifPresent(e -> System.out.println(e.getName()+" has slaary:"+e.getSalary()));


        System.out.println("Number of Males and Females working:");
        Map<Boolean, Long> m2 = listnew.stream().collect(Collectors.partitioningBy(e -> e.gender.equals("M"),Collectors.counting()));
        System.out.println(m2);

        System.out.println("Total expense for the company dept wise:");
        Map<String, Integer> m6 = listnew.stream().collect(Collectors.groupingBy(e -> e.department, Collectors.summingInt(e->e.salary)));
        System.out.println(m6);

        System.out.println("Top 5 Senior Employees:");
        List<Employee> top5Senior = listnew.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed()).limit(5).collect(Collectors.toList());
        top5Senior.forEach(e -> System.out.println(e.getName() + " " + e.getAge()));

        System.out.println("Managers:");
        listnew.stream().filter(e -> "Manager".equals(e.getDesig())).forEach(e -> System.out.println(e.getName() + " " + e.getAge()));
        listnew.stream().filter(e -> "Employee".equals(e.getDesig())).forEach(e -> e.hike());
        listnew.stream().filter(e -> "Employee".equals(e.getDesig())).forEach(e -> System.out.println("Name:"+e.getName()+" Salary"+e.getSalary()));

    
        System.out.println(listnew.size());

    }
}