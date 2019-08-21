# Emp-Dubbo

## 启动zookeeper提示8080端口被占用
```log
org.apache.zookeeper.server.admin.AdminServer$AdminServerException: Problem starting AdminServer on address 0.0.0.0, port 8080 and command URL /commands
        at org.apache.zookeeper.server.admin.JettyAdminServer.start(JettyAdminServer.java:107)
        at org.apache.zookeeper.server.ZooKeeperServerMain.runFromConfig(ZooKeeperServerMain.java:138)
        at org.apache.zookeeper.server.ZooKeeperServerMain.initializeAndRun(ZooKeeperServerMain.java:106)
        at org.apache.zookeeper.server.ZooKeeperServerMain.main(ZooKeeperServerMain.java:64)
        at org.apache.zookeeper.server.quorum.QuorumPeerMain.initializeAndRun(QuorumPeerMain.java:128)
        at org.apache.zookeeper.server.quorum.QuorumPeerMain.main(QuorumPeerMain.java:82)
Caused by: java.io.IOException: Failed to bind to /0.0.0.0:8080
        at org.eclipse.jetty.server.ServerConnector.openAcceptChannel(ServerConnector.java:346)
        at org.eclipse.jetty.server.ServerConnector.open(ServerConnector.java:308)
        at org.eclipse.jetty.server.AbstractNetworkConnector.doStart(AbstractNetworkConnector.java:80)
        at org.eclipse.jetty.server.ServerConnector.doStart(ServerConnector.java:236)
        at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
        at org.eclipse.jetty.server.Server.doStart(Server.java:396)
        at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
        at org.apache.zookeeper.server.admin.JettyAdminServer.start(JettyAdminServer.java:103)
        ... 5 more
Caused by: java.net.BindException: Address already in use: bind
        at sun.nio.ch.Net.bind0(Native Method)
        at sun.nio.ch.Net.bind(Net.java:433)
        at sun.nio.ch.Net.bind(Net.java:425)
        at sun.nio.ch.ServerSocketChannelImpl.bind(ServerSocketChannelImpl.java:223)
        at sun.nio.ch.ServerSocketAdaptor.bind(ServerSocketAdaptor.java:74)
        at org.eclipse.jetty.server.ServerConnector.openAcceptChannel(ServerConnector.java:342)
        ... 12 more
Unable to start AdminServer, exiting abnormally
```

* Zookeeper AdminServer，默认使用8080端口
* 修改zoo.cfg **admin.serverPort=8081**

## zookeeper zoo.cfg 常用配置
```cfg
dataDir=../tmp/data/
dataLogDir=../log/
admin.serverPort=8080
```


## zookeeper 集群配置
* [参考](https://www.cnblogs.com/likemebee/p/7891300.html)
* 将zookeeper复制成为2份 zk1和zk2
* 在主目录下建立tmp/data 和tmp/log目录，并在temp/data目录下建立myid文件，写入不同的数字，例如1，2
* 配置conf/zoo.cfg文件

```config
tickTime=2000
initLimit=10
syncLimit=5
# 使用绝对路径
dataDir=D:/Program Files/zk1/tmp/data
# 使用绝对路径
logDir=D:/Program Files/zk1/tmp/log
admin.serverPort=8080
clientPort=2181

server.1=127.0.0.1:2881:3881
server.2=127.0.0.1:2882:3882
```


```config
tickTime=2000
initLimit=10
syncLimit=5
# 使用绝对路径
dataDir=D:/Program Files/zk2/tmp/data
# 使用绝对路径
logDir=D:/Program Files/zk2/tmp/log
admin.serverPort=8081
clientPort=2182

server.1=127.0.0.1:2881:3881
server.2=127.0.0.1:2882:3882
```

* 启动zk
    * ./bin/zkServer.cmd
* 注意：zk的部署个数最好为奇数，ZK集群的机制是只要超过半数的节点OK，集群就能正常提供服务。只有ZK节点挂得太多，只剩一半或不到一半节点能工作，集群才失效。

* 查看zk启动状态
    * ./bin/zkServer.cmd status

* 启动Emp-service 和Emp-web，curl http://localhost:9002/emp/index 可以正常访问，此时当关闭任何一个zk，或者全部关闭zk，都是可以访问的，因为服务已经被发现，两个应用是直连的方式。
* dubbo配置
```xml
<dubbo:registry address="zookeeper://127.0.0.1:2181?backup=127.0.0.1:2182,127.0.0.1:2183"/>
```

## dubbo 服务提供者集群
> 通过在多台机器上部署提供者，并且每台机器上提供者的端口不同来实现负载均衡

* 方式1 (推荐)
    - 使用jvm参数实现 例如jvm堆设值： java -jar -Xmx3550m -Xms3550m -Xmn2g -Xss128k xxx.jar
    - 将**Emp-service-1.0-SNAPSHOT.jar** 和**lib** 包同时放到两个不同的目录，例如d1/和d2/
    - 运行命令 **java -jar -Ddubbo.protocol.name=dubbo -Ddubbo.protocol.port=20880 ./d1/Emp-service-1.0-SNAPSHOT.jar**
    - 运行命令 **java -jar -Ddubbo.protocol.name=dubbo -Ddubbo.protocol.port=20881 ./d2/Emp-service-1.0-SNAPSHOT.jar**
    - 22222被占用 可以通过添加参数 **-Ddubbo.application.qos.accept.foreign.ip=false -Ddubbo.application.qos.port=22223 -Ddubbo.application.qos.enable=true** 来解决
    - 完整命令
    > java -jar -Ddubbo.protocol.name=dubbo -Ddubbo.protocol.port=20883 -Ddubbo.application.qos.accept.foreign.ip=false -Ddubbo.application.qos.port=22223 -Ddubbo.application.qos.enable=true Emp-service-1.0-SNAPSHOT.jar
    
    > java -jar -Ddubbo.protocol.name=dubbo -Ddubbo.protocol.port=20884 -Ddubbo.application.qos.accept.foreign.ip=false -Ddubbo.application.qos.port=22224 -Ddubbo.application.qos.enable=true Emp-service-1.0-SNAPSHOT.jar
    
    - 测试：多个提供者一起运行，关闭任何一个对服务消费者都没有影响，除非。。。。。。。全部关闭后，服务消费者才会报错找不到服务提供者。

* 方式2
> 修改spring-service.xml中的port参数为其他的
```xml
    <dubbo:protocol name="dubbo" port="20881"/>
```
然后再打jar包运行，非常麻烦

## Dubbo Admin管理控制台
将dubbo-admin-2.5.3.war部署到配置jdk7的tomcat下，启动tomcat，访问localhost:7777/dubbo-admin-2.5.3 输入用户名和密码root即可进入

