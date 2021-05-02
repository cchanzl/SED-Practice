package ic.doc;

import com.worldpay.CardNumber;
import com.worldpay.CreditCardTransaction;
import com.worldpay.TransactionProcessor;

import java.util.HashMap;
import java.util.Map;

public class ShoppingBasket {

  private final Map<Item, Integer> items = new HashMap<>();
  private CardNumber cardNumber;

  public void addItem(Item item) {
    if (items.containsKey(item)) {
      items.put(item, items.get(item) + 1);
    } else {
      items.put(item, 1);
    }
  }

  public void enterCardDetails(String cardNumber) {
    this.cardNumber = new CardNumber(cardNumber);
  }

  public void checkout() {
    int totalPounds = 0;
    int totalItems = 0;
    for (Item item : items.keySet()) {
      Integer quantity = items.get(item);
      totalItems = totalItems + quantity;
      totalPounds = totalPounds + quantity * item.priceInPounds();
    }

    if (totalItems > 3) {
      totalPounds = Math.min(totalPounds, totalPounds - 5);
    }

    new TransactionProcessor().process(new CreditCardTransaction(cardNumber, totalPounds, 0));
  }
}