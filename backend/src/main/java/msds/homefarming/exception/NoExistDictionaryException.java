package msds.homefarming.exception;

public class NoExistDictionaryException extends RuntimeException
{
    public NoExistDictionaryException()
    {
        super();
    }

    public NoExistDictionaryException(String message)
    {
        super(message);
    }

    public NoExistDictionaryException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoExistDictionaryException(Throwable cause)
    {
        super(cause);
    }

    protected NoExistDictionaryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
