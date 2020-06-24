package irr4.gestion_publication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import irr4.gestion_publication.models.Pdf;

@Service
public class PdfService {

    public Pdf addFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        try {
            Pdf file = new Pdf();
            file.setName(fileName);
            file.setType(multipartFile.getContentType());
            file.setFile(multipartFile.getBytes());
            return file;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
