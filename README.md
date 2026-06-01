# container-limiter

limiter

[中文](./README_zh_CN.md)

Support function：

- [x] Limit Traffic
- [x] Limited Service

## Tips:

Before Current Limiting, Please Press And Measure The QPS For Accurate Current Limiting  
Accurate Flow Restriction Ensures That The Service Will Not Be Shut Down Or Restarted Due To Excessive QPS, And The
Service Cluster Is More Robust  
The Current Limiting Operation Should Be Divided Into Transactional Operation And Non Transactional Operation, And The
QPS Gap Between Them Is Usually Large  
Limit Service, For Service That May Cause Delay Or High Error Rate, To Ensure The Security Of Services

## Quick Start

```xml
<!--Adding dependencies to pom. XML-->
<dependency>
    <artifactId>container-limiter-loading</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>1.0.0.0-RELEASE</version>
</dependency>
```

# Start Using

```java

@ScannerPackage(packageName = "com.hello.world.web.limiter")
public class LimiterRegistrationImpl implements InterfaceManualRegistration {
    @Override
    public void scannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        LimiterRegistration.limiterRegistrationScannerAll(scannerClassList, registrationMap);
    }
}
```

# Limit Traffic

# Limited Service

 ```java

@Limit
public class LimitFallback {
    public String limit(String limit) {
        System.out.println("fallBack" + limit);
        return "LimitFallback";
    }

    public String limitedService(String limitedService) {
        System.out.println("limitedService" + limitedService);
        return "limitedServiceFallback";
    }

    @LimitTraffic(limitName = "limit", permitsPerSecond = 1, fallbackClass = LimitFallback.class, fallbackMethod = "limit")
    public String hello(String limit) {
        System.out.println("helloWorld" + limit);
        return "Hello";
    }

    @LimitedService(fallbackClass = LimitFallback.class, fallbackMethod = "limitedService")
    public String world(String limitedService) {
        System.out.println("World::" + limitedService);
        return "limitedServiceFallback";
    }
}
 ```

# Web

  ```java

@Http("/web")
public class HttpDemo {
    @Set
    private WebLoading webLoading;

    @Set
    private LimitFallback limitFallback;

    @Get("/limit")
    public String helloWorld() {
        String abc = limitFallback.hello("abc");
        System.out.println(abc);
        return "OK";
    }

    @Get("/world")
    public String world() {
        String world = limitFallback.world("world");
        System.out.println(world);
        return "world";
    }
}
  ```
  

  
