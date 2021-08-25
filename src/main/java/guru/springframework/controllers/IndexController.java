package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import guru.springframework.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        log.debug("IndexController::getIndexPage");

        model.addAttribute("recipes", recipeService.GetListOfRecipe());

        return "index";
    }
}
