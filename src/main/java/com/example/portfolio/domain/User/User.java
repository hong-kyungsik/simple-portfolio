package com.example.portfolio.domain.User;

import com.example.portfolio.domain.Portfolio.Portfolio;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(builderMethodName = "hiddenBuilder")
@Slf4j
public class User{
  @Transient
  private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Portfolio> portfolios;

  public static UserBuilder builder(String name, String email, String password){
    return hiddenBuilder().name(name).email(email).password(password);
  }

  public void encodePassword(){
    password = passwordEncoder.encode(password);
  }

}
