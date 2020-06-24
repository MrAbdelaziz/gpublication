package irr4.gestion_publication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import irr4.gestion_publication.models.Category;
import irr4.gestion_publication.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category getCategory(String id){
        return categoryRepository.findById(id).get();
    }

    public Category deleteCategory(String id){
        Category category = getCategory(id);
        if(category != null){
            categoryRepository.deleteById(id);
            return category;
        }
        return  null;
    }

}
