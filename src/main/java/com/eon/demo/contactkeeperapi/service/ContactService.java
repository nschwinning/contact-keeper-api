package com.eon.demo.contactkeeperapi.service;

import com.eon.demo.contactkeeperapi.data.model.ContactEntity;
import com.eon.demo.contactkeeperapi.data.repository.ContactRepository;
import com.eon.demo.contactkeeperapi.exceptionhandling.model.AuthorizationException;
import com.eon.demo.contactkeeperapi.exceptionhandling.model.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserService userService;

    @Transactional
    public ContactEntity createContact(ContactEntity contact) {
        var currentUser = userService.getCurrentUser();

        contact.setUser(currentUser);
        return contactRepository.save(contact);
    }

    @Transactional
    public ContactEntity updateContact(ContactEntity contact) {
        var currentUser = userService.getCurrentUser();

        if (contact.getUser().getId()!=currentUser.getId()) {
            throw new AuthorizationException("Not alloewd to update user");
        }
        return contactRepository.save(contact);
    }

    public List<ContactEntity> getContactsForLoggedInUser(){
        var currentUser = userService.getCurrentUser();

        return contactRepository.getByUser(currentUser);
    }

    public ContactEntity getContactById(long contactId) {

        return  getContactsForLoggedInUser()
                .stream()
                .filter(contact -> contact.getId()==contactId)
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Could not find contact"));
    }

    @Transactional
    public void deleteContactByUserId(long contactId) {
        var contact = getContactById(contactId);

        contactRepository.delete(contact);

    }

}
