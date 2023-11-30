/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacos.taco_cloud;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.taco_cloud.Ingredient;
import tacos.taco_cloud.Ingredient.Type;
import tacos.taco_cloud.Taco;

/**
 *
 * @author NguyenHuuTuan
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private IngredientRepository ingredientRepo;
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);
        
        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
        }
    }
    
    @GetMapping
    public String showDesignForm(Model model){
        model.addAttribute("taco",new Taco());
        return "design";
    }
    
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        List<Ingredient> ingrList = new ArrayList<>();
        for(Ingredient ingredient:ingredients){
            if(ingredient.getType().equals(type)){
                ingrList.add(ingredient);
            }
        }
        return ingrList;
    }
    
    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors){
        if(errors.hasErrors()){
            return "design";
        }
        
        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }
}
