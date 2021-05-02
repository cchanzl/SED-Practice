package ic.doc.monitoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebPageProbe implements Probe {

    private final String url;
    private final String stringToCheckFor;

    public WebPageProbe(String url, String stringToCheckFor) {
        this.url = url;
        this.stringToCheckFor = stringToCheckFor;
    }

    @Override
    public boolean passes() {
        BufferedReader in = null;
        try {
            URL u = new URL(url);
            URLConnection connection = u.openConnection();
            in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains(stringToCheckFor)) {
                    return true;
                }
            }


        } catch (IOException e) {
            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // give up
            }
        }
        return false;
    }

    @Override
    public String getFailureDescription() {
        return "Page at " + url + " did not contain " + stringToCheckFor;
    }
}
