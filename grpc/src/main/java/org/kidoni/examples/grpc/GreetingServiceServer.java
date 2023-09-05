package org.kidoni.examples.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServiceServer {
    private final Server server;

    public GreetingServiceServer(int port) {
        this(ServerBuilder.forPort(port));
    }

    public GreetingServiceServer(ServerBuilder<?> serverBuilder) {
        server = serverBuilder.addService(new GreetingService()).build();
    }

    public void start() throws IOException {
        server.start();
    }

    private void shutdown() {
        System.out.println("shutting down");
        if (server != null) {
            server.shutdown();
        }
    }

    private void handleRequests() throws InterruptedException {
        System.out.println("handling requests ...");
        server.awaitTermination();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GreetingServiceServer greetingServiceServer = new GreetingServiceServer(8888);
        Runtime.getRuntime().addShutdownHook(new Thread(greetingServiceServer::shutdown));
        greetingServiceServer.start();
        greetingServiceServer.handleRequests();
    }
}
