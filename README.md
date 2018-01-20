# Diploma bridge library

[![License](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](https://opensource.org/licenses/Apache-2.0) 
[![Build Status](https://travis-ci.org/angpysha/diploma_bridge.svg?branch=master)](https://travis-ci.org/angpysha/diploma_bridge)
[![Stage](https://img.shields.io/badge/stage-beta-orange.svg)](https://github.com/angpysha/diploma_bridge)
[![Maven](https://maven-badges.herokuapp.com/maven-central/io.github.angpysha/diploma_bridge/badge.svg?style=green)](https://github.com/angpysha/diploma_bridge)
[![Tag](https://img.shields.io/github/tag/angpysha/diploma_bridge.svg)](https://github.com/angpysha/diploma_bridge/tags)

The library, which contains API and models for interaction Raspberry Pi app 
and Android app with server.

## Requirements

- JDK 8
- IntelliJ IDEA (or other IDE with gradle support)
- Git

## Getting started

### With Maven

```$xslt
<dependency>
    <groupId>io.github.angpysha</groupId>
    <artifactId>diploma_bridge</artifactId>
    <version>0.3.1</version>
</dependency>
```

### With Gradle

```$xslt
    compile 'io.github.angpysha:diploma_bridge:0.3'
```

## Getting started

1. Create data model, which extends from **Entity** class, model for entity searching, which extends from EntitySearch and provide data send from your Raspberry Pi to web server. 

    Data model: 
    
    ```$xslt
    public class DHT11 extends Entity {
        //declare your propeties here
    }
    ```
    Data search:
    
    ```$xslt
    public class DhtSearch extends SearchEntity {
        //declare your propeties here
    }
    ```

2. You must implement these apis on your web server: 

    ```$xslt
    search; get; add; update; delete; last; datecount;
    ``` 

3. Create controller class:

    Example: 
    
    ```$xslt
    public class DhtController extends BaseController<DHT11_Data,DhtSearch>{
    
        public DhtController() {
            SEARCH_URL = "/dhts/search";
            GET_URL =  "/dhts/get";
            ADD_URL = "/dhts/add";
            UPDATE_URL="/dhts/update";
            DELETE_URL="/dhts/delete";
            GET_LAST_URL  = "/dhts/last";
            GET_SIZE_URL = "/dhts/datecount";
        }
    }
    
    
    ```

Now you can use methods from controller to get info. 
## License

This project is licensing by Apache 2 license. See [LICENSE](https://github.com/angpysha/diploma_bridge/blob/master/LICENSE)
