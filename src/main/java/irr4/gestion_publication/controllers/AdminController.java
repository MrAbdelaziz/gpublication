package irr4.gestion_publication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import irr4.gestion_publication.services.AuthorService;
import irr4.gestion_publication.services.CategoryService;
import irr4.gestion_publication.services.PublicationService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PublicationService publicationService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("authors", authorService.getAuthors().size());
        model.addAttribute("categories", categoryService.getCategories().size());
        model.addAttribute("publications", publicationService.getPublications().size());
        return "pages/admin/home";
    }
}
