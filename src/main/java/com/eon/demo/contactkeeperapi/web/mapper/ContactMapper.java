package com.eon.demo.contactkeeperapi.web.mapper;

import com.eon.demo.contactkeeperapi.data.model.ContactEntity;
import com.eon.demo.contactkeeperapi.data.model.ContactType;
import com.eon.demo.contactkeeperapi.web.model.ContactGetResource;
import com.eon.demo.contactkeeperapi.web.model.ContactPostResource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ContactMapper {

    public ContactEntity mapToContactEntity(ContactPostResource contactResource) {
        return ContactEntity.builder()
                .contactType(getTypeFromResource(contactResource))
                .email(contactResource.getEmail())
                .firstName(contactResource.getFirstName())
                .lastName(contactResource.getLastName())
                .phone(contactResource.getPhone())
                .build();
    }

    public ContactGetResource mapToContactResource(ContactEntity contact) {
        return ContactGetResource.builder()
                .id(contact.getId())
                .email(contact.getEmail())
                .lastName(contact.getLastName())
                .firstName(contact.getFirstName())
                .phone(contact.getPhone())
                .type(contact.getContactType().name().toLowerCase(Locale.ROOT))
                .build();
    }

    private ContactType getTypeFromResource(ContactPostResource contactResource) {
        if (contactResource.getType().equals("professional")) {
            return ContactType.PROFESSIONAL;
        }
        return ContactType.PRIVATE;
    }

}
