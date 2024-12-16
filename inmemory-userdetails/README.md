# inmemory user details 개발용

```java

   @Bean
    @Profile("dev")
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder passwordEncoder = passwordEncoder(); // 재사용
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin")) // BCrypt로 암호화
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
    
    
```

아니면

spring.security.username = ?

spring.security.password = ? 

