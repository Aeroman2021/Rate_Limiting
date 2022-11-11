package ir.tamin.crud.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


public class InterceptorConfig implements HandlerInterceptor {

    private final Bucket bucket ;
    private final int numTokens;

    public InterceptorConfig(Bucket bucket, int numTokens) {
        this.bucket=bucket;
        this.numTokens=numTokens;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ConsumptionProbe probe = this.bucket.tryConsumeAndReturnRemaining(this.numTokens);

        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining",
                    Long.toString(probe.getRemainingTokens()));
            return true;
        }

        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); // 429
        response.addHeader("X-Rate-Limit-Retry-After-Milliseconds",
                Long.toString(TimeUnit.NANOSECONDS.toMillis(probe.getNanosToWaitForRefill())));
        return false;

    }


}
