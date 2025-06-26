Create a Person class with name, age.
Use encapsulation (private fields, getters/setters).
In main, create a list of persons, use Stream to filter people age > 18.

import java.util.*;
import java.util.stream.*;

public class Person {
    // ✅ 1. Capitalize `String`, it's case-sensitive
    private String name;
    private int age;

    // ✅ 2. Getters (should return the field, not accept a parameter)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // ✅ 3. Setters (should take parameter and assign to this.field)
    public void setName(String name) {
        this.name = name; // Use '.' not ','
    }

    public void setAge(int age) {
        this.age = age;
    }
}

// ✅ Separate Main class
class Main {
    public static void main(String[] args) {
        System.out.println("Running...");

        // ✅ Create some Person objects
        Person p1 = new Person();
        p1.setName("Alice");
        p1.setAge(22);

        Person p2 = new Person();
        p2.setName("Bob");
        p2.setAge(15);

        Person p3 = new Person();
        p3.setName("Charlie");
        p3.setAge(30);

        // ✅ Create a List of people
        List<Person> people = Arrays.asList(p1, p2, p3);

        // ✅ Use Stream to filter people older than 18
        List<Person> adults = people.stream()
            .filter(p -> p.getAge() > 18)
            .collect(Collectors.toList());

        // ✅ Print the names of adults
        System.out.println("Adults:");
        for (Person p : adults) {
            System.out.println(p.getName() + " - " + p.getAge());
        }
    }
}





==> POJO VS JavaBean
POJO (Plain Old Java Object)
A POJO is a simple Java object that doesn’t follow any special conventions or extend/implement any predefined Java classes/interfaces (like `Serializable`, `Cloneable`, etc.).
🔹 Characteristics:
* No restrictions on method names or signatures.
* No requirement for getter/setter methods.
* Can have any access modifier (private, protected, public).
* Can contain business logic.

🔹 Example:

public class Person {
    String name;
    int age;
}

✅ **JavaBean**


A JavaBean is a special type of POJO that follows specific conventions to be used in frameworks, tools, and components.
🔹 Characteristics:
* Must have a **public no-argument constructor**.
* All properties must be **private** (encapsulation).
* Provides **getter and setter** methods for accessing private fields.
* Should be **serializable** (optional but recommended).

🔹 Example:

import java.io.Serializable;

public class PersonBean implements Serializable {
    private String name;
    private int age;

    public PersonBean() {} // No-arg constructor

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
        this.age = age;
    }
}
