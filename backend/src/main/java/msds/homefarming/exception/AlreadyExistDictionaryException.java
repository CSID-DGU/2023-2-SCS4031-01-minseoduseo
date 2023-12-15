package msds.homefarming.exception;

public class AlreadyExistDictionaryException extends RuntimeException
{
    public AlreadyExistDictionaryException()
    {
        super();
    }

    public AlreadyExistDictionaryException(String message)
    {
        super(message);
    }

    public AlreadyExistDictionaryException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AlreadyExistDictionaryException(Throwable cause)
    {
        super(cause);
    }

    protected AlreadyExistDictionaryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
