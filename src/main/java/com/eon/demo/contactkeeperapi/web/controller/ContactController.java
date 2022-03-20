package com.eon.demo.contactkeeperapi.web.controller;

import com.eon.demo.contactkeeperapi.service.ContactService;
import com.eon.demo.contactkeeperapi.web.mapper.ContactMapper;
import com.eon.demo.contactkeeperapi.web.model.ContactGetResource;
import com.eon.demo.contactkeeperapi.web.model.ContactPostResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
public class ContactController {

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    @GetMapping("/api/contacts")
    public ResponseEntity<List<ContactGetResource>> getContacts() {
        var contacts = contactService.getContactsForLoggedInUser().stream()
                .map(contactEntity -> contactMapper.mapToContactResource(contactEntity))
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/api/contacts/{id}")
    public ResponseEntity<ContactGetResource> getContact(@PathVariable long id) {
        var contact = contactService.getContactById(id);
        return ResponseEntity.ok(contactMapper.mapToContactResource(contact));
    }

    @PostMapping("/api/contacts")
    public ResponseEntity<ContactGetResource> createContact(@RequestBody ContactPostResource createContactResource) {
        var contact = contactService.createContact(contactMapper.mapToContactEntity(createContactResource));
        var uri = linkTo(methodOn(ContactController.class).getContact(contact.getId())).toUri();
        return ResponseEntity.created(uri).body(contactMapper.mapToContactResource(contact));
    }

    @PutMapping("/api/contacts/{id}")
    public ResponseEntity<ContactGetResource> updateContact(@PathVariable long id, @RequestBody ContactPostResource createContactResource) {
        var contact = contactService.getContactById(id);
        contact = contactMapper.mapToContactEntity(createContactResource);
        return ResponseEntity.ok(contactMapper.mapToContactResource(contactService.updateContact(contact)));
    }

    @DeleteMapping("/api/contacts/{userId}")
    public ResponseEntity<Void> deleteContact(@PathVariable long userId) {
        contactService.deleteContactByUserId(userId);
        return ResponseEntity.noContent().build();
    }

}
