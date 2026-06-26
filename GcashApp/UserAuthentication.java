    package GcashApp;

    import java.util.ArrayList;

    public class UserAuthentication {

        private ArrayList<User> users = new ArrayList<>();
        private User currentUser = null;
        private int nextId = 1;

        public void register(String name, String email, String number, String pin) {

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }

            if (!email.contains("@")) {
                System.out.println("Invalid email.");
                return;
            }

            if (number.length() != 11) {
                System.out.println("Mobile number must be 11 digits.");
                return;
            }

            if (pin.length() != 4) {
                System.out.println("PIN must be 4 digits.");
                return;
            }

            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(email)) {
                    System.out.println("Email already exists.");
                    return;
                }
            }

            User newUser = new User(nextId++, name, email, number, pin);
            users.add(newUser);

            System.out.println("Registration Successful!");
            System.out.println("Your User ID is: " + newUser.getId());
        }

        public void login(String email, String pin) {

            for (User user : users) {

                if (user.getEmail().equalsIgnoreCase(email)
                        && user.getPin().equals(pin)) {

                    currentUser = user;

                    System.out.println("Login Successful!");
                    System.out.println("Welcome " + user.getName());
                    System.out.println("User ID: " + user.getId());

                    return;
                }
            }

            System.out.println("Invalid Email or PIN.");
        }

        public void changePin(String oldPin, String newPin) {

            if (currentUser == null) {
                System.out.println("Please login first.");
                return;
            }

            if (!currentUser.getPin().equals(oldPin)) {
                System.out.println("Incorrect current PIN.");
                return;
            }

            if (newPin.length() != 4) {
                System.out.println("PIN must be 4 digits.");
                return;
            }

            currentUser.setPin(newPin);

            System.out.println("PIN changed successfully.");
        }

        public void logout() {

            if (currentUser == null) {
                System.out.println("No user is currently logged in.");
                return;
            }

            System.out.println(currentUser.getName() + " logged out.");
            currentUser = null;
        }

        public User getCurrentUser() {
            return currentUser;
        }
    }