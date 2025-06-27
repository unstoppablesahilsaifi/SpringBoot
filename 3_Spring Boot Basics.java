
## 🎯 What is Spring Boot?

Spring Boot is a framework that **simplifies Spring configuration** so you can build and run apps **faster and cleaner**.

### 💡 Why Spring Boot?

| Feature                    | Description                            |
| -------------------------- | -------------------------------------- |
| ✅ **Auto-configuration**   | Spring figures out the config for you  |
| ✅ **Embedded server**      | No need for external Tomcat/Jetty      |
| ✅ **Starter dependencies** | Pre-bundled libraries for common tasks |
| ✅ **Actuator**             | Built-in endpoints to monitor your app |
| ✅ **Spring Initializr**    | Easy project generator                 |

---

## 🔧 Your First Spring Boot App: “Hello Spring Boot REST”

---

### ✅ 1. Create Project

Use [https://start.spring.io](https://start.spring.io):

* **Project**: Maven
* **Language**: Java
* **Spring Boot**: latest (e.g., 3.2+)
* **Dependencies**:

  * Spring Web

Then unzip and open in IntelliJ or Eclipse.

---

### ✅ 2. Project Structure

```
src/main/java/
├── com.example.demo/
│   ├── DemoApplication.java
│   └── HelloController.java
```

---

### ✅ 3. `DemoApplication.java`

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // ← Enables auto-configuration, component scan, etc.
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

---

### ✅ 4. `HelloController.java`

```java
package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(defaultValue = "World") String name) {
        return "Hello, " + name + " 👋";
    }
}
```

---

### ✅ 5. Run the App

In IntelliJ:

* Right-click `DemoApplication.java` → Run

Or use terminal:

```bash
./mvnw spring-boot:run
```

---

### ✅ 6. Test It

Go to your browser or Postman:

```
http://localhost:8080/hello?name=John
```

You’ll see:

```
Hello, John 👋
```

---

## 🔍 How It Works

| Part                     | Meaning                                                                 |
| ------------------------ | ----------------------------------------------------------------------- |
| `@SpringBootApplication` | Combo of `@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan` |
| `@RestController`        | Tells Spring this is a web controller that returns JSON/text            |
| `@GetMapping("/hello")`  | Maps a GET request to `/hello` endpoint                                 |
| `@RequestParam`          | Binds query parameter `name=...` to method param                        |

---

## 📦 Bonus: What is a "Starter"?

* `spring-boot-starter-web` = Spring MVC + Jackson + Embedded Tomcat
* `spring-boot-starter-data-jpa` = Hibernate + Spring Data
* `spring-boot-starter-security` = Spring Security config
* `spring-boot-devtools` = Hot reload

---

## ✅ Your Task

1. Generate Spring Boot project from Spring Initializr with `spring-boot-starter-web`.
2. Create `HelloController.java`.
3. Test your `/hello?name=YourName` endpoint.
4. Confirm you're seeing `Hello, YourName 👋`.

