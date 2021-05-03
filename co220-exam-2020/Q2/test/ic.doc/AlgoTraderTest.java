package test.ic.doc;

import com.londonstockexchange.InvtMarket;
import com.londonstockexchange.StockPrice;
import com.londonstockexchange.TickerSymbol;
import ic.doc.Alert;
import ic.doc.AlgoTrader;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class AlgoTraderTest {

    static final TickerSymbol GOOG = TickerSymbol.GOOG;
    static final TickerSymbol MSFT = TickerSymbol.MSFT;
    static final TickerSymbol APPL = TickerSymbol.APPL;

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    Alert alert = context.mock(Alert.class);

    @Test
    public void TestBuyStockWhenRising(){
        context.checking(
                new Expectations(){
                    {
                        exactly(1).of(alert).buy(String.valueOf(GOOG));
                        exactly(1).of(alert).buy(String.valueOf(MSFT));
                        exactly(1).of(alert).buy(String.valueOf(APPL));
                    }
                });
        AlgoTrader trader = new AlgoTrader();
        trader.trade(new TestMarket(0), alert);
        trader.trade(new TestMarket(1000), alert);
    }

    @Test
    public void TestSellStockWhenFalling(){
        context.checking(
                new Expectations(){
                    {
                        exactly(1).of(alert).sell(String.valueOf(GOOG));
                        exactly(1).of(alert).sell(String.valueOf(MSFT));
                        exactly(1).of(alert).sell(String.valueOf(APPL));
                    }
                });
        AlgoTrader trader = new AlgoTrader();
        trader.trade(new TestMarket(1000), alert);
        trader.trade(new TestMarket(0), alert);
    }

  private static class TestMarket implements InvtMarket {
        int scale = 0;

        public TestMarket(int scale){
            this.scale = scale;
        }

        @Override
        public StockPrice currentPriceFor(TickerSymbol stock) {
        return new StockPrice(stock, (int) (Math.random() * scale));
        }
    }
}
