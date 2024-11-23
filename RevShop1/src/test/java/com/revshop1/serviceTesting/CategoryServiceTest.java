package com.revshop1.serviceTesting;

import com.revshop1.model.Category;
import com.revshop1.repository.CategoryRepository;
import com.revshop1.service.CategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category testCategory;

    @BeforeEach
    void setUp() {
        testCategory = new Category();
        testCategory.setCategoryId(1);
        testCategory.setName("Electronics");
    }

    @Test
    void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(testCategory));

        List<Category> categories = categoryService.getAllCategories();

        assertEquals(1, categories.size());
        assertEquals("Electronics", categories.get(0).getName());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoryById_Found() {
        when(categoryRepository.findById(1)).thenReturn(Optional.of(testCategory));

        Category category = categoryService.getCategoryById(1);

        assertNotNull(category);
        assertEquals("Electronics", category.getName());
        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        Category category = categoryService.getCategoryById(1);

        assertNull(category);
        verify(categoryRepository, times(1)).findById(1);
    }
}