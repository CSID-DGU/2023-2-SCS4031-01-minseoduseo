package msds.homefarming.exception.advice;

import msds.homefarming.exception.*;
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

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler
    public ErrorResult noAuthorityException(NoAuthorityException e)
    {
        return new ErrorResult(403L, "FORBIDDEN", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult noExistDiaryException(NoExistDiaryException e)
    {
        return new ErrorResult(400L, "BAD_REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult noExistDictionaryException(NoExistDictionaryException e)
    {
        return new ErrorResult(400L, "BAD_REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult alreadyExistDictionaryException(AlreadyExistDictionaryException e)
    {
        return new ErrorResult(400L, "BAD_REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler
    public ErrorResult chatbotTimeOutException(ChatbotTimeoutException e)
    {
        return new ErrorResult(504L, "GATEWAY_TIMEOUT", e.getMessage());
    }
}
