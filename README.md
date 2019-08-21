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
> 将zookeeper复制成为2份 zk1和zk2
> 在主目录下建立tmp/data 和tmp/log目录，并在temp/data目录下建立myid文件，写入不同的数字，例如1，2
> 配置conf/zoo.cfg文件

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

> 启动zk
    * ./bin/zkServer.cmd
> 注意：zk的部署个数最好为奇数，ZK集群的机制是只要超过半数的节点OK，集群就能正常提供服务。只有ZK节点挂得太多，只剩一半或不到一半节点能工作，集群才失效。

> 查看zk启动状态
    * ./bin/zkServer.cmd status

> 启动Emp-service 和Emp-web，curl http://localhost:9002/emp/index 可以正常访问，此时当关闭任何一个zk，或者全部关闭zk，都是可以访问的，因为服务已经被发现，两个应用是直连的方式。
> dubbo配置
```xml
<dubbo:registry address="zookeeper://127.0.0.1:2181?backup=127.0.0.1:2182,127.0.0.1:2183"/>
```
