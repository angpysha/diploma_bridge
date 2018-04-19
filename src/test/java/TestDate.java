/*
 *    Copyright 2018 Andrew Petrowsky
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
import io.github.angpysha.diploma_bridge.Models.DHT11_Data;
import io.github.angpysha.diploma_bridge.Models.DhtSearch;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestDate {
    @Test
    public void TestUnique() {
        try {
            String date = "2018-03-26 18:56:14";

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date datew = simpleDateFormat.parse(date);

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(datew);

            calendar.add(Calendar.SECOND,-1);

            Date date1 = calendar.getTime();

            Calendar calendar1 = Calendar.getInstance();

            calendar1.setTime(datew);

            calendar.add(Calendar.SECOND,1);
            Date date2 = calendar1.getTime();
            DhtSearch search = new DhtSearch();
            DhtController controller = new DhtController();

            search.setBeginDate(date1);
            search.setEndDate(date2);
            controller.setBaseUrl("http://rasp.kl.com.ua/web/api/v1");
            List<DHT11_Data> data = controller.Search(search,DHT11_Data.class);
            int i =0;
        } catch (ParseException ex) {
            int i =0;
        }
    }

    @Test
    public void TestUnique2() {
        try {
            DHT11_Data data = new DHT11_Data();
            String date = "2018-03-26 18:56:14";

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date datew = simpleDateFormat.parse(date);
            data.setCreated_at(datew);

            DhtController controller = new DhtController();
            controller.setBaseUrl("http://rasp.kl.com.ua/web/api/v1");

            int count = controller.GetCount(data,DHT11_Data.class,DhtSearch.class);
            int i =0;
        } catch (Exception ex) {
            int i=0;
        }
    }
}
