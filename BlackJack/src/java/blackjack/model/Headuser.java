
package blackjack.model;

import blackjack.dao.UserDAO;
import blackjack.services.UserService;


/**
 *
 * @author Chayenne Jacques
 */
public class Headuser extends User {
    
    private String password;
    private String email;
    
    public Headuser(String nickname, int balance, String password, String email, Icon icon) {
        super(nickname, balance, icon);
        this.password=password;
        this.email=email;
    }
    /**
     * amount is het aantal credits die je wil toekennen aan een bepaalde speler(user)
     * @param amount
     * @param user 
     */
    public void addCredits(int amount, User user){
        blAddCredits(amount,user);
    }
    
    private void blAddCredits(int amount, User user)
    {
        /*
        Relatieve wijziging van credits.
        */
        user.setBalance(user.getBalance()+amount);
    }
    /*
    Wijzigt de gebruiker in de database.
    */
    public void editUser (User user){
        blEditUser(user);
    }
    
    private void blEditUser(User user)
    {
        UserService.editUser(user);
    }
    /*
    Voegt de gebruiker toe aan de database.
    */
    public void addUser(User user){
        blAddUser(user);
    }
    
    private void blAddUser(User user)
    {
        UserService.addUser(user);
    }
    /*
    Verwijdert de gebruiker met de meegegeven nickname uit de database.
    */
    public void removeUser(String nickname){
        blRemoveUser(nickname);
    }
    
    private void blRemoveUser(String nickname)
    {
        UserService.removeUser(nickname);
    }
}
