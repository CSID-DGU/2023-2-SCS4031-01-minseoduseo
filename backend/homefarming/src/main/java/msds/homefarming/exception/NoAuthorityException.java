package msds.homefarming.exception;

public class NoAuthorityException extends RuntimeException
{
    public NoAuthorityException()
    {
        super();
    }

    public NoAuthorityException(String message)
    {
        super(message);
    }

    public NoAuthorityException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoAuthorityException(Throwable cause)
    {
        super(cause);
    }

    protected NoAuthorityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
