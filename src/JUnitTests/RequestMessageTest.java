package JUnitTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.junit.Test;

import Handlers.RequestHandler;

public class RequestMessageTest {

    @Test
    public void httpbinGetQueryParamTest() throws IOException, URISyntaxException {
        // Input = httpc get 'http://httpbin.org/get?course=networking&assignment=1'

        final String[] args = { "get", "'http://httpbin.org/get?course=networking&assignment=1'" };

        final String expected = "GET /get?course=networking&assignment=1 HTTP/1.0\r\n\r\n";

        RequestHandler.handleRequest(args);

        final String actual = RequestHandler.requestMessage;

        assertEquals(expected, actual);

    }

    @Test
    public void gaiaGetIndexTest() throws IOException, URISyntaxException {
        // Input = httpc get 'gaia.cs.umass.edu/kurose_ross/interactive/index.php'

        final String[] args = { "get", "'gaia.cs.umass.edu/kurose_ross/interactive/index.php'" };

        final String expected = "GET /kurose_ross/interactive/index.php HTTP/1.0\r\n\r\n";

        RequestHandler.handleRequest(args);

        final String actual = RequestHandler.requestMessage;

        assertEquals(expected, actual);
    }

    @Test
    public void httpbinVerboseGetQueryParamTest() throws IOException, URISyntaxException {
        // Input = httpc get -v 'http://httpbin.org/get?course=networking&assignment=1'

        final String[] args = { "get", "-v", "'http://httpbin.org/get?course=networking&assignment=1'" };

        final String expected = "GET /get?course=networking&assignment=1 HTTP/1.0\r\n\r\n";

        RequestHandler.handleRequest(args);

        final String actual = RequestHandler.requestMessage;

        assertEquals(expected, actual);
    }

    @Test
    public void postWithInlineData() throws IOException, URISyntaxException {
        // Input = httpc post -h Content-Type:application/json -d '{"Assignment": 1}'
        // http://httpbin.org/post

        final String input = "post -h Content-Type:application/json -d '{\"Assignment\":1}' http://httpbin.org/post";

        final String[] args = input.split(" ");

        final String expected = "POST /post HTTP/1.0\r\nContent-Type: application/json\r\n\r\n{\"Assignment\": 1}";

        RequestHandler.handleRequest(args);

        final String actual = RequestHandler.requestMessage;

        assertEquals(expected, actual);
    }

    @Test
    public void multipleHeadersGet() throws IOException, URISyntaxException {
        // Input = httpc get -h Content-Type:application/json -h Host:Hello -h
        // accept:ThisGift
        // http://httpbin.org/get

        final String input = "get -h Content-Type:application/json -h Host:Hello -h accept:ThisGift http://httpbin.org/get";

        final String[] args = input.split(" ");

        final String expected = "GET /get HTTP/1.0\r\nContent-Type: application/json\r\nHost: Hello\r\naccept: ThisGift\r\n\r\n";

        RequestHandler.handleRequest(args);

        final String actual = RequestHandler.requestMessage;

        assertEquals(expected, actual);
    }

    @Test
    public void postFileInlineDataWithVerboseAndHeader() throws IOException, URISyntaxException {
        // Input = httpc post -v
        // -h Content-Type:application/json -h Host:GimmeCookies -h accept:ThisGift
        // -f 'FilePathHere'
        // http://httpbin.org/get

        final String filePath = "";

        final String input = "get -v -h Content-Type:application/json -h Host:GimmeCookies -h accept:ThisGift -f "
                + filePath + " http://httpbin.org/get";

        final String[] args = input.split(" ");

        FileInputStream file = new FileInputStream(filePath);
        BufferedReader in = new BufferedReader(new InputStreamReader(file));

        int data = in.read();
        String entityBody = "";

        while (data != -1) {
            char c = (char) data;
            entityBody += c;
            data = in.read();
        }

        in.close();

        final String fileContent = entityBody;

        final String expected = "GET /get HTTP/1.0\r\nContent-Type: application/json\r\nHost: GimmeCookies\r\naccept: ThisGift\r\n\r\n"
                + fileContent;

        RequestHandler.handleRequest(args);

        final String actual = RequestHandler.requestMessage;

        assertEquals(expected, actual);
    }
}