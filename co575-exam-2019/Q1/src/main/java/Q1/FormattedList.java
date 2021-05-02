package Q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormattedList {

    private final OnlineList list;
    private List<String> content = new ArrayList<>();

    public FormattedList(OnlineList list, String... items) {
        content.addAll(Arrays.asList(items));
        this.list = list;
    }

    public void add(String item) {
        content.add(item);
    }

    public void print() {
        System.out.println(list.formatHeader());
        for (String item : content) {
            System.out.println(list.formatItem(item));
        }
        System.out.println(list.formatFooter());
    }

}

