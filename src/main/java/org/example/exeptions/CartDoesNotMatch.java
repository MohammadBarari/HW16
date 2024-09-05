package org.example.exeptions;

public class CartDoesNotMatch extends Exception {
    public CartDoesNotMatch() {
        super("Cart does not match");
    }
}
