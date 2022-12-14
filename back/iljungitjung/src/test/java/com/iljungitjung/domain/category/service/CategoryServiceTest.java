package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateDto;
import com.iljungitjung.domain.category.dto.CategoryCreateResponseDto;
import com.iljungitjung.domain.category.dto.CategoryListCreateRequestDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpSession;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@DisplayName("카테고리 서비스")
@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

    private CategoryService categoryService;
    @MockBean
    private HttpSession httpSession;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private UserService userService;

    @BeforeEach
    void init(){
        categoryService = new CategoryServiceImpl(categoryRepository, userService);
    }

    @Test
    @DisplayName("카테고리 등록")
    void addCategory(){
        //given
        String categoryName = "categoryName";
        String time = "0130";
        String color = "#000000";

        User userFrom = createUserFrom();

        List<Category> categoryList = new ArrayList<>();
        CategoryCreateDto categoryCreateRequestDto = new CategoryCreateDto(categoryName, time, color);
        Category category = categoryCreateRequestDto.toEntity();
        categoryList.add(category);

        List<CategoryCreateDto> categoryCreateRequestDtoList = new ArrayList<>();
        categoryCreateRequestDtoList.add(categoryCreateRequestDto);

        CategoryListCreateRequestDto categoryListCreateRequestDto = new CategoryListCreateRequestDto(categoryCreateRequestDtoList);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findByUser_Id(any(Long.class))).thenReturn(categoryList);

        CategoryCreateResponseDto categoryCreateResponseDto = categoryService.addCategory(categoryListCreateRequestDto, httpSession);

        //then
        Assertions.assertEquals(1L, categoryCreateResponseDto.getCount());

    }
    private User createUserFrom(){
        User userFrom = User.builder().build();

        return userFrom;
    }

}
