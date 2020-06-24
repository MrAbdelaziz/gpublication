package irr4.gestion_publication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import irr4.gestion_publication.models.Author;
import irr4.gestion_publication.models.Organisme;
import irr4.gestion_publication.services.AuthorService;
import irr4.gestion_publication.services.OrganismeService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private OrganismeService organismeService;
    private static String pubId;

    @GetMapping("/authors")
    public String index(Model model){
        List<Author> authors = authorService.getAuthors();
        if(authors.size() > 0){
            model.addAttribute("authors", authors);
        }
        return "pages/admin/authors/index";
    }

    @GetMapping("/authors/create")
    public String create(Model model){
        Author author = new Author();
        List<Organisme> organismes = organismeService.getOrganismes();
        model.addAttribute("author", author);
        model.addAttribute("organismes", organismes);
        return "pages/admin/authors/create";
    }

    @PostMapping("/authors/store")
    public String store(@ModelAttribute Author author, Model model, RedirectAttributes redirectAttributes){
        author.setAdresse(author.getAdresse());
        author.setOrganisme(author.getOrganisme());
        Author createdAuthor = authorService.addAuthor(author);
        redirectAttributes.addFlashAttribute("message", "ajouté avec succès !");
        return "redirect:/admin/authors";
    }

    @GetMapping("/authors/show/{id}")
    public String show(@PathVariable String id, Model model){
        Author author = authorService.getAuthor(id);
        model.addAttribute("author", author);
        return  "pages/admin/authors/show";
    }

    @GetMapping("/authors/delete/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes){
        Author author = authorService.deleteAuthor(id);
        redirectAttributes.addFlashAttribute("message","auteur supprimé avec succès !");
        return "redirect:/admin/authors";
    }

    @GetMapping("/publication/{id}/authors")
    public String authors(@PathVariable String id ,Model model){
        pubId = id;
        List<Author> authors = authorService.getAuthors();
        List<Author> pubAuthors = authorService.getPublicationAuthors(id);
        model.addAttribute("author", new Author());
        model.addAttribute("authors", authors);
        if(pubAuthors.size() > 0){
            model.addAttribute("pubAuthors", pubAuthors);
        }
        return "pages/admin/publications/post_authors/index";
    }

    @PostMapping("/publication/authors/create")
    public String addAuthorToThePost(@ModelAttribute Author author ,Model model, RedirectAttributes redirectAttributes){
        Author author1 = authorService.addAuthorToPost(pubId, author.getId());
        redirectAttributes.addFlashAttribute("message","ajouté avec succès !");
        return "redirect:/admin/publication/" + pubId + "/authors";
    }

    @GetMapping("/publication/authors/delete/{id}")
    public String deleteAuthorFromPublication(@PathVariable String id, Model model, RedirectAttributes redirectAttributes){
        Author author = authorService.deleteAuthorFormPublication(pubId, id);
        redirectAttributes.addFlashAttribute("message"," supprimé avec succès !");
        return "redirect:/admin/publication/" + pubId + "/authors";
    }

}
