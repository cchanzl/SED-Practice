package Q2;

import java.util.List;

public class HttpRequestBuilder {
    private String url;
    private Method method;
    private java.util.List<String> params;
    private List<String> headers;
    private String body;

    public HttpRequest build(){
        return new HttpRequest(url, method, params, headers, body);
    }

    public static HttpRequestBuilder request(){
        return new HttpRequestBuilder();
    }

    public HttpRequestBuilder withURL(String url){
        this.url = url;
        return this;
    }

    public HttpRequestBuilder withMethod(Method method){
        this.method = method;
        return this;
    }

    public HttpRequestBuilder withParams(List<String> params){
        this.params = params;
        return this;
    }

    public HttpRequestBuilder withHeaders(List<String> headers){
        this.headers = headers;
        return this;
    }

    public HttpRequestBuilder withBody(String body){
        this.body = body;
        return this;
    }
}
