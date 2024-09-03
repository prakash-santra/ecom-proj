package com.prakash.ecom_proj.Service;

import com.prakash.ecom_proj.Model.Event;
import com.prakash.ecom_proj.Repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepo repo;

    public List<Event> get_List() {
        return repo.findAll();
    }

    public Event get_Product(int pid) {
        return repo.findById(pid).orElse(new Event());
    }

    public Event addProduct(Event product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getName());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public List<Event> getList(String keyStroke) {
        return repo.searchList(keyStroke);
    }

    public Event updateEvent(int id, Event event, MultipartFile multipartFile) throws IOException {
        event.setImageName(multipartFile.getOriginalFilename());
        event.setImageType(multipartFile.getContentType());
        event.setImageData(multipartFile.getBytes());
        return repo.save(event);
    }
    public void deleteEvent(int id) {
        repo.deleteById(id);
    }
}
