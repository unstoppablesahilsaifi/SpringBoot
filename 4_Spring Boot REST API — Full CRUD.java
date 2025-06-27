Spring Boot REST API — Full CRUD

This is the most important step for interviews & real-world dev. By the end, you’ll:

✅ Build a full REST API
✅ Handle HTTP methods (GET, POST, PUT, DELETE)
✅ Use DTOs
✅ Handle exceptions
✅ Understand status codes
✅ Be interview-ready 💼

---

## 🛠️ We're going to build a simple **"Person API"**

### 📦 Technologies:

* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database (in-memory)
* Lombok (optional)

---

## ✅ 1. Create a New Spring Boot Project

From [https://start.spring.io](https://start.spring.io):

* **Project**: Maven
* **Dependencies**:

  * Spring Web
  * Spring Data JPA
  * H2 Database
  * Lombok (optional but handy)

---

## 🧱 2. Create the Person Entity

```java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    // Getters and Setters
}
```

---

## 📦 3. Create the Repository

```java
package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
```

---

## 🌐 4. Create the Controller

```java
package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepo;

    @GetMapping
    public List<Person> getAll() {
        return personRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        return personRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return new ResponseEntity<>(personRepo.save(person), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person newPerson) {
        return personRepo.findById(id)
                .map(existing -> {
                    existing.setName(newPerson.getName());
                    existing.setAge(newPerson.getAge());
                    return ResponseEntity.ok(personRepo.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!personRepo.existsById(id)) return ResponseEntity.notFound().build();
        personRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## 🧪 5. Test in Postman / Browser

| Action  | Method | URL              | Body (JSON)                      |
| ------- | ------ | ---------------- | -------------------------------- |
| Get All | GET    | `/api/persons`   | —                                |
| Get One | GET    | `/api/persons/1` | —                                |
| Create  | POST   | `/api/persons`   | `{"name": "Alice", "age": 30}`   |
| Update  | PUT    | `/api/persons/1` | `{"name": "Alice B", "age": 31}` |
| Delete  | DELETE | `/api/persons/1` | —                                |

---

## 🗃️ 6. application.properties

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

Then open:
🧪 H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
→ JDBC URL: `jdbc:h2:mem:testdb`

---


