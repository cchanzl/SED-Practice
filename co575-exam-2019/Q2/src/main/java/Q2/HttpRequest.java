package Q2;

// Answer to 2A
// Builder Pattern

// Answer to 2C
//import HttpRequestBuilder.request;
//HttpRequest request = request().withURL("http://exams.imperial.ac.uk/575").withBody("mark=100").withHeader("Date=02-05-2019");

import java.util.List;

public class HttpRequest {

    private final String url;
    private final String body;
    private final List<String> params;
    private final Method method;
    private final List<String> headers;

    public HttpRequest(String url, Method method, List<String> params, List<String> headers, String body) {
        this.url = url;
        this.body = body;
        this.params = params;
        this.method = method;
        this.headers = headers;
    }

    // more code here - not relevant for exam question

}

