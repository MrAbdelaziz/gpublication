package irr4.gestion_publication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import irr4.gestion_publication.models.Organisme;
import irr4.gestion_publication.models.Tag;
import irr4.gestion_publication.services.TagService;

import java.util.List;

@Service
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;
    private static String pubId;

    @GetMapping("/publications/{id}/tags")
    public String index(@PathVariable String id, Model model){
        pubId = id;
        List<Tag> tags = tagService.getPostTags(id);
        Tag tag = new Tag();
        model.addAttribute("tag", tag);
        if(tags.size() > 0){
            model.addAttribute("tags", tags);
        }
        return "/pages/admin/tags/index";
    }

    @PostMapping("/tags/store")
    public String store(@ModelAttribute Tag tag, RedirectAttributes redirectAttributes){
        Tag tag1 = tagService.addTagToPost(pubId, tag);
        redirectAttributes.addFlashAttribute("message", tag1.getName() +" bien ajouter !");
        return "redirect:/admin/publications/"+pubId+"/tags";
    }

    @GetMapping("/tags/delete/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes){
        Tag tag = tagService.deleteTagfromPublication(id);
        redirectAttributes.addFlashAttribute("message", tag.getName() +" bien supprimer !");
        return "redirect:/admin/publications/" + pubId + "/tags";
    }

}
