package com.mohamed.blogball.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mohamed.blogball.model.audit.UserDateAudit;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category extends UserDateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Post> posts;

  public Category(String name) {
    super();
    this.name = name;
  }

  public List<Post> getPosts() {
    return this.posts == null ? null : new ArrayList<>(this.posts);
  }

  public void setPosts(List<Post> posts) {
    if (posts == null) {
      this.posts = null;
    } else {
      this.posts = Collections.unmodifiableList(posts);
    }
  }
}
