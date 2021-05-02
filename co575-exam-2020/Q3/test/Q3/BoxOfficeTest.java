package Q3;

// Answer for 3Bi
// A command is when you tell another class to do something by passing a message. You don't expect any return values
// A query is when you are asking a question and you therefore expect a return value from the called method.

// A command is bookTickets()
// A query is checkAvailable()

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class BoxOfficeTest {

  static final Show LION_KING =
      new Show("The Lion King", 44.00);

  static final Show HAMILTON =
      new Show("Hamilton", 80.00);

  static final Customer SALLY = new Customer("Sally Davies");
  static final Customer TOM = new Customer("Thomas Williams");

  // write your tests here
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Theatre theatre = context.mock(Theatre.class);
  Payments payment = context.mock(Payments.class);
  WaitingList waitList = context.mock(WaitingList.class);
  BoxOffice boxOffice = new BoxOffice(theatre, payment, waitList);

  @Test
  public void testSuccessfulBookingProcess(){
    context.checking(
            new Expectations(){
              {
                exactly(1).of(theatre).checkAvailable(LION_KING, 4);
                will(returnValue(true));
                exactly(1).of(payment).pay(LION_KING.price()*4, SALLY);
                exactly(1).of(theatre).confirm(LION_KING, 4);
              }
            });
    boxOffice.bookTickets(LION_KING, 4, SALLY);
  }

  @Test
  public void testAddingToWaitingList(){
    context.checking(
            new Expectations(){
              {
                exactly(1).of(theatre).checkAvailable(HAMILTON, 2);
                will(returnValue(false));
                exactly(1).of(waitList).add(TOM, HAMILTON, 2);
              }
            });
    boxOffice.bookTickets(HAMILTON, 2, TOM);
  }

  @Test
  public void testReturnProcess(){
    context.checking(
            new Expectations(){
              {
                exactly(1).of(waitList).anyoneWaiting(HAMILTON, 4);
                exactly(1).of(payment).pay(HAMILTON.price()*2, TOM);
              }
            });

    boxOffice.returnTickets(HAMILTON, 4);
    boxOffice.bookTickets(TOM, HAMILTON, 2);
  }


}
