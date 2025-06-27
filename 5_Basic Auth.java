Spring Security — Basic Auth & JWT

Spring Security is **essential** for any real-world app. Every job interview expects you to understand:

* Authentication (who are you?)
* Authorization (what are you allowed to do?)
* Securing endpoints (e.g. `/admin`, `/user`)
* Stateless JWT tokens 🔐

---

## 🛡️ Part 1: Spring Security with Basic Auth

### 📦 Add dependency (if not already)

Add to `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

---

### 🔒 By default, Spring Security enables HTTP Basic Auth

* Open browser → you'll be prompted for a username/password
* Default credentials:

  * Username: `user`
  * Password: printed in console on startup

---

### ✏️ Create Your Own Users

➡️ Add a `SecurityConfig.java` file:

```java
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                    .anyRequest().authenticated()
                )
                .httpBasic()
                .and().build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

---

### ✅ Create two test endpoints:

```java
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello USER";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello ADMIN";
    }
}
```

---

### 🔐 Try in Postman:

* `/api/user/hello` → login as `user` or `admin`
* `/api/admin/hello` → only `admin` can access

✅ That’s **basic authentication and role-based access**.
