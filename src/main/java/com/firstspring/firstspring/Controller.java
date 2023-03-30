package com.firstspring.firstspring;


import com.firstspring.firstspring.model.Photo;
import com.firstspring.firstspring.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/photos")
public class Controller {

    @Autowired
    private PhotoRepository repo;
    @GetMapping("/{photoid}")
    public byte[] getPhotoById(@PathVariable String photoid){
        Photo photo =repo.findById(UUID.fromString(photoid)).get();
        return photo.getContent();
    }



    @PostMapping("")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes)throws IOException {
        Photo newPhoto = new Photo();
        newPhoto.setContent(file.getBytes());
        newPhoto.setContentType(file.getContentType());
        newPhoto.setOriginalFilename(file.getOriginalFilename());

        Photo photo1 = repo.save(newPhoto);

        return photo1.getId().toString();


    }



}
