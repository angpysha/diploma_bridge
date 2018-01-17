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

import com.andrewpetrowski.diploma.bridgelib.Controllers.BmpController;
import com.andrewpetrowski.diploma.bridgelib.Models.Bmp180_Data;
import com.andrewpetrowski.diploma.bridgelib.Models.BmpSearch;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestBmp {

    @Test
    public void TestAdd() {
        try {
            Bmp180_Data data = new Bmp180_Data(27,179,102235);
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

            cl.setPressure(999999);

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
            search.setBeginPressure(200000);
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

            Assert.assertEquals(data.getTemperature(),27,1e-3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
