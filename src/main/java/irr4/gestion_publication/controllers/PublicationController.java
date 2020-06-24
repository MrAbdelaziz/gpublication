package irr4.gestion_publication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import irr4.gestion_publication.models.*;
import irr4.gestion_publication.services.CategoryService;
import irr4.gestion_publication.services.DeciplineService;
import irr4.gestion_publication.services.PdfService;
import irr4.gestion_publication.services.PublicationService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DeciplineService deciplineService;
    @Autowired
    private PdfService pdfService;

    @GetMapping("")
    public String index(Model model){
        List<Publication> publications = publicationService.getPublications();
        if(publications.size() > 0){
            model.addAttribute("publications", publications);
        }
        return "pages/admin/publications/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Publication publication = new Publication();
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("deciplines", deciplineService.getDeciplines());
        model.addAttribute("publication", publication);
        return "pages/admin/publications/create";
    }

    @PostMapping("/store")
    public String store(
            @RequestParam("files") MultipartFile[] files,
            @ModelAttribute Publication publication,
            Model model,
            RedirectAttributes redirectAttributes) {

        publication.setCategory(publication.getCategory());
        publication.setDecipline(publication.getDecipline());
        publication.setAuthors(new ArrayList<>());
        publication.setTags(new ArrayList<>());
        publication.setFile(new Pdf());

        for (MultipartFile file: files) {
            Pdf pdf = pdfService.addFile(file);
            publication.setFile(pdf);
        }

        Publication createdPublication = publicationService.addPublication(publication);
        redirectAttributes.addFlashAttribute("message","ajouté avec succès !");
        return "redirect:/admin/publications";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable String id, Model model){
        Publication publication = publicationService.getPublication(id);
        model.addAttribute("publication", publication);
        return  "pages/admin/publications/show";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes){
        Publication publication = publicationService.deletePublication(id);
        redirectAttributes.addFlashAttribute("message", "supprimé avec succès !");
        return "redirect:/admin/publications";
    }

    @GetMapping("/download/{pubId}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String pubId){
        Pdf pdf = publicationService.getPublication(pubId).getFile();
        if(pdf != null){
        	
        	  HttpHeaders headers = new HttpHeaders();
        	  headers.setContentType(MediaType.parseMediaType("application/pdf"));
        	  headers.add("Access-Control-Allow-Origin", "*");
        	  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        	  headers.add("Access-Control-Allow-Headers", "Content-Type");
        	  headers.add("Content-Disposition", "filename=" + pdf.getName());
        	  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        	  headers.add("Pragma", "no-cache");
        	  headers.add("Expires", "0");
        	

        	  InputStream targetStream = new ByteArrayInputStream(pdf.getFile());
        	  headers.setContentLength(pdf.getFile().length);
        	  ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
        	   new InputStreamResource(targetStream), headers, HttpStatus.OK);
        	  return response;
        	
        	
     
            

        }
        return null;
    }

}
