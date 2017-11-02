# raining
项目是基于thrift 开发的rpc 框架。在服务端只需要在提供服务的实现类上使用@RainServer 即可对外提供服务。客户端在对第三方接口调用的使用只需要在接口上注入@RainClient 即可实现接口动态调用。

The project is a RPC framework developed based on thrift. The server only needs to provide the service for the foreign @RainServer can use to provide services in the implementation class. When the client calls the third party interface, it only needs to inject @RainClient into the interface to realize the dynamic call of the interface.