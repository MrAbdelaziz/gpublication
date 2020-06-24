package irr4.gestion_publication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import irr4.gestion_publication.models.Category;
import irr4.gestion_publication.services.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String index(Model model){
        List<Category> categories = categoryService.getCategories();
        if(categories.size() > 0){
            model.addAttribute("categories", categories);
        }
        return "pages/admin/categories/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return "pages/admin/categories/create";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute Category category, Model model, RedirectAttributes redirectAttributes){
        Category createdCategory = categoryService.addCategory(category);
        redirectAttributes.addFlashAttribute("message","ajouté avec succès !");
        return "redirect:/admin/categories";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Category category = categoryService.getCategory(id);
        model.addAttribute("category", category);
        return "pages/admin/categories/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute Category category , @PathVariable String id,RedirectAttributes redirectAttributes) {
        category.setId(id);
        Category updatedCategory = categoryService.addCategory(category);
        redirectAttributes.addFlashAttribute("message","modification effectuée avec succès !" );
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes){
        Category category = categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("message","supprimé avec succès !");
        return "redirect:/admin/categories";
    }

}
