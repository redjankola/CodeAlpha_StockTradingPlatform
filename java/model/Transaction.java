package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private String stockSymbol;
    private int quantity;
    private double price;
    private TransactionType type;
    private LocalDateTime timestamp;

    public Transaction(String stockSymbol, int quantity,
                       double price, TransactionType type) {

        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s %d shares of %s at $%.2f",
                timestamp,
                type,
                quantity,
                stockSymbol,
                price
        );
    }
}