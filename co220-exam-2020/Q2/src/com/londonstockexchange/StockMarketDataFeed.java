package com.londonstockexchange;

public class StockMarketDataFeed implements InvtMarket {

  private static final StockMarketDataFeed INSTANCE = new StockMarketDataFeed();

  private StockMarketDataFeed() {}

  public static StockMarketDataFeed getInstance() {
    return INSTANCE;
  }

  @Override
  public StockPrice currentPriceFor(TickerSymbol stock) {
    return new StockPrice(stock, (int) (Math.random() * 1000));
  }
}
