package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    IndexController controller;

    @Mock
    Model model;

    HashSet recipes;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(recipeService);


    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        Recipe testRecipe = new Recipe();
        testRecipe.setId(2l);
        recipes = new HashSet();
        recipes.add(testRecipe);
        recipes.add(new Recipe());


        when(recipeService.getListOfRecipe()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = controller.getIndexPage(model);

        assertEquals("index", viewName);
        verify(recipeService, times(1)).getListOfRecipe();
        verify(model, times(1)).addAttribute(eq( "recipes"), argumentCaptor.capture());
        Set<Recipe> setFromControler = argumentCaptor.getValue();
        assertEquals(2, setFromControler.size());
    }
}