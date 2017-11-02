package com.laoge.raining.client.router;

import com.netflix.ribbon.ClientOptions;
import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.http.HttpRequestTemplate;
import com.netflix.ribbon.http.HttpResourceGroup;
import io.netty.buffer.ByteBuf;

import java.util.Observable;

public class Test  {

    public static void main(String[] args){
        HttpResourceGroup httpResourceGroup = Ribbon.createHttpResourceGroup("movieServiceClient",
                ClientOptions.create()
                        .withMaxAutoRetriesNextServer(3)
                        .withConfigurationBasedServerList("localhost:8080,localhost:8088"));
        HttpRequestTemplate<ByteBuf> recommendationsByUserIdTemplate = httpResourceGroup.newTemplateBuilder("recommendationsByUserId", ByteBuf.class)
                .withMethod("GET")
                .withUriTemplate("/users/{userId}/recommendations")
               // .withFallbackProvider(new RecommendationServiceFallbackHandler())
               // .withResponseValidator(new RecommendationServiceResponseValidator())
                .build();
/*        Observable<ByteBuf> result = recommendationsByUserIdTemplate.requestBuilder()
                .withRequestProperty("userId", "user1")
                .build()
                .observe();
    */}
}
