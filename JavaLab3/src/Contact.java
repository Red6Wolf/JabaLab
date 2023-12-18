import java.util.HashMap;

public class Contact {
    private String name;
    private String email;
    private String additionalInfo;

    public Contact(String name, String email, String additionalInfo) {
        this.name = name;
        this.email = email;
        this.additionalInfo = additionalInfo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}

 class PhoneBook {
    private HashMap<String, Contact> contacts;

    public PhoneBook() {
        contacts = new HashMap<>();
    }

    public void addContact(String phoneNumber, Contact contact) {
        contacts.put(phoneNumber, contact);
    }

    public Contact findContact(String phoneNumber) {
        return contacts.get(phoneNumber);
    }

    public void removeContact(String phoneNumber) {
        contacts.remove(phoneNumber);
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        // Добавление контактов
        Contact contact1 = new Contact("John Doe", "john@example.com", "Additional info 1");
        Contact contact2 = new Contact("Jane Smith", "jane@example.com", "Additional info 2");

        phoneBook.addContact("+1234567890", contact1);
        phoneBook.addContact("+9876543210", contact2);

        // Поиск контакта по номеру телефона
        Contact foundContact = phoneBook.findContact("+1234567890");
        if (foundContact != null) {
            System.out.println("Found contact: " + foundContact.getName());
        } else {
            System.out.println("Contact not found.");
        }

        // Удаление контакта
        phoneBook.removeContact("+1234567890");
        Contact removedContact = phoneBook.findContact("+1234567890");
        if (removedContact == null) {
            System.out.println("Contact removed.");
        } else {
            System.out.println("Failed to remove contact.");
        }
    }
}
