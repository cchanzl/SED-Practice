package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class OrderProcessorTest {

  static final Book DESIGN_PATTERNS_BOOK =
      new Book("Design Patterns", "Gamma, Helm, Johnson and Vlissides", 25.99);
  static final Book LEGACY_CODE_BOOK =
      new Book("Working Effectively with Legacy Code", "Feathers", 29.99);

  static final Customer ALICE = new Customer("Alice Jones");
  static final Customer BOB = new Customer("Bob Smith");

  // write your tests here
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Warehouse warehouse = context.mock(Warehouse.class);
  Buyer buyer = context.mock(Buyer.class);
  PaymentSystem paymentSys = context.mock(PaymentSystem.class);

  final OrderProcessor orderProcessor = new OrderProcessor(warehouse, buyer, paymentSys);

  @Test
  public void TestOrderingBookChecksStock(){
    context.checking(
            new Expectations(){
              {
                exactly(1).of(warehouse).checkStockLevel(LEGACY_CODE_BOOK);
                ignoring(buyer);
              }
            });
    orderProcessor.order(LEGACY_CODE_BOOK, 1, BOB);
  }

  @Test
  public void TestBuyMoreBooksIfLowStockLevel(){
    context.checking(
            new Expectations(){
              {
                exactly(1).of(warehouse).checkStockLevel(LEGACY_CODE_BOOK);
                will(returnValue(0));
                exactly(1).of(buyer).buyMoreOf(LEGACY_CODE_BOOK);
                exactly(1).of(warehouse).checkStockLevel(LEGACY_CODE_BOOK);
                will(returnValue(5));
                exactly(1).of(paymentSys).charge(LEGACY_CODE_BOOK.price()*1, BOB);
                exactly(1).of(warehouse).dispatch(LEGACY_CODE_BOOK, 1, BOB);
              }
            });
    orderProcessor.order(LEGACY_CODE_BOOK, 1, BOB);
    orderProcessor.newBooksArrived();
  }

    @Test
    public void TestLowRestockDoesNothing(){
        context.checking(
                new Expectations(){
                    {
                        exactly(1).of(warehouse).checkStockLevel(LEGACY_CODE_BOOK);
                        will(returnValue(0));
                        exactly(1).of(buyer).buyMoreOf(LEGACY_CODE_BOOK);
                        exactly(1).of(warehouse).checkStockLevel(LEGACY_CODE_BOOK);
                        will(returnValue(0));
                    }
                });
        orderProcessor.order(LEGACY_CODE_BOOK, 1, BOB);
        orderProcessor.newBooksArrived();
    }

  @Test
  public void TestPaymentAndDispatchSystem(){
    context.checking(
            new Expectations(){
              {
                exactly(1).of(warehouse).checkStockLevel(DESIGN_PATTERNS_BOOK);
                will(returnValue(3));
                exactly(1).of(paymentSys).charge(DESIGN_PATTERNS_BOOK.price()*2, ALICE);
                exactly(1).of(warehouse).dispatch(DESIGN_PATTERNS_BOOK, 2, ALICE);
              }
            });
    orderProcessor.order(DESIGN_PATTERNS_BOOK, 2, ALICE);
  }
}
