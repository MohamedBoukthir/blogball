package com.mohamed.blogball.Repositories;

import com.mohamed.blogball.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
