# Contact, Task, and Appointment Service Implementation

This directory contains the implementation of three service classes demonstrating test-driven development and quality assurance practices. The project includes comprehensive unit tests for all components.

## Project Requirements

- Java JDK 17
- Maven 3.x
- JUnit 5.9.2

## Project Structure

```
src/
├── main/
│   ├── contact/
│   │   ├── Contact.java           # Contact entity class
│   │   └── ContactService.java    # Contact management service
│   ├── task/
│   │   ├── Task.java             # Task entity class
│   │   └── TaskService.java      # Task management service
│   └── appointment/
│       ├── Appointment.java      # Appointment entity class
│       └── AppointmentService.java # Appointment management service
└── test/
    ├── contact/
    │   ├── ContactTest.java      # Contact class unit tests
    │   └── ContactServiceTest.java # ContactService class unit tests
    ├── task/
    │   ├── TaskTest.java         # Task class unit tests
    │   └── TaskServiceTest.java  # TaskService class unit tests
    └── appointment/
        ├── AppointmentTest.java  # Appointment class unit tests
        └── AppointmentServiceTest.java # AppointmentService class unit tests
```

## Building the Project

From the root directory of the project, run:

```bash
mvn clean install
```

## Running Tests

To run all tests:

```bash
mvn test
```

To run tests for a specific class:

```bash
# For Contact tests
mvn test -Dtest=ContactTest
mvn test -Dtest=ContactServiceTest

# For Task tests
mvn test -Dtest=TaskTest
mvn test -Dtest=TaskServiceTest

# For Appointment tests
mvn test -Dtest=AppointmentTest
mvn test -Dtest=AppointmentServiceTest
```

## Service Specifications

### Contact Service
- **Contact Requirements**:
  - Contact ID: required, unique, ≤10 characters, immutable
  - First Name: required, ≤10 characters
  - Last Name: required, ≤10 characters
  - Phone: required, exactly 10 digits
  - Address: required, ≤30 characters
- **Service Operations**:
  - Add new contact
  - Delete contact by ID
  - Update contact fields (excluding ID)

### Task Service
- **Task Requirements**:
  - Task ID: required, unique, ≤10 characters, immutable
  - Name: required, ≤20 characters
  - Description: required, ≤50 characters
- **Service Operations**:
  - Add new task
  - Delete task by ID
  - Update task fields (excluding ID)

### Appointment Service
- **Appointment Requirements**:
  - Appointment ID: required, unique, ≤10 characters, immutable
  - Date: required, must be a future date
  - Description: required, ≤50 characters
- **Service Operations**:
  - Add new appointment
  - Delete appointment by ID
  - Update appointment fields (excluding ID)

## Code Examples

### Creating and Testing a Contact

```java
// Create a new contact
Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");

// Add to service
ContactService contactService = new ContactService();
contactService.addContact(contact);

// Update contact information
contactService.updateContact("1234567890", "Jane", null, null, "456 Oak St");
```

### Running a Specific Test

```java
// In ContactTest.java
@Test
public void testValidContact() {
    Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
    assertEquals("1234567890", contact.getContactId());
    assertEquals("John", contact.getFirstName());
    assertEquals("Doe", contact.getLastName());
    assertEquals("1234567890", contact.getPhone());
    assertEquals("123 Main St", contact.getAddress());
}
```

## Common Issues and Solutions

1. **Test Failures Due to Date Comparison**
   - When testing Appointments, ensure you're creating future dates for valid tests
   - Use `Calendar` or `Date` utilities to generate appropriate test dates

2. **Phone Number Validation**
   - Phone numbers must be exactly 10 digits
   - Use regex pattern `\\d{10}` for validation

3. **ID Uniqueness**
   - Services enforce unique IDs
   - Always check for existing IDs before adding new items

## Contributing

When adding new features or modifications:
1. Write tests first (TDD approach)
2. Ensure all tests pass
3. Maintain existing code style and naming conventions
4. Update documentation as needed