namespace java com.laoge.raining.server.invoke


struct RainRequestParam {
   1:string classRUI;
   2:string className;
   3:string methodName;
   4:string body;
}

struct RainRequest {
   1:string classRUI;
   2:string className;
   3:string methodName;
   4:string body;
   5:list<RainRequestParam> paramList;
}

struct RainResponseHead{
1:string classRUI;
2:string className;
3:string methodName;
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

    RainResponse route(1:RainRequest rainRequest);

}
