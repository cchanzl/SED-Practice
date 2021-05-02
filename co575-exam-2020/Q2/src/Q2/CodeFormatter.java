package Q2;
// Answer to 2D
// The strategy method is preferred as it has a looser coupling compared to the template method.
// Under the Template method, we cannot remove CodeFormatter and apply it on other types of formatter, without also
// bringing along either Java or Ruby code formatter which implements the abstract methods.
// However, with strategy method, the code base for CodeFormatter can be reused independently without
// necessarily understanding what the sub classes are doing.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeFormatter {

  private final Formatter formatter;

  public CodeFormatter(Formatter formatter){
    this.formatter = formatter;
  }

  public String format(String source) {

    String trimmed = stripBlankLines(source);
    int indentLevel = 0;

    List<String> indentedCode = new ArrayList<>();

    for(String line : linesOf(trimmed)) {
      if (line.contains(formatter.endOfBlock())) {
        indentLevel -= 1;
      }
      indentedCode.add(indentBy(indentLevel, formatter.tabsOrSpaces(), line));
      for (String openBlock : formatter.startOfBlock()) {
        if (line.contains(openBlock)) {
          System.out.println("line contains " + openBlock);
          indentLevel += 1;
        }
      }
    }

    return String.join("\n", indentedCode);
  }

  private String indentBy(int num, WhiteSpace whiteSpace, String line) {
    String indent = "";
    for(int i = 0; i < num; i++) {
      indent = indent + whiteSpace.literal;
    }
    return indent + line.trim();
  }

  private List<String> linesOf(String source) {
    return Arrays.asList(source.split("\n"));
  }

  private String stripBlankLines(String source) {
    return source.trim();
  }

}
