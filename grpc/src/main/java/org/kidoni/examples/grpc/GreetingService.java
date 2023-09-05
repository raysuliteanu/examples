package org.kidoni.examples.grpc;

import io.grpc.stub.StreamObserver;

public class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greet(final Greeting.GreetingRequest request, final StreamObserver<Greeting.GreetingResponse> responseObserver) {
        String name = request.getName();
        responseObserver.onNext(Greeting.GreetingResponse.newBuilder().setResponse("hello, " + name).build());
        responseObserver.onCompleted();
    }
}
