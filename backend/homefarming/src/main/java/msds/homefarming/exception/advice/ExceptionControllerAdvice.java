package msds.homefarming.exception.advice;

import msds.homefarming.exception.NoExistMemberException;
import msds.homefarming.exception.NoExistPlantException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice
{
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult noExistMemberException(NoExistMemberException e)
    {
        return new ErrorResult(400L, "BAD_REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult noExistPlantException(NoExistPlantException e)
    {
        return new ErrorResult(400L, "BAD_REQUEST", e.getMessage());
    }
}
