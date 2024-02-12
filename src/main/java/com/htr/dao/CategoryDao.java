package com.htr.dao;

import com.htr.pojo.Category;
import java.util.List;

public interface CategoryDao {

    List<Category> listAllCategories();

    Category getCategoryById(Long id);
}
