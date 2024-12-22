
public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        // Validate contactId
        this.validateTextFields(contactId, "Contact ID");

        // Validate firstName
        this.validateTextFields(firstName, "First name");

        // Validate lastName
        this.validateTextFields(lastName, "Last name");

        // Validate phone
        this.validatePhone(phone);

        // Validate address
        this.validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setters for mutable fields
    public void setFirstName(String firstName) {
        this.validateTextFields(firstName, "First name");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.validateTextFields(lastName, "Last name");
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.validatePhone(phone);
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.validateAddress(address);
        this.address = address;
    }

    // Validators
    private void validateTextFields(String field, String fieldName) {
        if (field == null || field.length() > 10) {
            throw new IllegalArgumentException(fieldName + " must not be null and must be 10 characters or less");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone must be exactly 10 digits");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address must not be null and must be 30 characters or less");
        }
    }
}
