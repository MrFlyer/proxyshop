# 基于使用SpringBoot的GET请求转发（所谓的代理）

## 关键词

- slf4j.Logger

- SpringBoot

- HttpClients

- DockerFile

## 具体操作

这里采用HttpClients库作为编辑发送请求的库，建立了一个getSent类进行实现GET的请求的发送并且处理返还回来的XML数据进行二次处理，获取到所需要的字段然后返回到上层之后，返回给上级给API请求的地方数据实现数据交互

```java
@RestController
public class control {

    public int countNum = 0;
    @RequestMapping("/getinfo")
    public String getinfo() {
        getSent getSent = new getSent();
        countNum += 1;
        return getSent.getinfo(countNum);
    }
}

```

通过使用自加的方法进行计数的增加，来了解一共在何时出发了多少次请求，可以更好的为后期出现问题的解决

## 新引入

- 在getSent.java中引入了`import java.time.LocalDateTime; import java.time.format.DateTimeFormatter;`这两个SpringBoot自身引入的log4j改版实现log的监控

- 与log4j使用方法相同`private static Logger logger = LoggerFactory.getLogger(getSent.class);`然后在通过`logger.info/error/warning`的方式进行log的输出就可以更好的知道实在什么时间在哪个进程出现了什么问题打出来了这条log，更好的可以在后续的debug中改善解决现有问题

## 使用方法

1. 引用进来对应包

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
```

2. 声明一个logger对象

```java
private static Logger logger = LoggerFactory.getLogger(getSent.class);
```

3. 使用error,warning,info等方式进行log输出

```java
logger.info()
logger.error()
logger.warning()
```


