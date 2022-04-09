package com.example.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Contact;
import com.example.Repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public boolean saveContact(Contact contact) {
		contact.setActiveSw("Y");
		Contact save = contactRepository.save(contact);
		if (save.getContactId() != null) {
			return true;
		}

		return false;
	}

	@Override
	public List<Contact> getAllContacts() {
		Contact contact = new Contact();
		contact.setActiveSw("y");
		
		return contactRepository.findAll();
	}

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> findById = contactRepository.findById(contactId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {

		Optional<Contact> findById = contactRepository.findById(contactId);
		if (findById.isPresent()) {
			Contact cont = findById.get();
			cont.setActiveSw("N");
			contactRepository.save(cont);
			return true;
		}
		return false;
	}

}
