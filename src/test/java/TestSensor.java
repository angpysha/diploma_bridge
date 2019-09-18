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

import io.github.angpysha.diploma_bridge.Controllers.SensorController;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class TestSensor {

    @Test
    public void TestGetAll() {
        String data = SensorController.GetAll();
        Assert.assertNotNull(data);
    }

    @Test
    public void TestAdd() {
        String data = SensorController.Add("{\n" +
                "\t\"type\": \"dht11\",\n" +
                "\t\"temperature\": \"25\",\n" +
                "\t\"humidity\": \"28\"\n" +
                "}");
        Assert.assertNotNull(data);
    }

    @Test
    public void TestUpdate() {
        String data = SensorController.Update("{\n" +
                "\t\"type\": \"dht11\",\n" +
                "\t\"temperature\": \"35\",\n" +
                "\t\"humidity\": \"45\"\n" +
                "}","5d82825f137c9b514c006866");
        Assert.assertNotNull(data);

    }

    @Test
    public void TestDelete() {
        String data = SensorController.Delete("5d82825f137c9b514c006866");
        Assert.assertNotNull(data);
    }

    @Test
    public void TestSearch() {
        String data = SensorController.Search("{\n" +
                "\t\"datefrom\": \"2019-09-18 20:00:00\",\n" +
                "\t\"dateto\": \"2019-09-18 20:47:13\"\n" +
                "}");
        Assert.assertNotNull(data);
    }
}
