/**
 * @author: Aldo Jabes
 */
package models;

public class PayObject {
    private User payer;
    private User payee;
    private int amount;
    public PayObject(User payee, User payer, int amount){
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    public User getPayer() {
        return payer;
    }

    public User getPayee() {
        return payee;
    }

    public int getAmount() {
        return amount;
    }
}
