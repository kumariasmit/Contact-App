package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AppPro.AppMessages;
import com.example.Entity.Contact;
import com.example.Service.ContactService;
import com.example.constant.AppConstant;

@RestController
@CrossOrigin
public class ContactRestController {
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private	AppMessages appMessages;

	@PostMapping("/contact")
	public String saveContacts(@RequestBody Contact contact) {
		boolean status = contactService.saveContact(contact);
		Map<String,String>messages  = appMessages.getMessages();
		if (status) {
			return messages.get(AppConstant.CONTACT_SAVE_SUCC);
		} else {
			return messages.get(AppConstant.CONTACT_SAVE_FAIL);
		}

	}

	@GetMapping("/contacts")
	public List<Contact> getAllContact() {
		return contactService.getAllContacts();

	}

	@GetMapping("/contact/{Cid}")
	public Contact getContactById(@PathVariable("Cid") Integer contactId) {

		return contactService.getContactById(contactId);
	}

	@DeleteMapping("/contact/{Cid}")
	public String deleteContact(@PathVariable("Cid") Integer contactId) {
		boolean status = contactService.deleteContactById(contactId);
		
		Map<String,String>messages  = appMessages.getMessages();
		if (status) {
			return messages.get(AppConstant.CONTACT_DEL_SUCC);
		}
		else {
			return messages.get(AppConstant.CONTACT_DEL_FAIL);
		}

	}
}
