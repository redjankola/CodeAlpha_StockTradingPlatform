package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import model.Stock;
import service.MarketService;
import service.TradingService;

import java.util.List;

public class TradingController {

    private final MarketService marketService;
    private final TradingService tradingService;

    public TradingController() {

        marketService = new MarketService();
        tradingService = new TradingService(marketService);
    }

    public List<Stock> getStocks() {
        return marketService.getStocks();
    }

    public void refreshMarket() {
        marketService.simulateMarketFluctuation();
    }

    public void savePortfolio() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Save Portfolio");
        alert.setHeaderText("Portfolio Saved");
        alert.setContentText(
                "Your portfolio has been saved successfully."
        );

        alert.showAndWait();
    }

    public void buyStockDialog() {

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Buy Stock");
        dialog.setHeaderText("Buy Stock");
        dialog.setContentText("Enter stock symbol:");

        dialog.showAndWait();
    }

    public void sellStockDialog() {

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Sell Stock");
        dialog.setHeaderText("Sell Stock");
        dialog.setContentText("Enter stock symbol:");

        dialog.showAndWait();
    }
}