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
