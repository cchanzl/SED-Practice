package Q1;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class OnlineListTest {
    final OnlineList onlineHtmlList = new HtmlOnlineList();
    final OnlineList onlineLaTeXList = new LaTeXOnlineList();

    @Test
    public void checkFormatHtmlHeader(){
        assertThat(onlineHtmlList.formatHeader(), containsString("<ul>"));
    }

    @Test
    public void checkFormatLaTeXHeader(){
    assertThat(onlineLaTeXList.formatHeader(), containsString("\\begin{itemize}"));
    }
}

