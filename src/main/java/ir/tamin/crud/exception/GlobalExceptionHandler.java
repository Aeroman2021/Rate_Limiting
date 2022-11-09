package ir.tamin.crud.exception;

import ir.tamin.crud.exception.EmptyListException;
import ir.tamin.crud.exception.EmptyNameException;
import ir.tamin.crud.exception.PersonNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import static org.zalando.problem.Status.*;

@RestControllerAdvice
public class GlobalExceptionHandler implements ProblemHandling {


    @ExceptionHandler
    public ResponseEntity<Problem> handle(PersonNotFoundException ex, NativeWebRequest request){
        ThrowableProblem problem = Problem.builder().withStatus(NOT_FOUND)
                .withTitle("Person not Found")
                .withDetail(ex.getMessage())
                .build();
        return create(problem,request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handle(EmptyNameException ex, NativeWebRequest request){
        ThrowableProblem problem = Problem.builder().withStatus(BAD_REQUEST)
                .withTitle("Person with empty name")
                .withDetail(ex.getMessage())
                .build();
        return create(problem,request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handle(EmptyListException ex, NativeWebRequest request){
        ThrowableProblem problem = Problem.builder().withStatus(NO_CONTENT)
                .withTitle("Empty Result")
                .withDetail(ex.getMessage())
                .build();
        return create(problem,request);
    }
}
