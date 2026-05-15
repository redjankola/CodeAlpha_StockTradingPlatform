package service;

import model.*;

public class TradingService {

    private MarketService marketService;

    public TradingService(MarketService marketService) {
        this.marketService = marketService;
    }

    public void buyStock(User user,
                         String symbol,
                         int quantity) {

        Stock stock = marketService.getStock(symbol);

        if (stock == null) {
            throw new IllegalArgumentException("Stock not found.");
        }

        double totalCost = stock.getPrice() * quantity;

        if (user.getBalance() < totalCost) {
            throw new IllegalArgumentException("Not enough balance.");
        }

        user.withdraw(totalCost);

        user.getPortfolio()
                .buyStock(symbol, quantity);

        user.addTransaction(
                new Transaction(
                        symbol,
                        quantity,
                        stock.getPrice(),
                        TransactionType.BUY
                )
        );

        System.out.println("Purchase successful.");
    }

    public void sellStock(User user,
                          String symbol,
                          int quantity) {

        Stock stock = marketService.getStock(symbol);

        if (stock == null) {
            throw new IllegalArgumentException("Stock not found.");
        }

        user.getPortfolio()
                .sellStock(symbol, quantity);

        double totalRevenue =
                stock.getPrice() * quantity;

        user.deposit(totalRevenue);

        user.addTransaction(
                new Transaction(
                        symbol,
                        quantity,
                        stock.getPrice(),
                        TransactionType.SELL
                )
        );

        System.out.println("Sale successful.");
    }

    public void displayPortfolio(User user) {

        System.out.println("\n========= PORTFOLIO =========");

        double totalValue = 0;

        for (var entry :
                user.getPortfolio()
                        .getHoldings()
                        .entrySet()) {

            String symbol = entry.getKey();
            int quantity = entry.getValue();

            Stock stock =
                    marketService.getStock(symbol);

            double stockValue =
                    stock.getPrice() * quantity;

            totalValue += stockValue;

            System.out.printf(
                    "%s -> %d shares | Value: $%.2f%n",
                    symbol,
                    quantity,
                    stockValue
            );
        }

        System.out.printf(
                "Cash Balance: $%.2f%n",
                user.getBalance()
        );

        System.out.printf(
                "Total Portfolio Value: $%.2f%n",
                totalValue + user.getBalance()
        );
    }

    public void displayTransactions(User user) {

        System.out.println("\n====== TRANSACTION HISTORY ======");

        user.getTransactions()
                .forEach(System.out::println);
    }
}