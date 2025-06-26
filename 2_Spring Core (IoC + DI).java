Spring Core (IoC + DI)
 🔁 Inversion of Control (IoC)
> Instead of your code **creating dependencies**, Spring **injects** them for you.

## 💉 Dependency Injection (DI)
> A design pattern where dependencies are **provided** to a class instead of being **created** inside it.

---

## ⚙️ Spring Basics: Annotations You Must Know

| Annotation       | Purpose                                                |
| ---------------- | ------------------------------------------------------ |
| `@Component`     | Marks a class as a Spring-managed component            |
| `@Autowired`     | Automatically injects a bean (object)                  |
| `@Configuration` | Declares a class that defines Spring Beans manually    |
| `@Bean`          | Used inside `@Configuration` to manually define a bean |
| `@ComponentScan` | Tells Spring where to look for components              |

  
## ✅ Project Structure for Today:
spring-core-demo/
├── AppConfig.java
├── Car.java
├── Engine.java
├── MainApp.java

## 🚗 Engine.java

import org.springframework.stereotype.Component;

@Component
public class Engine {
    public void start() {
        System.out.println("Engine started!");
    }
}

> `@Component`: Tells Spring to manage this class as a **bean**.

---

## 🚘 Car.java

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    @Autowired
    private Engine engine;

    public void drive() {
        engine.start();
        System.out.println("Car is running...");
    }
}

> `@Autowired`: Tells Spring to inject the `Engine` bean **into Car**.

## 🧪 AppConfig.java

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "your.package.name") // ✅ Replace this with your package!
public class AppConfig {
}
```

> This tells Spring to **scan and register** your `@Component` classes.

---

## 🎬 MainApp.java

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        Car car = context.getBean(Car.class);
        car.drive();
    }
}

### ✅ Expected Output:

Engine started!
Car is running...

## 🔍 Explanation of Each Part

| Concept            | What Happens                                  |
| ------------------ | --------------------------------------------- |
| **IoC Container**  | `ApplicationContext` holds and manages beans  |
| **Bean**           | An object managed by Spring                   |
| **Autowiring**     | Spring finds a matching bean and injects it   |
| **Component Scan** | Automatically finds classes with `@Component` |



 @Component
public class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}



@Component
public class Car {
    private final Engine engine;



    @Autowired
    public Car(Engine engine) {  // Spring injects the dependency
        this.engine = engine;
    }



    public void drive() {
        engine.start();
        System.out.println("Car is moving");
    }
}
 





Sure! Let’s break this code down line by line in simple words, like you’re explaining it to a beginner. 🔍👇



🔹 @Component

@Component
public class Engine {

This tells Spring: "Hey! Manage this Engine class as a Bean."
Meaning: Spring will create, configure, and inject objects of this class whenever needed.



🔹 Engine class

public void start() {
    System.out.println("Engine started");
}

Simple method: When called, it just prints "Engine started".



🔹 Another @Component

@Component
public class Car {

Same as above: Spring will also manage the Car class as a Bean.



🔹 Dependency Declaration

private final Engine engine;

Car needs an Engine to work.
This is a dependency (Car depends on Engine).



🔹 Constructor + @Autowired

@Autowired
public Car(Engine engine) {
    this.engine = engine;
}

This is Constructor-Based Dependency Injection.
@Autowired tells Spring:

“When creating a Car object, please inject a matching Engine Bean automatically.”

✅ So you don’t need to write: new Engine() anywhere. Spring handles that!



🔹 drive() method

public void drive() {
    engine.start();
    System.out.println("Car is moving");
}

When drive() is called:

The Engine starts.
Then the car moves.



🔁 Full Working Together

When you run your Spring Boot app:

Spring scans all @Component classes.
It creates and manages:

One Engine bean
One Car bean (and injects the Engine into it)
When you get the Car bean and call drive():



💡 Simple Analogy

Engine is like a battery 🔋.
Car is like a remote-control car 🚗.
You don’t insert the battery manually — Spring does it for you via Dependency Injection.



Let me know if you want me to show the main method or Spring Boot setup part for this example too!

Engine started
Car is moving
