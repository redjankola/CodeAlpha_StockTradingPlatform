package service;

import model.Stock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

public class MarketService {

    private Map<String, Stock> marketStocks = new HashMap<>();

    public MarketService() {

        marketStocks.put("AAPL",
                new Stock("AAPL", "Apple Inc.", 180));

        marketStocks.put("GOOG",
                new Stock("GOOG", "Google", 140));

        marketStocks.put("TSLA",
                new Stock("TSLA", "Tesla", 250));

        marketStocks.put("AMZN",
                new Stock("AMZN", "Amazon", 135));
    }

    public void displayMarket() {

        System.out.println("\n========= MARKET =========");

        marketStocks.values()
                .forEach(System.out::println);
    }

    public List<Stock> getStocks() {
        return marketStocks.values()
                .stream()
                .collect(Collectors.toList());
    }

    public Stock getStock(String symbol) {
        return marketStocks.get(symbol);
    }

    public void simulateMarketFluctuation() {

        Random random = new Random();

        for (Stock stock : marketStocks.values()) {

            double changePercent =
                    -5 + (10 * random.nextDouble());

            double newPrice =
                    stock.getPrice() * (1 + changePercent / 100);

            stock.updatePrice(newPrice);
        }
    }

    public Map<String, Stock> getMarketStocks() {
        return marketStocks;
    }
}