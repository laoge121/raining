namespace java com.laoge.raining.common.route
namespace go rainrpc

struct RainRequestParam {
   1:string classURI;
   2:string className;
   4:string body;
}

struct RainRequest {
   1:string classURI;
   2:string className;
   3:string methodName;
   4:string body;
   5:list<RainRequestParam> paramList;
}

struct RainResponseHead{
1:string classURI;
2:string className;
}
struct RainResponseBody{
1:string body;
}
struct RainResponse {
    1:RainResponseHead responseHead;
    2:RainResponseBody responseBody;
}

//服务端 客户端转换
service RouteService{

    RainResponse execute(1:RainRequest rainRequest);

}
