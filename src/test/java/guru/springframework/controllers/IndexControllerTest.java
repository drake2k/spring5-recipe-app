package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

        Recipe testRecipe = new Recipe();
        testRecipe.setId(2l);
        recipes = new HashSet();
        recipes.add(testRecipe);
    }

    @Test
    public void getIndexPage() {
        when(recipeService.GetListOfRecipe()).thenReturn(recipes);

        assertEquals("index", controller.getIndexPage(model));
        verify(recipeService, times(1)).GetListOfRecipe();
        verify(model, times(1)).addAttribute(eq( "recipes"), anySet());
    }
}