package com.example.CRUD.controller;

import com.example.CRUD.utillity.Security;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class AdminController {
    @GetMapping("/api/currentuser")
    public ResponseEntity index() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", Security.getCurrentUsername());
        result.put("Authorities", Security.getCurrentUserRole());
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
