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
import io.github.angpysha.diploma_bridge.Decorators.DateEx;
import io.github.angpysha.diploma_bridge.Models.DHT11_Data;
import io.github.angpysha.diploma_bridge.Models.DhtSearch;
import io.github.angpysha.diploma_bridge.Models.DisplayPeriod;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestDht {

    @Test
    public void TestGetDatesCount() {
        DhtController dht = new DhtController();
        int gg = dht.GetDatesCount();

        System.out.println(gg);
    //    Assert.assertEquals(gg,12);
    }

    @Test
    public void TestByDay() {
        DhtController controller = new DhtController();

        List<DHT11_Data> data = controller.GetByPeriod(new DateEx(new Date()).ZeroTime(), DisplayPeriod.DAY,
                DHT11_Data.class, DhtSearch.class);

        Assert.assertTrue(data.get(0).getTemperature()>0);
        Assert.assertTrue(data.get(0).getHumidity()>0);
    }

    @Test
    public void TestByWeek() {
        DhtController controller = new DhtController();

        List<DHT11_Data> data = controller.GetByPeriod(new DateEx(new Date()).ZeroTime(), DisplayPeriod.WEEK,
                DHT11_Data.class, DhtSearch.class);

        Assert.assertTrue(data.get(0).getTemperature()>0);
        Assert.assertTrue(data.get(0).getHumidity()>0);
    }

    @Test
    public void TestByMonth() {
        DhtController controller = new DhtController();

        List<DHT11_Data> data = controller.GetByPeriod(new DateEx(new Date()).ZeroTime(), DisplayPeriod.MONTH,
                DHT11_Data.class, DhtSearch.class);

        Assert.assertTrue(data.get(0).getTemperature()>0);
        Assert.assertTrue(data.get(0).getHumidity()>0);
    }

    @Test
    public void TestByYear() {
        DhtController controller = new DhtController();

        List<DHT11_Data> data = controller.GetByPeriod(new DateEx(new Date()).ZeroTime(), DisplayPeriod.YEAR,
                DHT11_Data.class, DhtSearch.class);

        Assert.assertTrue(data.get(0).getTemperature()>0);
        Assert.assertTrue(data.get(0).getHumidity()>0);
    }
}
