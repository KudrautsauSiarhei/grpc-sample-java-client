package org.example;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class StreamClient {
    public static void main(String[] args) {
        final ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:8081")
                .usePlaintext()
                .build();

        final GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder().setName("Siarhei").build();

        final Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greetings(request);

        while (response.hasNext()) {
            System.out.println(response.next());
        }

        channel.shutdownNow();
    }
}
