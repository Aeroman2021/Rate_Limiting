package ir.tamin.crud.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.github.bucket4j.local.LocalBucket;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.Duration;


@SpringBootApplication
public class LoggingConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Refill refill = Refill.greedy(10, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(10, refill).withInitialTokens(1);
        Bucket bucket = Bucket4j.builder().addLimit(limit).build();
        registry.addInterceptor(new InterceptorConfig(bucket,1))
                .addPathPatterns("/get_person_by_id");

        refill = Refill.greedy(5, Duration.ofMinutes(1));
        limit = Bandwidth.classic(5, refill).withInitialTokens(1);
        bucket = Bucket4j.builder().addLimit(limit).build();
        registry.addInterceptor(new InterceptorConfig(bucket,1))
                .addPathPatterns("/get_all_persons");
    }




}

