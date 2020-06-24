package irr4.gestion_publication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import irr4.gestion_publication.models.Decipline;
import irr4.gestion_publication.services.DeciplineService;

import java.util.List;

@Controller
@RequestMapping("/admin/deciplines")
public class DeciplineController {

    @Autowired
    private DeciplineService deciplineService;

    @GetMapping("")
    public String index(Model model){
        List<Decipline> deciplines = deciplineService.getDeciplines();
        if(deciplines.size() > 0){
            model.addAttribute("deciplines", deciplines);
        }
        return "pages/admin/deciplines/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Decipline decipline = new Decipline();
        model.addAttribute("decipline", decipline);
        return "pages/admin/deciplines/create";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute Decipline decipline, Model model, RedirectAttributes redirectAttributes){
        Decipline createdDecipline = deciplineService.addDecipline(decipline);
        redirectAttributes.addFlashAttribute("message", "ajouté avec succès !");
        return "redirect:/admin/deciplines";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Decipline decipline = deciplineService.getDecipline(id);
        model.addAttribute("decipline", decipline);
        return "pages/admin/deciplines/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute Decipline decipline , @PathVariable String id,RedirectAttributes redirectAttributes) {
        decipline.setId(id);
        Decipline updatedDecipline = deciplineService.addDecipline(decipline);
        redirectAttributes.addFlashAttribute("message", "modification effectuée avec succès !" );
        return "redirect:/admin/deciplines";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes){
        Decipline decipline = deciplineService.deleteDecipline(id);
        redirectAttributes.addFlashAttribute("message", "supprimé avec succès !");
        return "redirect:/admin/deciplines";
    }

}
