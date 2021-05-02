package Q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CodeFormatter {

  public String format(String source) {

    String trimmed = stripBlankLines(source);
    int indentLevel = 0;

    List<String> indentedCode = new ArrayList<>();

    for(String line : linesOf(trimmed)) {
      if (line.contains(endOfBlock())) {
        indentLevel -= 1;
      }
      indentedCode.add(indentBy(indentLevel, tabsOrSpaces(), line));
      for (String openBlock : startOfBlock()) {
        if (line.contains(openBlock)) {
          System.out.println("line contains " + openBlock);
          indentLevel += 1;
        }
      }

    }

    return String.join("\n", indentedCode);
  }

  protected abstract List<String> startOfBlock();

  protected abstract String endOfBlock();

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

  protected abstract WhiteSpace tabsOrSpaces();

}
