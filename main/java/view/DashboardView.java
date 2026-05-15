package view;

import controller.TradingController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import model.Stock;

public class DashboardView {

    private BorderPane root;

    private TableView<Stock> marketTable;
    private TableView<String> portfolioTable;
    private TableView<String> transactionTable;

    private Label balanceLabel;
    private Label portfolioValueLabel;

    private TradingController controller;

    public DashboardView() {

        controller = new TradingController();

        root = new BorderPane();

        root.setLeft(createSidebar());
        root.setCenter(createCenterPanel());
        root.setTop(createHeader());

        root.getStyleClass().add("background");
    }

    private Parent createHeader() {

        HBox header = new HBox();

        header.setPadding(new Insets(20));
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Stock Trading Dashboard");

        title.setFont(Font.font(28));

        title.getStyleClass().add("header-title");

        header.getChildren().add(title);

        return header;
    }

    private Parent createSidebar() {

        VBox sidebar = new VBox(20);

        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(260);

        sidebar.getStyleClass().add("sidebar");

        Button buyButton = new Button("Buy Stock");
        Button sellButton = new Button("Sell Stock");
        Button refreshButton = new Button("Refresh Market");
        Button saveButton = new Button("Save Portfolio");

        buyButton.setMaxWidth(Double.MAX_VALUE);
        sellButton.setMaxWidth(Double.MAX_VALUE);
        refreshButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setMaxWidth(Double.MAX_VALUE);

        buyButton.getStyleClass().add("primary-button");
        sellButton.getStyleClass().add("danger-button");
        refreshButton.getStyleClass().add("secondary-button");
        saveButton.getStyleClass().add("success-button");

        buyButton.setOnAction(e -> controller.buyStockDialog());
        sellButton.setOnAction(e -> controller.sellStockDialog());
        refreshButton.setOnAction(e -> controller.refreshMarket());
        saveButton.setOnAction(e -> controller.savePortfolio());

        sidebar.getChildren().addAll(
                buyButton,
                sellButton,
                refreshButton,
                saveButton
        );

        return sidebar;
    }

    private Parent createCenterPanel() {

        VBox container = new VBox(20);

        container.setPadding(new Insets(20));

        HBox cards = createStatCards();

        marketTable = createMarketTable();

        LineChart<Number, Number> chart = createPortfolioChart();

        container.getChildren().addAll(
                cards,
                marketTable,
                chart
        );

        return container;
    }

    private HBox createStatCards() {

        HBox cards = new HBox(20);

        VBox balanceCard = createCard("Balance", "$10,000");
        VBox portfolioCard = createCard("Portfolio Value", "$12,450");
        VBox profitCard = createCard("Profit", "+24.5%");

        cards.getChildren().addAll(
                balanceCard,
                portfolioCard,
                profitCard
        );

        return cards;
    }
    private VBox createCard(String title, String value) {

        VBox card = new VBox(10);

        card.setPadding(new Insets(20));
        card.setPrefWidth(250);

        card.getStyleClass().add("card");

        Label titleLabel = new Label(title);
        Label valueLabel = new Label(value);

        titleLabel.getStyleClass().add("card-title");
        valueLabel.getStyleClass().add("card-value");

        card.getChildren().addAll(titleLabel, valueLabel);

        return card;
    }

    private TableView<Stock> createMarketTable() {

        TableView<Stock> table = new TableView<>();

        TableColumn<Stock, String> symbolCol = new TableColumn<>("Symbol");
        symbolCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getSymbol()));

        TableColumn<Stock, String> companyCol = new TableColumn<>("Company");
        companyCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getCompanyName()));

        TableColumn<Stock, Number> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()));

        table.getColumns().addAll(symbolCol, companyCol, priceCol);

        table.setItems(FXCollections.observableArrayList(
                controller.getStocks()
        ));

        table.setPrefHeight(350);

        return table;
    }

    private LineChart<Number, Number> createPortfolioChart() {

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<Number, Number> chart =
                new LineChart<>(xAxis, yAxis);

        chart.setTitle("Portfolio Performance");

        XYChart.Series<Number, Number> series =
                new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>(1, 10000));
        series.getData().add(new XYChart.Data<>(2, 10400));
        series.getData().add(new XYChart.Data<>(3, 11000));
        series.getData().add(new XYChart.Data<>(4, 11750));
        series.getData().add(new XYChart.Data<>(5, 12450));

        chart.getData().add(series);

        chart.setPrefHeight(300);

        return chart;
    }

    public Parent getRoot() {
        return root;
    }
}

