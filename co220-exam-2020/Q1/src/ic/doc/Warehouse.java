package ic.doc;

public interface Warehouse {
    public int checkStockLevel(Book book);
    public void dispatch(Book book, int i, Customer customer);
}
