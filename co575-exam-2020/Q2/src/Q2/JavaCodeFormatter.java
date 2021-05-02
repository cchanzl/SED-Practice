package Q2;

import java.util.List;

public class JavaCodeFormatter implements Formatter {

  @Override
  public List<String> startOfBlock() {
    return List.of("{");
  }

  @Override
  public String endOfBlock() {
    return "}";
  }

  @Override
  public WhiteSpace tabsOrSpaces() {
    return WhiteSpace.TWOSPACES;
  }

}
