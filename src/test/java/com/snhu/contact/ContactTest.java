package com.snhu.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class ContactTest {

    @Test
    public void testValidContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1234567890", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testInvalidContactId() {
        // When contactId is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });

        // When contactId is greater than 10 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testInvalidFirstName() {
        // When firstName is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", null, "Doe", "1234567890", "123 Main St");
        });

        // When firstName is greater than 10 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "JohnJohnJohn", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testInvalidLastName() {
        // When lastName is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", null, "1234567890", "123 Main St");
        });

        // When lastName is greater than 10 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "DoeDoeDoeDoe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testInvalidPhone() {
        // When phone is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", null, "123 Main St");
        });

        // When phone is less than 10 digits
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "123456789", "123 Main St");
        });

        // When phone is greater than 10 digits
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "12345678901", "123 Main St");
        });

        // When phone contains a non-numeric character
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "I234567890", "123 Main St");
        });
    }

    @Test
    void testInvalidAddress() {
        // When address is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "1234567890", null);
        });

        // When address is greater than 30 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "1234567890",
                    "This address is way too long to be valid in our system");
        });
    }
}
