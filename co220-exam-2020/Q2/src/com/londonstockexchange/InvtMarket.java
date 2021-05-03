package com.londonstockexchange;

public interface InvtMarket {
    public StockPrice currentPriceFor(TickerSymbol stock);
}
