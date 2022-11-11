package ir.tamin.crud;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import ir.tamin.crud.config.InterceptorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@SpringBootApplication
public class CrudApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Refill refill = Refill.greedy(10, Duration.ofMinutes(1));
        Bandwidth limit1 = Bandwidth.classic(10, refill).withInitialTokens(1);
        Bucket bucket1 = Bucket4j.builder().addLimit(limit1).build();
        registry.addInterceptor(new InterceptorConfig(bucket1,1))
                .addPathPatterns("/person/*");

        Refill refill2 = Refill.greedy(5, Duration.ofMinutes(1));
        Bandwidth limit2 = Bandwidth.classic(5, refill2).withInitialTokens(1);
        Bucket bucket2 = Bucket4j.builder().addLimit(limit2).build();
        registry.addInterceptor(new InterceptorConfig(bucket2,1))
                .addPathPatterns("/get_all_persons/*");
    }

}
