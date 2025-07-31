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

===========================================================================
 You’ve already done Field Injection:
java
Copy
Edit
@Autowired
private Engine engine;
❗ Downside: Not testable and hard to mock. ❌

 
 ✅ Best Practice: Constructor Injection
java
Copy
Edit
@Component
public class Car {
    private final Engine engine;

    @Autowired  // Optional since Spring Boot 4.3+ if 1 constructor only
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Car is running (Constructor DI)...");
    }
}
++++++++++++++++++++++++++++++++++

@Component
public class OrderService {
    private final PaymentService paymentService;

    @Autowired
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}


============================================================================

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



+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



 Perfect! Chalo ek **simple real-world example** dekhte hain:
`Car` depends on `Engine`.
Hum **2 versions** banayenge:

---

## 🔴 Version 1: **Direct Object Creation** (No Spring, No DI)

### `Engine.java`

```java
public class Engine {
    public void start() {
        System.out.println("Petrol Engine started!");
    }
}
```

### `Car.java`

```java
public class Car {
    private Engine engine = new Engine(); // ❌ Direct creation

    public void drive() {
        engine.start();
        System.out.println("Car is running...");
    }
}
```

### `Main.java`

```java
public class Main {
    public static void main(String[] args) {
        Car car = new Car();  // ❌ Manual wiring
        car.drive();
    }
}
```

### 🔍 Problems:

* Agar kal ko `ElectricEngine` chahiye, `Car` class modify karni padegi.
* Testing me mock engine inject karna impossible.
* Reusability nahi.
* Spring ka koi fayda nahi.

---

## ✅ Version 2: **Constructor-Based Dependency Injection using Spring**

### `Engine.java`

```java
import org.springframework.stereotype.Component;

@Component
public class Engine {
    public void start() {
        System.out.println("Petrol Engine started!");
    }
}
```

### `Car.java`

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Car is running...");
    }
}
```

### `AppConfig.java`

```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example") // ⚠️ Replace with your package name
public class AppConfig {
}
```

### `Main.java`

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Car car = context.getBean(Car.class); // ✅ Spring handles wiring
        car.drive();
    }
}
```

---

## 🔄 Change Scenario: Petrol ➝ Electric Engine

### `ElectricEngine.java`

```java
import org.springframework.stereotype.Component;

@Component
public class ElectricEngine extends Engine {
    @Override
    public void start() {
        System.out.println("Electric Engine started silently!");
    }
}
```

### ✅ No need to touch `Car.java` — Spring will auto-inject new Engine if qualified!



## 🧠 Conclusion

| ❌ Direct Import/Creation | ✅ Dependency Injection (Spring)       |
| ------------------------ | ------------------------------------- |
| Tight coupling           | Loose coupling                        |
| Hard to test             | Easy to test using mocks              |
| Hard to replace          | Easy to swap implementations          |
| No Spring power          | Full use of Spring container features |
| Not reusable             | Reusable and maintainable             |

---

Agar chaho toh main ek **GitHub-ready project folder** bhi bana ke de sakta hoon with both versions side-by-side. Batana.

