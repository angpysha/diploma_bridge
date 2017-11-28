import com.andrewpetrowski.diploma.bridgelib.HttpHelpers.URLParams;
import com.andrewpetrowski.diploma.bridgelib.Models.DHT11_Data;
import com.andrewpetrowski.diploma.bridgelib.RestApi;
import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class TestMain {
    @Test
    public void TestParams()
    {
        URLParams params = new URLParams();

        params.Add("fasd","afsdfad");
        params.Add("fasdd","eeee");

        String ss = params.toString();

        int i =0;
    }
}
