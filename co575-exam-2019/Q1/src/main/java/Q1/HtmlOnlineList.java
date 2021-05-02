package Q1;

public class HtmlOnlineList implements OnlineList {

    @Override
    public String formatHeader() {
        return "<ul>";
    }

    @Override
    public String formatItem(String item) {
        return "  <li>" + item + "</li>";
    }

    @Override
    public String formatFooter() {
        return "</ul>";
    }
}
