package develop.models;

import reactor.core.publisher.Mono;

public class IdGenerator {
    private static IdGenerator instance;
    private long id = 0;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public Mono<Long> generateId() {
        return Mono.create(sink -> {
            long newId = incrementAndGet();
            sink.success(newId);
        });
    }

    public synchronized long incrementAndGet() {
        return ++id;
    }

}

