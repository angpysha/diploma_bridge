/*
 *    Copyright 2017 Andrew Petrowsky
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import io.github.angpysha.diploma_bridge.Controllers.DhtController;
import io.github.angpysha.diploma_bridge.Decorators.DateEx;
import io.github.angpysha.diploma_bridge.HttpHelpers.URLParams;
import io.github.angpysha.diploma_bridge.Models.DHT11_Data;
import io.github.angpysha.diploma_bridge.Models.DhtSearch;
import io.github.angpysha.diploma_bridge.RestApiEx;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestMain {
    @Test
    public void checkPost() {
        try {
            int[] dataf = {1, 1, 1, 8, 5};
            Headers headers = new Headers();
            RestApiEx<DHT11_Data,DhtSearch> data = new RestApiEx<>();
            data.EnableMapper();
            DHT11_Data datad = new DHT11_Data(dataf);
            HttpResponse<JsonNode> res = data.SendPost(datad);

            int i = 0;
        } catch (UnirestException ex)
        {
            String mes = ex.getMessage();
            int i =0;
        }



    }

    @Test
    public void TestParams()
    {
        URLParams params = new URLParams();

        params.Add("fasd","afsdfad");
        params.Add("fasdd","eeee");

        String ss = params.toString();

        int i =0;
    }
    @Test
    public void TestSearch() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            DhtSearch search = new DhtSearch();
//            search.Date = Date.from(LocalDate.of(2017,12,1).atStartOfDay(ZoneId.systemDefault())
//                    .toInstant());
////            search.endDate = Date.from(LocalDate.of(2017,12,4).atStartOfDay(ZoneId.systemDefault())
//                    .toInstant());
            //search.beginTemperature = 20f;

            RestApiEx<DHT11_Data,DhtSearch> searchAp = new RestApiEx<>();
           ObjectMapper mapper = new ObjectMapper();

      //      String json = mapper.writeValueAsString(search);
            HttpResponse<JsonNode> dataa = searchAp.SendPost("http://diplomaapi:8080/dhts/search", search);
            mapper.setDateFormat(df);

           // String body = ff.getBody().toString();
           // List<DHT11_Data> dd = mapper.readValue(body, new TypeReference<List<DHT11_Data>>() {});
//            JsonNode object = dataa.getBody();
//
//            JSONArray s_object = dataa.getBody().getArray();
            String ss = dataa.getBody()
                    .getArray()
                    .toString();
            List<DHT11_Data> dd = mapper.readValue(ss, mapper.getTypeFactory().constructCollectionType(List.class, DHT11_Data.class));

            int i =0;

        } catch (Exception ex)
        {
            int i =0;
        }
    }

    @Test
    public void TestGet() {
        try
        {
            DhtController controller = new DhtController();
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            RestApi<DHT11_Data> restApi = new RestApi<>();
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.setDateFormat(df);
//            HttpResponse<JsonNode> response = restApi.SendPost("http://diplomaapi:8080/dhts/get/26",null);
//            String tmpStr = response.getBody()
//                    .getObject()
//                    .toString();
//
//
//            DHT11_Data data = mapper.readValue(tmpStr,DHT11_Data.class);
//            System.out.print(data.getHumidity());
            DHT11_Data data = controller.Get(26,DHT11_Data.class);
            int i =0;
          //  return mapper.readValue(tmpStr,  DHT11_Data);
        } catch (Exception ex)
        {
            int i=0;
        }
    }

    @Test
    public void TestAddDHT() {
        try {
            DhtController controller = new DhtController();

            DHT11_Data data = new DHT11_Data(27,64);

            boolean res = controller.AddAsync(data).get();

            DHT11_Data last = controller.GetLast(DHT11_Data.class);
            Assert.assertEquals(res,true);
            Assert.assertEquals(last.getTemperature(),27,1e-5);

            int i =0;
        } catch (Exception ex )
        {
            int i =0;
        }
    }

    @Test
    public void TestUpdateDHT() {
        try{
            DhtController controller = new DhtController();
            DHT11_Data data = new DHT11_Data(27,54);
            boolean res = controller.Update(28,data);
            int i=0;
        } catch (Exception ex) {
            ex.printStackTrace();
            int i=0;
        }
    }

    @Test
    public void TestDeleteDHT() {
        try {
            DhtController controller = new DhtController();
        //    DhtSearch search = new DhtSearch();
            controller.Remove(27);
            int i =0;

        } catch (Exception ex) {
            ex.printStackTrace();
            int i=0;
        }
    }

    @Test
    public void TestGetLast() {
        try {
            DHT11_Data data = new DhtController().GetLast(DHT11_Data.class);
            Assert.assertTrue(data.getTemperature() > 10);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void TestSearch2() {
        try {
//            Date date =  Date.from(LocalDate.of(2017, 12, 29).atStartOfDay(ZoneId.systemDefault())
//                    .toInstant());
            Date date = new Date();

            date = new DateEx(date).ZeroTime();
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date);
            calendar.add(Calendar.DATE,-2);
            Date before = calendar.getTime();

            calendar.setTime(date);
            calendar.set(Calendar.DATE,1);
            Date after = calendar.getTime();
            DhtSearch search = new DhtSearch(before,after,null,null,null,null);

            List<DHT11_Data> result = new DhtController().SearchAsync(search,DHT11_Data.class).get();
            int i=0;
            Assert.assertEquals(result.get(0).getTemperature(),25,1e-3);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    @Test
    public void TestLocalDateTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        String month = DateEx.ToFormatedLocalDateEx(cal.getTime(),new Locale("uk"));

        System.out.println(month);

    }



}
