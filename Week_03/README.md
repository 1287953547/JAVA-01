学习笔记

1.针对NiO针对传统多线程BIO的修改：传统的方式是在客户连接上后，便分发线程池的线程，但是这时客户的信息并没有传递过来，所以占用cpu的线程还会处于等待状态，极其浪费资源。所以NIO针对这个问题，有一个专门的线程，注册连接，等连接的IO准备好了，再通知相应连接，给予线程运行，减少了cpu的资源浪费。

