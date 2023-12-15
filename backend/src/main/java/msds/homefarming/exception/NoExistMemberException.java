package msds.homefarming.exception;

public class NoExistMemberException extends RuntimeException
{
    public NoExistMemberException()
    {
        super();
    }

    public NoExistMemberException(String message)
    {
        super(message);
    }

    public NoExistMemberException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoExistMemberException(Throwable cause)
    {
        super(cause);
    }

    protected NoExistMemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
