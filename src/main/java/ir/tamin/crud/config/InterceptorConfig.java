package ir.tamin.crud.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.local.LocalBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@Component
public class InterceptorConfig implements HandlerInterceptor {


    @Autowired
    private  final Bucket bucket ;
    private  final Integer numTokens;

    public InterceptorConfig(Bucket bucket, int numTokens) {
        this.bucket=bucket;
        this.numTokens=numTokens;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ConsumptionProbe probe = this.bucket.tryConsumeAndReturnRemaining(this.numTokens);

        if(probe.isConsumed()){
            response.addHeader("X-Rated ramining comsumed ", Long.toString(probe.getRemainingTokens()));
            return true;
        }
        
        response.setStatus(TOO_MANY_REQUESTS.value());
        response.addHeader("X-Rate-Limit-Retry-After-Milliseconds",
                Long.toString(TimeUnit.NANOSECONDS.toMillis(probe.getNanosToWaitForRefill())));
        return false;

    }


}
