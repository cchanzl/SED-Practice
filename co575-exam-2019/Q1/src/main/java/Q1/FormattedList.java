package Q1;

// Answer to 1A
// Template Method

// Answer to 1B
// Strategy Method

// Answer to 1D
// The strategy method is preferred as it has a looser coupling compared to the template method.
// Under the Template method, we cannot remove FormattedList and apply it on other list types, without also
// bringing along either Html or LaTeX list which implements the abstract methods.
// However, with strategy method, the code base for FormattedList can be reused independently without
// necessarily understanding what the sub classes are doing.

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

