package com.mohamed.blogball.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mohamed.blogball.model.audit.DateAudit;
import com.mohamed.blogball.model.role.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
    name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = {"username"}),
      @UniqueConstraint(columnNames = {"email"})
    })
@NoArgsConstructor
@Data
public class User extends DateAudit implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @NotBlank
  @Column(name = "first_name")
  @Size(max = 40)
  private String firstName;

  @NotBlank
  @Column(name = "last_name")
  @Size(max = 40)
  private String lastName;

  @NotBlank
  @Column(name = "username")
  @Size(max = 30)
  private String username;

  @NotBlank
  @NaturalId
  @Size(max = 40)
  @Email
  @Column(name = "email")
  private String email;

  @NotBlank
  @Column(name = "password")
  @Size(min = 8, max = 100)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  private RoleName role;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Post> posts;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

  public User(String firstName, String lastName, String username, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public List<Post> getPosts() {

    return posts == null ? null : new ArrayList<>(posts);
  }

  public void setPosts(List<Post> posts) {

    if (posts == null) {
      this.posts = null;
    } else {
      this.posts = Collections.unmodifiableList(posts);
    }
  }

  public List<Comment> getComments() {
    return comments == null ? null : new ArrayList<>(comments);
  }

  public void setComments(List<Comment> comments) {

    if (comments == null) {
      this.comments = null;
    } else {
      this.comments = Collections.unmodifiableList(comments);
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
