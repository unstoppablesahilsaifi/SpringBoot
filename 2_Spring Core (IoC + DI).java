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

