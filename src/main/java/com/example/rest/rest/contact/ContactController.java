package com.example.rest.rest.contact;

import com.example.rest.services.contact.ContactRequest;
import com.example.rest.services.contact.ContactResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/contact")
public interface ContactController {

    @ApiOperation(value = "Set user contacts", notes = "Set or change user contacts")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "Contacts changed"),
                    @ApiResponse(code = 404, message = "User not found")
            }
    )
    @PostMapping
    ResponseEntity<Void> setUserContacts(@RequestBody @Valid ContactRequest request);

    @ApiOperation(value = "Get user contacts", notes = "get user contacts")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "User or contacts not found")
            }
    )
    @GetMapping
    ResponseEntity<ContactResponse> getUserContacts(@RequestParam String name);
}
