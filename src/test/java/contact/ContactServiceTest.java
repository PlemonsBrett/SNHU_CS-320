import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {

    private ContactService contactService;
    private Contact testContact;

    @BeforeEach
    void setUp() {
        contactService = new ContactService();
        testContact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
    }

    @Test
    void testAddContact() {
        contactService.addContact(testContact);
        assertEquals(testContact, contactService.getContact("1234567890"));
    }

    @Test
    void testAddDuplicateContact() {
        contactService.addContact(testContact);
        Contact duplicateContact = new Contact("1234567890", "Jane", "Smith", "9876543210", "456 Oak St");
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(duplicateContact);
        });
    }

    @Test
    void testDeleteContact() {
        contactService.addContact(testContact);
        contactService.deleteContact("1234567890");
        assertNull(contactService.getContact("1234567890"));
    }

    @Test
    void testDeleteNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact("nonexistent");
        });
    }
}
