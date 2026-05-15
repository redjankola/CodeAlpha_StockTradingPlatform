package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Portfolio implements Serializable {

    private Map<String, Integer> holdings = new HashMap<>();

    public void buyStock(String symbol, int quantity) {
        holdings.put(symbol,
                holdings.getOrDefault(symbol, 0) + quantity);
    }

    public void sellStock(String symbol, int quantity) {

        if (!holdings.containsKey(symbol)) {
            throw new IllegalArgumentException("Stock not owned.");
        }

        int currentQty = holdings.get(symbol);

        if (quantity > currentQty) {
            throw new IllegalArgumentException("Insufficient shares.");
        }

        if (quantity == currentQty) {
            holdings.remove(symbol);
        } else {
            holdings.put(symbol, currentQty - quantity);
        }
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }
}