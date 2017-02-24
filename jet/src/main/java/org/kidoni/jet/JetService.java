package org.kidoni.jet;

import java.util.List;

import com.hazelcast.core.IList;
import com.hazelcast.jet.JetInstance;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.hazelcast.jet.stream.DistributedCollectors.toIList;

@RestController
public class JetService {
    private final JetInstance jetInstance;

    public JetService(final JetInstance jetInstance) {
        this.jetInstance = jetInstance;
    }

    @PostMapping("/go")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<String> doSomething(@RequestBody final List<Float> values) {
        final IList<Float> floats = jetInstance.getHazelcastInstance().getList("floats");
        floats.addAll(values);

        return jetInstance.getList("floats")
                .stream()
                .map(aFloat -> (Float) aFloat * (Float) aFloat)
                .map(Object::toString)
                .collect(toIList());
    }
}
