import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Stock entity to hold market pricing
class Stock {
    private String symbol;
    private String companyName;
    private double price;

    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

// User portfolio entity
class Portfolio {
    private double balance;
    private Map<String, Integer> holdings; // Symbol -> Quantity

    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
        this.holdings = new HashMap<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean buyStock(String symbol, int quantity, double pricePerShare) {
        double totalCost = quantity * pricePerShare;
        if (totalCost > balance) {
            System.out.println("Error: Insufficient balance to complete this purchase.");
            return false;
        }

        balance -= totalCost;
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        System.out.printf("Success: Bought %d shares of %s for $%.2f\n", quantity, symbol, totalCost);
        return true;
    }

    public boolean sellStock(String symbol, int quantity, double pricePerShare) {
        int currentQuantity = holdings.getOrDefault(symbol, 0);
        if (currentQuantity < quantity) {
            System.out.println("Error: You do not own enough shares to sell.");
            return false;
        }

        double totalEarning = quantity * pricePerShare;
        balance += totalEarning;
        int remaining = currentQuantity - quantity;

        if (remaining == 0) {
            holdings.remove(symbol);
        } else {
            holdings.put(symbol, remaining);
        }

        System.out.printf("Success: Sold %d shares of %s for $%.2f\n", quantity, symbol, totalEarning);
        return true;
    }

    public void displayPortfolio(Map<String, Stock> market) {
        System.out.println("\n=========================================");
        System.out.println("             MY PORTFOLIO                ");
        System.out.println("=========================================");
        System.out.printf("Available Cash: $%.2f\n", balance);
        System.out.println("-----------------------------------------");
        System.out.printf("%-10s %-10s %-15s %-15s\n", "Symbol", "Shares", "Current Price", "Market Value");
        System.out.println("-----------------------------------------");

        double totalHoldingsValue = 0;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int qty = entry.getValue();
            double price = market.get(symbol).getPrice();
            double value = qty * price;
            totalHoldingsValue += value;

            System.out.printf("%-10s %-10d $%-14.2f $%-14.2f\n", symbol, qty, price, value);
        }

        double totalNetWorth = balance + totalHoldingsValue;
        System.out.println("-----------------------------------------");
        System.out.printf("Holdings Value : $%.2f\n", totalHoldingsValue);
        System.out.printf("Total Net Worth: $%.2f\n", totalNetWorth);
        System.out.println("=========================================");
    }
}

public class StockTradingPlatform {
    private static Map<String, Stock> market = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initMarketData();

        Portfolio userPortfolio = new Portfolio(10000.00); // Starting with $10,000 virtual balance

        System.out.println("=========================================");
        System.out.println("  CodeAlpha - Stock Trading Platform     ");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\n1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View My Portfolio");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayMarketData();
                    break;

                case "2":
                    displayMarketData();
                    System.out.print("\nEnter Stock Symbol to BUY: ");
                    String buySymbol = scanner.nextLine().trim().toUpperCase();
                    if (!market.containsKey(buySymbol)) {
                        System.out.println("Error: Invalid stock symbol.");
                        break;
                    }
                    System.out.print("Enter quantity to buy: ");
                    try {
                        int buyQty = Integer.parseInt(scanner.nextLine().trim());
                        if (buyQty <= 0) {
                            System.out.println("Quantity must be greater than zero.");
                            break;
                        }
                        userPortfolio.buyStock(buySymbol, buyQty, market.get(buySymbol).getPrice());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numeric quantity.");
                    }
                    break;

                case "3":
                    System.out.print("\nEnter Stock Symbol to SELL: ");
                    String sellSymbol = scanner.nextLine().trim().toUpperCase();
                    if (!market.containsKey(sellSymbol)) {
                        System.out.println("Error: Invalid stock symbol.");
                        break;
                    }
                    System.out.print("Enter quantity to sell: ");
                    try {
                        int sellQty = Integer.parseInt(scanner.nextLine().trim());
                        if (sellQty <= 0) {
                            System.out.println("Quantity must be greater than zero.");
                            break;
                        }
                        userPortfolio.sellStock(sellSymbol, sellQty, market.get(sellSymbol).getPrice());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numeric quantity.");
                    }
                    break;

                case "4":
                    userPortfolio.displayPortfolio(market);
                    break;

                case "5":
                    System.out.println("Thank you for using CodeAlpha Trading Platform. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 5.");
            }
        }
    }

    private static void initMarketData() {
        market.put("AAPL", new Stock("AAPL", "Apple Inc.", 185.50));
        market.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 145.20));
        market.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 420.10));
        market.put("AMZN", new Stock("AMZN", "Amazon.com Inc.", 175.30));
        market.put("TSLA", new Stock("TSLA", "Tesla Inc.", 195.40));
    }

    private static void displayMarketData() {
        System.out.println("\n=========================================");
        System.out.println("              MARKET DATA                ");
        System.out.println("=========================================");
        System.out.printf("%-10s %-20s %-10s\n", "Symbol", "Company Name", "Price");
        System.out.println("-----------------------------------------");
        for (Stock stock : market.values()) {
            System.out.printf("%-10s %-20s $%-10.2f\n", stock.getSymbol(), stock.getCompanyName(), stock.getPrice());
        }
        System.out.println("=========================================");
    }
}