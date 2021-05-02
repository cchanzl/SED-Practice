package Q3;

public class BoxOffice {
    private Theatre theatre;
    private Payments payment;
    private WaitingList waitList;

    public BoxOffice(Theatre theatre, Payments payment, WaitingList waitList){
        this.theatre = theatre;
        this.payment = payment;
        this.waitList = waitList;
    }

    public void bookTickets(Customer cust, Show show, int i){
        payment.pay(show.price()*i, cust);
    }

    public void bookTickets(Show show, int i, Customer cust){
        if(theatre.checkAvailable(show, i)){
            payment.pay(show.price()*i, cust);
            theatre.confirm(show, i);
        }
        else {waitList.add(cust, show, i);}
    }

    public void returnTickets(Show show, int i) {
        waitList.anyoneWaiting(show, i);
    }
}
