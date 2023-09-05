package org.kidoni.examples.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    private final ManagedChannel channel;
    private final GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceStub;

    public GreetingClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    public GreetingClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        greetingServiceStub = GreetingServiceGrpc.newBlockingStub(channel);
    }

    void greet(String name) {
        Greeting.GreetingRequest greetingRequest = Greeting.GreetingRequest.newBuilder().setName(name).build();
        Greeting.GreetingResponse response = greetingServiceStub.greet(greetingRequest);
        System.out.println(response.getResponse());
    }

    void shutdown() {
        channel.shutdown();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: GreetingClient name");
            System.exit(-1);
        }

        GreetingClient greetingClient = new GreetingClient("localhost", 8888);
        try {
            greetingClient.greet(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            greetingClient.shutdown();
        }
    }
}
