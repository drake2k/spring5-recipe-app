package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);

        Category american = categoryRepository.findByDescription("American").get();
        Category mexican = categoryRepository.findByDescription("Mexican").get();

        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByUom("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByUom("Tablespoon").get();
        UnitOfMeasure each = unitOfMeasureRepository.findByUom("Each").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByUom("Pinch").get();
        UnitOfMeasure pint = unitOfMeasureRepository.findByUom("Pint").get();
        UnitOfMeasure cup = unitOfMeasureRepository.findByUom("Cup").get();

        Recipe guacamole = new Recipe();
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setCookTime(0);

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");
        guacamole.setNotes(guacamoleNotes);

        //guacamole.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chilis, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setServings(4);
        guacamole.setPrepTime(10);
        guacamole.setSource("Simply Recipe");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        // should be only american but just to test the relationship, still curious if teacher will think its mexican hehe
        guacamole.getCategories().add(american);
        guacamole.getCategories().add(mexican);

        // ingredients
        guacamole.AddIngredient(new Ingredient("ripe avocados", new BigDecimal(2), each));
        guacamole.AddIngredient(new Ingredient("salt", new BigDecimal(0.25), teaspoon));
        guacamole.AddIngredient(new Ingredient("fresh lime or lemon juice", new BigDecimal(1), tablespoon));
        guacamole.AddIngredient(new Ingredient("minced red onions", new BigDecimal(3), tablespoon));
        guacamole.AddIngredient(new Ingredient("serrano or jalapeno", new BigDecimal(1), each));
        guacamole.AddIngredient(new Ingredient("cilantro, finely chopped", new BigDecimal(2), tablespoon));
        guacamole.AddIngredient(new Ingredient("freshly ground black pepper", new BigDecimal(1), pinch));
        guacamole.AddIngredient(new Ingredient(" ripe tomato, chopped (optional)", new BigDecimal(0.5), each));
        guacamole.AddIngredient(new Ingredient("Red radish or jicama slices for garnish (optional)", new BigDecimal(1), each));
        guacamole.AddIngredient(new Ingredient("salt", null,each));

        guacamole.setDirections("Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "\n Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                "\n Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n" +
                "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");

        recipes.add(guacamole);


        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.AddIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoon));
        tacosRecipe.AddIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoon));
        tacosRecipe.AddIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoon));
        tacosRecipe.AddIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoon));
        tacosRecipe.AddIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaspoon));
        tacosRecipe.AddIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), each));
        tacosRecipe.AddIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tablespoon));
        tacosRecipe.AddIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoon));
        tacosRecipe.AddIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tablespoon));
        tacosRecipe.AddIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoon));
        tacosRecipe.AddIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), each));
        tacosRecipe.AddIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cup));
        tacosRecipe.AddIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), each));
        tacosRecipe.AddIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), each));
        tacosRecipe.AddIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pint));
        tacosRecipe.AddIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), each));
        tacosRecipe.AddIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), each));
        tacosRecipe.AddIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cup));
        tacosRecipe.AddIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), each));

        tacosRecipe.getCategories().add(american);
        tacosRecipe.getCategories().add(mexican);

        tacosRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacosRecipe.setServings(4);
        tacosRecipe.setSource("Simply Recipes");

        recipes.add(tacosRecipe);


        return recipes;
    }

   /* @Override
    public void run(String... args) throws Exception {
        List<Recipe> recipes = getRecipes();

        recipeRepository.saveAll(recipes);
    }*/

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("RecipeBootstrap::onApplicationEvent");
        List<Recipe> recipes = getRecipes();
        recipeRepository.saveAll(recipes);
    }
}
