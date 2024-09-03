package com.prakash.ecom_proj.Controller;

import com.prakash.ecom_proj.Model.Event;
import com.prakash.ecom_proj.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EventController {

    @Autowired
    EventService service;

    //Getting all the Products list in Home Page
    @GetMapping("/products")
    public ResponseEntity<List<Event>>list(){
        return new ResponseEntity<>(service.get_List(), HttpStatus.OK);
    }
    @GetMapping("/product/{pid}")
    public ResponseEntity<Event> product(@PathVariable int pid){
        return new ResponseEntity<>(service.get_Product(pid),HttpStatus.OK);
    }

    //Adding the Event from User
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Event product,
                                        @RequestPart MultipartFile imageFile){
        try {
            Event product1 = service.addProduct(product,imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get the Image by Id
    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]>getProductImageById(@PathVariable int id){
        Event product = service.get_Product(id);
        byte[] imageFile = product.getImageData();
        return new ResponseEntity<>(imageFile,HttpStatus.OK);
    }

    //Searching the Event, based on KeyStroke and return the List
    @GetMapping("/products/{search}")
    public ResponseEntity<List<Event>>searchEvent(@RequestParam String keyStroke){
        List<Event>getEventList = service.getList(keyStroke);
        System.out.println("Searching with Keyword "+keyStroke);
        return new ResponseEntity<>(getEventList,HttpStatus.OK);
    }

//For Updating Value
    @PutMapping("/product/{id}")
    public ResponseEntity<String>updateEvent(@PathVariable int id,
                                             @RequestPart Event event,
                                             @RequestPart MultipartFile imageFile){
        Event event1;
        try {
            event1 = service.updateEvent(id,event,imageFile);
        }catch (IOException e){
            return new ResponseEntity<>("Failed to Update",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (event1 != null){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }else
            return new ResponseEntity<>("Failed to UPpdate",HttpStatus.BAD_REQUEST);
    }

    //For Deletin Particular event by id
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String>deleteEntity(@PathVariable int id){
        Event event = service.get_Product(id);
        if (event != null){
            service.deleteEvent(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Failed ti Delete",HttpStatus.BAD_GATEWAY);
    }

}












