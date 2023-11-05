package msds.homefarming.exception;

public class NoExistDiaryException extends RuntimeException
{
    public NoExistDiaryException()
    {
        super();
    }

    public NoExistDiaryException(String message)
    {
        super(message);
    }

    public NoExistDiaryException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoExistDiaryException(Throwable cause)
    {
        super(cause);
    }

    protected NoExistDiaryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
