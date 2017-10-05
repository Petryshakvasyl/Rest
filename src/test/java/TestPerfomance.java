import org.apache.tomcat.jni.Thread;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pevsat on 03.10.2017.
 */

public class TestPerfomance {

    @Test
    public void testperfomance(){
        AsyncRestTemplate template = new AsyncRestTemplate();
        String fooResourceUrl = "http://localhost:8080/repositories/Petryshakvasyl/simpleExample";
        long start = System.nanoTime();
        for (int i =0; i<20; i++){
            template.exchange(fooResourceUrl, HttpMethod.GET, null, String.class);
        }
        long end = System.nanoTime();

        System.out.println((end-start)/1_000_000_000.0);
    }
}
