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

import io.github.angpysha.diploma_bridge.Controllers.BmpController;
import io.github.angpysha.diploma_bridge.Decorators.DateEx;
import io.github.angpysha.diploma_bridge.Models.Bmp180_Data;
import io.github.angpysha.diploma_bridge.Models.BmpSearch;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestBmp {

    @Test
    public void TestAdd() {
        try {
            Bmp180_Data data = new Bmp180_Data(27f,179f,102235f);
            BmpController controller = new BmpController();
            boolean result = controller.AddAsync(data).get();

            Assert.assertEquals(result,true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Test
    public void testUpdate() {
        try {
            BmpController controller = new BmpController();

            Bmp180_Data cl = controller.Get(2,Bmp180_Data.class);

            cl.setPressure(999999f);

            boolean res = controller.Update(2,cl);

            Assert.assertEquals(res,true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void TestSearch() {
        try {
            BmpController controller = new BmpController();
            BmpSearch search = new BmpSearch();
            search.setBeginPressure(200000f);
            List<Bmp180_Data> data = controller.SearchAsync(search,Bmp180_Data.class).get();

            Assert.assertEquals(data.get(0).getPressure(),999999,1e-3);
        } catch (Exception ex) {

        }
    }

    @Test
    public void TestLast() {
        try {
            BmpController controller = new BmpController();
            Bmp180_Data data = controller.GetLast(Bmp180_Data.class);

//            Assert.assertEquals(data.getTemperature(),27,1e-3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void TestByDate() throws ExecutionException, InterruptedException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new DateEx(new Date()).ZeroTime());

        calendar.add(Calendar.DATE,1);

        Date after = calendar.getTime();

        BmpSearch filetr = new BmpSearch(new DateEx(new Date()).ZeroTime(),after,
                null,null,
                null,null,
                null,null);

        BmpController controller = new BmpController();

        List<Bmp180_Data> date = controller.SearchAsync(filetr,Bmp180_Data.class).get();

        System.out.println(date.get(0).getPressure());

    }
}
