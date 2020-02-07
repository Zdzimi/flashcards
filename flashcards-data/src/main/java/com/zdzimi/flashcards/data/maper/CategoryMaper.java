package com.zdzimi.flashcards.data.maper;

import com.zdzimi.flashcards.core.model.Category;
import com.zdzimi.flashcards.data.dto.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

public class CategoryMaper {

    public static Category mapCategoryDTOToCategory(CategoryDTO categoryDTO) {
        return new Category(
                categoryDTO.getCategoryId(),
                categoryDTO.getCategoryName(),
                categoryDTO.getLastUpdate()
        );
    }

    public static List<Category> mapCategoryDTOListToCategoryList(List<CategoryDTO> categoryDTOList) {
        List<Category> categoryList = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOList) {
            categoryList.add(mapCategoryDTOToCategory(categoryDTO));
        }
        return categoryList;
    }

    public static CategoryDTO mapCategoryToCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getLastUpdate()
        );
    }
}
