package irr4.gestion_publication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import irr4.gestion_publication.models.Organisme;
import irr4.gestion_publication.services.OrganismeService;

import java.util.List;

@Controller
@RequestMapping("/admin/organismes")
public class OrganismeController {

    @Autowired
    private OrganismeService organismeService;

    @GetMapping("")
    public String index(Model model){
        List<Organisme> organismes = organismeService.getOrganismes();
        if(organismes.size() > 0){
            model.addAttribute("organismes", organismes);
        }
        return "pages/admin/organismes/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Organisme organisme = new Organisme();
        model.addAttribute("organisme", organisme);
        return "pages/admin/organismes/create";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute Organisme organisme, Model model, RedirectAttributes redirectAttributes){
        Organisme createdOrganisme = organismeService.addOrganisme(organisme);
        redirectAttributes.addFlashAttribute("message", "ajouté avec succès !");
        return "redirect:/admin/organismes";
    }



    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Organisme organisme = organismeService.getOrganisme(id);
        model.addAttribute("organisme", organisme);
        return "pages/admin/organismes/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute Organisme organisme , @PathVariable String id,RedirectAttributes redirectAttributes) {
        organisme.setId(id);
        Organisme updatedOrganisme = organismeService.addOrganisme(organisme);
        redirectAttributes.addFlashAttribute("message","modification effectuée avec succès !" );
        return "redirect:/admin/organismes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes){
        Organisme organisme = organismeService.deleteOrganisme(id);
        redirectAttributes.addFlashAttribute("message", "supprimé avec succès !");
        return "redirect:/admin/organismes";
    }


}
