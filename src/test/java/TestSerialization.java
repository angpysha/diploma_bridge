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

import com.andrewpetrowski.diploma.bridgelib.Helpers.EntitySerializer;
import com.andrewpetrowski.diploma.bridgelib.Models.DHT11_Data;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestSerialization {
    @Test
    public void TestFromObjectToJson() {
        DHT11_Data data = new DHT11_Data(25,18);
        EntitySerializer<DHT11_Data> serializer = new EntitySerializer<>();

        serializer.EntityToJsonFile(data,"E:\\test.json");
        File f = new File("E:\\test.json");

        Assert.assertEquals(f.exists(),true);
    }

    @Test
    public void TestFromJsonToObejct() {
        EntitySerializer<DHT11_Data> serializer = new EntitySerializer<>();

        DHT11_Data data = serializer.JsonFileToEntity("E:\\test.json",DHT11_Data.class);
        Assert.assertEquals(data.getTemperature(),25f,1e-3);
    }

    @Test
    public void TestListToJson() {
        EntitySerializer<DHT11_Data> serializer = new EntitySerializer<>();

        List<DHT11_Data> data = new LinkedList<>();

        data.add(new DHT11_Data(25,34));
        data.add(new DHT11_Data(24,32));
        data.add(new DHT11_Data(23,24));

        Boolean result = serializer.ListToJsonFile(data,"E:\\testList.json");

        Assert.assertEquals(result,true);
    }

    @Test
    public void TestJsonToList() {
        EntitySerializer<DHT11_Data> serializer = new EntitySerializer<>();

        List<DHT11_Data> data = serializer.FromJsonFileToList("E:\\testList.json",DHT11_Data[].class);

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
