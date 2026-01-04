package banking.app;

import banking.model.User;
import banking.service.AuthService;
import banking.service.Session;

public class App {

    public static void main(String[] args) {

         
        AuthService authService = new AuthService();
        Session session = new Session();

        
        User user = new User("RAYAN", "1234", "CUSTOMER");
        authService.register(user);

     
        User loggedUser = authService.login("RAYAN", "1234");

        if (loggedUser != null) {
            session.login(loggedUser);
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }

       
        if (session.isLoggedIn()) {
            System.out.println("Current user: "
                    + session.getCurrentUser().getUsername());
        }

      
        session.logout();
        System.out.println("User logged out");

        System.out.println("Is logged in? " + session.isLoggedIn());
    }
}
