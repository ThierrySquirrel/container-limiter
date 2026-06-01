# container-limiter

限流

[English](./README.md)

支持功能：

- [x] 限流
- [x] 限制服务

## 提示:

限流之前,请先压测好服务QPS,进行准确限流.  
准确限流,保障服务不会因为过高QPS导致服务关闭或重启,服务集群时更为健壮.  
限流操作,应该分为事务性操作,和非事务性操作,分开限流,通常两者QPS差距较大.  
限制服务,用于可能产生延迟或高出错率的服务,保障服务的安全性

## Quick Start

```xml
<!--在pom.xml中添加依赖-->
<dependency>
    <artifactId>container-limiter-loading</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>1.0.0.0-RELEASE</version>
</dependency>
```

# 开始使用

```java

@ScannerPackage(packageName = "com.hello.world.web.limiter")
public class LimiterRegistrationImpl implements InterfaceManualRegistration {
    @Override
    public void scannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        LimiterRegistration.limiterRegistrationScannerAll(scannerClassList, registrationMap);
    }
}
```

# 限流

# 限制服务

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
  

  
