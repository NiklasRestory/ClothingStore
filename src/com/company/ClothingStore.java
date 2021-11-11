package com.company;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.Scanner;

public class ClothingStore {
    String storeName;

    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();

    ArrayList<Clothing> clothesForSale = new ArrayList<>();

    Person currentUser = new Person("", "", "");
    UserType currentUserType = UserType.employee;

    public ClothingStore(String storeName) {
        this.storeName = storeName;

        customers.add(new Customer("Joe", "JoeStreet", "Male"));
        customers.add(new Customer("Luna", "LunaStreet", "Female"));
        customers.add(new Customer("Jake", "JakeStreet", "Male"));
        customers.add(new Customer("XYZ-566", "SpaceStreet", "N/A"));

        employees.add(new Employee("Joline", "JolineStreet", "Female", 20000, "Salaryman"));
        employees.add(new Employee("Jim", "JimStreet", "Male", 45000, "CEO"));

        clothesForSale.add(new Pants("Denim pants", 500, "Yike", Clothing.Size.ExtraLarge, true));
        clothesForSale.add(new Pants("Cotton pants", 120, "Loke", Clothing.Size.Medium, false));
        clothesForSale.add(new Shirt("Standard shirt", 20, "Yike", Clothing.Size.Small, false));
        clothesForSale.add(new Shirt("Shirt with hoodie", 100, "Loke", Clothing.Size.Large, true));

    }

    public String GetValidString(String inputMessage, String invalidInputMessage) {
        boolean done = false;
        String result = "";
        while (!done) {
            System.out.println(inputMessage);
            Scanner scanner = new Scanner(System.in);
            result = scanner.nextLine();
            if (result.isEmpty()) {
                System.out.println(invalidInputMessage);
                continue;
            }
            done = true;
        }
        return result;
    }

    public String GetCustomerOrEmployee() {
        boolean done = false;
        String result = "";
        while(!done) {
            result = GetValidString("Are you customer or employee?", "Invalid option.").toLowerCase();
            if (result.equals("customer") || result.equals("employee")) {
                done = true;
            }
            else {
                System.out.println("That is not \"customer\" or \"employee\".");
            }
        }
        return result;
    }

    boolean quit = false;

    public void Visit() {
        System.out.println("Welcome to " + storeName + "!");
        while (!quit) {
            Login();
            if (currentUserType == UserType.customer) {
                quit = CustomerMenu();
                System.out.println("I hope to see you again!");
            }
            else {
                quit = EmployeeMenu();
                System.out.println("I'll expect you 7 o'clock tomorrow.");
            }
        }
    }

    private boolean CustomerMenu() { //If returns true, we're quitting.
        boolean done = false;
        boolean quitting = false;
        while (!done) {
            System.out.println("Purchase something? Write \"Buy\" to buy the things in your shoppingcart, \"Remove\" to remove from your shoppingcart, \n\"Quit\" to end the program, \"Logout\" to log out, \nor the name of a piece of clothing to add it to the shopping-cart.");
            QuitOptions quitOptions = ProcessUserInput();
            done = quitOptions.loggingOut;
            quitting = quitOptions.quitting;
        }
        return quitting;
    }

    public void ListClothesForSale() {
        System.out.println("Currently for sale: ");
        for (Clothing clothes: clothesForSale) {
            System.out.println(clothes.getName() + ": By " + clothes.getBrand() + " for " + clothes.getForGender() + ". Size: "  + clothes.getSize() + ". Price: " + clothes.getPrice() + " SEK.");
            if (clothes instanceof Pants) {
                System.out.println("Slim: " + ((Pants)clothes).isSlim());
            }
            else if (clothes instanceof Shirt) {
                Shirt shirt = (Shirt)clothes;
                if (shirt.getHoodie()) {
                    System.out.println("Has a hoodie!");
                }
            }
        }
    }

    private QuitOptions ProcessUserInput() {
        QuitOptions quitOptions = new QuitOptions();
        ListClothesForSale();
        String input = GetValidString("What do you do?", "That's not a valid string.").toLowerCase();
        switch (input) {
            case "buy":
                Purchase();
                break;
            case "remove":
                AskWhatToRemove();
                break;
            case "quit":
                quitOptions.loggingOut = true;
                quitOptions.quitting = true;
                break;
            case "logout":
                quitOptions.loggingOut = true;
                break;
            default:
                AddToCart(input);
                break;
        }
        return quitOptions;
    }

    private void AskWhatToRemove() {
        String input = GetValidString("What do you want to remove?", "").toLowerCase();
        Customer currentCustomer = (Customer) currentUser;
        boolean successful = currentCustomer.RemoveFromShoppingCart(input);
        if (successful) {
            System.out.println("Successfully removed " + input + " from shoppinglist.");
        }
        else {
            System.out.println("You don't have " + input + ".");
        }
    }

    private void AddToCart(String input) {
        Customer currentCustomer = (Customer)currentUser;
        Clothing clothingToBuy = FindClothing(input);
        if (clothingToBuy == null) {
            System.out.println("We don't have that for sale, I'm afraid.");
        }
        else {
            currentCustomer.addToShoppingCart(clothingToBuy);
            System.out.println("Added " + clothingToBuy.getName() + " to your shopping-cart!");
        }

    }

    private Clothing FindClothing(String input) {
        Clothing clothingToBuy = null;
        for (Clothing clothing: clothesForSale) {
            if (input.equals(clothing.getName().toLowerCase())) {
                clothingToBuy = clothing;
                break;
            }
        }
        return clothingToBuy;
    }

    private void Purchase() {
        Customer customer = (Customer) currentUser;
        customer.purchase();
    }

    private boolean EmployeeMenu() {
        return false;
    }

    public void Login() {
        currentUserType = UserType.valueOf(GetCustomerOrEmployee());
        String username = GetValidString("Enter your name.", "That's not a name.");
        currentUser = GetUser(username, currentUserType);
        CheckIfRegistered(username);
    }

    public void CheckIfRegistered(String username) {
        if(currentUser == null) {
            if (currentUserType == UserType.customer) {
                RegisterCustomer(username);
            }
            else {
                RegisterEmployee(username);
            }
        }
    }

    private Person GetAddressAndGender() {
        String address = GetValidString("Input your address.", "We might need to ship things to you. Input valid address.");
        String gender = GetValidString("Input your gender.", "Invalid gender.");
        return new Person("", address, gender);
    }

    private void RegisterEmployee(String username) {
        System.out.println("First day on the job, huh?");
        Person tempPerson = GetAddressAndGender();
        System.out.println("And your starting salary is 10000 as a salaryman. Deal with it.");
        Employee employee = new Employee(username, tempPerson.getAddress(), tempPerson.getGender(), 10000, "Salaryman");
        employees.add(employee);
        System.out.println("Successfully registered. Now get to work.");
        currentUser = employee;
    }

    private void RegisterCustomer(String username) {
        System.out.println("Welcome!");
        Person tempPerson = GetAddressAndGender();
        Customer customer = new Customer(username, tempPerson.getAddress(), tempPerson.getGender());
        customers.add(customer);
        System.out.println("Successfully registered! I hope you have a good day here at " + storeName);
        currentUser = customer;
    }

    private Person GetUser(String username, UserType userTypeSought) {
        Person result = null;
        if (userTypeSought == UserType.customer) {
            for (Customer customer: customers) {
                if (customer.getName().equals(username)) {
                    result = customer;
                }
            }
        }
        else {
            for (Employee employee: employees) {
                if (employee.getName().equals(username)) {
                    result = employee;
                }
            }
        }

        return result;
    }

    enum UserType {
        customer,
        employee
    }
}
