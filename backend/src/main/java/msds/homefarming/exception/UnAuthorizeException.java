package msds.homefarming.exception;


public class UnAuthorizeException extends RuntimeException
{
    public UnAuthorizeException()
    {
        super();
    }

    public UnAuthorizeException(String message)
    {
        super(message);
    }

    public UnAuthorizeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UnAuthorizeException(Throwable cause)
    {
        super(cause);
    }

    protected UnAuthorizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
