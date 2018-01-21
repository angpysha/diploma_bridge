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

import io.github.angpysha.diploma_bridge.Helpers.EntitySerializer;
import io.github.angpysha.diploma_bridge.Models.DHT11_Data;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestSerialization {
    @Test
    public void TestFromObjectToJson() throws ExecutionException, InterruptedException {
        DHT11_Data data = new DHT11_Data(25,18);
        EntitySerializer<DHT11_Data> serializer = new EntitySerializer<>();

        serializer.EntityToJsonFileAsync(data,"E:\\test.json").get();
        File f = new File("E:\\test.json");

        Assert.assertEquals(f.exists(),true);
    }

    @Test
    public void TestFromJsonToObejct() {
        EntitySerializer<DHT11_Data> serializer = new EntitySerializer<>();

        DHT11_Data data = null;
        try {
            data = serializer.JsonFileToEntityAsync("E:\\test.json",DHT11_Data.class).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(data.getTemperature(),25f,1e-3);
    }

    @Test
    public void TestListToJson() {
        EntitySerializer<DHT11_Data> serializer = new EntitySerializer<>();

        List<DHT11_Data> data = new LinkedList<>();

        data.add(new DHT11_Data(25,34));
        data.add(new DHT11_Data(24,32));
        data.add(new DHT11_Data(23,24));

        Boolean result = null;
        try {
            result = serializer.ListToJsonFileAsync(data,"E:\\testList.json").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(result,true);
    }

    @Test
    public void TestJsonToList() throws ExecutionException, InterruptedException {
        EntitySerializer<DHT11_Data> serializer = new EntitySerializer<>();

        List<DHT11_Data> data = serializer.FromJsonFileToListAsync("E:\\testList.json",DHT11_Data[].class).get();

        Assert.assertEquals(data.get(0).getTemperature(),25f,1e-5);
    }

    @Test
    public void TestJsonToList2() {
        EntitySerializer<DHT11_Data> serializer = new EntitySerializer<>();

        List<DHT11_Data> data = null;
        try {
            data = serializer.FromJsonFileToListAsync("E:\\testList.json",(new TypeToken<List<DHT11_Data>>(){}).getType()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(data.get(0).getTemperature(),25f,1e-5);
    }

}
