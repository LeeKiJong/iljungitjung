package com.iljungitjung.domain.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("카테고리 컨트롤러")
public class CategoryControllerTest{

//    @Test
//    @DisplayName("카테고리 등록")
//    public void A() throws Exception {
//
//        //given
//        String categoryName = "커트4";
//        String time = "0130";
//        String color = "#000000";
//
//        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(categoryName, time, color);
//        CategoryIdResponseDto categoryIdResponseDto = new CategoryIdResponseDto(categoryId+1);
//
//        when(categoryService.addCategory(any(CategoryCreateRequestDto.class))).thenReturn(categoryIdResponseDto);
//
//        String requestAsString = objectMapper.writeValueAsString(categoryCreateRequestDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(post("/categories")
//                .content(requestAsString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//        categoryId++;
//    }
//    @Test
//    @DisplayName("카테고리 수정")
//    public void B() throws Exception {
//
//        //given
//        String categoryName = "커트 수정4";
//        String time = "0200";
//        String color = "#111111";
//
//        CategoryEditRequestDto categoryEditRequestDto = new CategoryEditRequestDto(categoryId, categoryName, time, color);
//        CategoryIdResponseDto categoryIdResponseDto = new CategoryIdResponseDto(categoryId);
//
//        Mockito.when(categoryService.updateCategory(any(CategoryEditRequestDto.class))).thenReturn(categoryIdResponseDto);
//
//        String requestAsString = objectMapper.writeValueAsString(categoryEditRequestDto);
//
//
//        //when
//        ResultActions actions = mockMvc.perform(put("/categories")
//                .content(requestAsString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//
//    }
//    @Test
//    @DisplayName("카테고리 삭제")
//    public void 카테고리_삭제_컨트롤러() throws Exception {
//
//
//        //given
//        CategoryIdResponseDto categoryIdResponseDto = new CategoryIdResponseDto(categoryId);
//
//        Mockito.when(categoryService.deleteCategory(any(categoryId.getClass()))).thenReturn(categoryIdResponseDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(delete("/categories/" + categoryId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//
//    }
}