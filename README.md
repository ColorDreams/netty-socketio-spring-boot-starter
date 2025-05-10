### 介绍

> 基于 [netty-socketio 2.X](https://github.com/mrniko/netty-socketio) 的 SpringBoot 快速集成自动化配置包。

### 项目部分实现参考自
- [socketio-spring-boot-starter 基于 netty-socketio 2.x 开源项目实现的Socket项目整合](https://github.com/hiwepy/socketio-spring-boot-starter)
- [redisson-spring-boot-starter Redisson官方SpringBoot starter包](https://github.com/redisson/redisson/tree/master/redisson-spring-boot-starter)
- [netty-socketio](https://github.com/mrniko/netty-socketio)

### 使用

#### 1、添加依赖
```xml
<!-- https://central.sonatype.com/artifact/io.github.deersunny/netty-socketio-spring-boot-starter -->
<dependency>
    <groupId>io.github.deersunny</groupId>
    <artifactId>netty-socketio-spring-boot-starter</artifactId>
    <version>2.0.0</version>
</dependency>
```

#### 2、在 SpringBoot 的配置文件中加入以下配置（参考自：[socketio-spring-boot-starter](https://github.com/hiwepy/socketio-spring-boot-starter/blob/master/README.md) ）

```yml
socketio:
  server:
    enabled: true
    ## 服务上下文地址，该地址与Nginx负载地址适配 /socket.io
    context: /socket.io
    ## host在本地测试可以设置为localhost或者本机IP，在Linux服务器跑可换成服务器IP
    hostname: 127.0.0.1
    ## netty启动端口
    port: 10065
    ## 添加头部版本信息
    add-version-header: true
    ## Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
    ping-interval: 25000
    ## Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
    ping-timeout: 60000
    ## 设置最大每帧处理数据的长度，防止他人利用大数据来攻击服务器
    max-frame-payload-length: 1048576
    ## 设置http交互最大内容长度
    max-http-content-length: 1048576
    transports:
      - polling
      - websocket
    use-linux-native-epoll: false
    ## 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间
    upgrade-timeout: 20000
    ## socket配置
    socket-config:
      reuse-address: true
      tcp-no-delay: true
      so-linger: 0
    ack-mode: auto
    allow-custom-requests: true
    ## sessionID 通过请求头io来获取
    random-session: false
    ## socket连接数大小（如只监听一个端口boss线程组为1即可）
    boss-threads: 1
    worker-threads: 100
```

#### 3、启动项目，使用 Socket.io 客户端访问 `10065` 端口

前端实现可以参考[socketio-spring-boot-starter](https://github.com/hiwepy/socketio-spring-boot-starter/blob/master/README.md) 中的案例