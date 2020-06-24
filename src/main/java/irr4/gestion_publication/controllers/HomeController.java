package irr4.gestion_publication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import irr4.gestion_publication.models.Publication;
import irr4.gestion_publication.services.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private PublicationService publicationService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DeciplineService deciplineService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private SearchService searchService;

    @GetMapping("")
    public String hello(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "category.id", required = false) String category,
            @RequestParam(name = "decipline.id", required = false) String decipline,
            @RequestParam(name = "auteur.id", required = false) String auteur
            ,Model model
    ){
    	model.addAttribute("auteurs", authorService.getAuthors());
        model.addAttribute("deciplines", deciplineService.getDeciplines());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("publication", new Publication());

        Set<Publication> publications = new HashSet<>();
        if(title != null){
            publications.addAll(searchService.findPublicationByTitle(title));
        }else if(category != null){
            publications.addAll(searchService.getPublicationsByCategory(category));
        }else if(decipline != null){
            publications.addAll(searchService.getPublicationsByDecipline(decipline));
        }else if(auteur != null){
        	 publications.addAll(searchService.getPublicationsByAuteur(auteur));
        	 model.addAttribute("comptepub", searchService.getPublicationsByAuteur(auteur).size());
        } else{
            publications.addAll(publicationService.getPublications());
        }

        if(publications.size() > 0){
            model.addAttribute("publications", publications);
        }
        return "pages/base/home";
    }

}
