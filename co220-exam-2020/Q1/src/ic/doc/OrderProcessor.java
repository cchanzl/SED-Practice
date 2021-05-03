package ic.doc;
// Answer to ibi
//
//
// Answer to 1bii
// Command = order(Book book, Integer i, Customer customer)
// Query = checkStockLevel(Book book)
//
// Answer to 1biii
// It leads to more flexible and portable designs as an object does not need to be concerned with what the
// other object is doing internally. Hence, it is easier to change requirements of objects independently
// without affecting other classes. It also provides better encapsulation since the internal state
// of objects are not communicated to other objects.
//
// Answer to ibiv
// Tell, don't ask

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

public class OrderProcessor {

    private final Warehouse warehouse;
    private final Buyer buyer;
    private final PaymentSystem paymentSys;
    private Map<Book, Map<Customer, Integer>> bookWaitingList = new HashMap<>();

    OrderProcessor(Warehouse warehouse, Buyer buyer, PaymentSystem paymentSys){
        this.warehouse = warehouse;
        this.buyer = buyer;
        this.paymentSys = paymentSys;
    }

    private void addToWaitingList(Book book, int i, Customer customer){
        // add book if empty
        if (bookWaitingList.get(book) == null ){
            Map<Customer, Integer> order = new HashMap<>();
            order.put(customer, i);
            bookWaitingList.put(book, order);
            return;
        }

        Map<Customer, Integer> order = bookWaitingList.get(book);
        if (order.get(customer) == null ) return; // return if customer is already on wait list
        order.put(customer, i);
    }

    public void order(Book book, int i, Customer customer){
        int bookCount = warehouse.checkStockLevel(book);
        if ( bookCount == 0 ) {
            buyer.buyMoreOf(book);
            addToWaitingList(book, i, customer);
            return;
        }
        if (bookCount >= i ){
            paymentSys.charge(book.price()*i, customer);
            warehouse.dispatch(book, i, customer);
        }
    }

    private void order(int i, Book book, Customer customer){
        int bookCount = warehouse.checkStockLevel(book);
        if ( bookCount == 0 ) return;
        if (bookCount >= i ){
            paymentSys.charge(book.price()*i, customer);
            warehouse.dispatch(book, i, customer);
        }
    }

    public void newBooksArrived(){
        for(Book book : bookWaitingList.keySet()){
            Map<Customer, Integer> order = bookWaitingList.get(book);
            // iterate through each order in waiting list for this book
            for (Entry<Customer, Integer> ord : order.entrySet()) {
                order(ord.getValue(), book, ord.getKey());
            }
        }
    }
}
