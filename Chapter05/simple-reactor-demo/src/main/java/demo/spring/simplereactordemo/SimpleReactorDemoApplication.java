package demo.spring.simplereactordemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@Slf4j
public class SimpleReactorDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SimpleReactorDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux.range(1, 6)
                .doOnRequest(n -> log.info("Request {} number", n))
//                .publishOn(Schedulers.elastic())
                .doOnComplete(() -> log.info("Publisher COMPLETE 1"))
                .map(i -> {
                    log.info("Publish {}, {}", Thread.currentThread(), i);
                    return 1;
                })
//                .subscribeOn(Schedulers.single())
                .doOnComplete(() -> log.info("Publisher COMPLETE 2"))
//                .onErrorResume(e->{
//                    log.error("Exception {}", e.toString());
//                    return Mono.just(1);
//                })
//                .onErrorReturn(-1)
                .subscribe(i -> log.info("i: {}", i),
                        e -> log.error("error {}", e.toString()),
                        () -> log.info("Subscriber COMPLETE")
                );
//        Thread.sleep(2000);
    }
}
