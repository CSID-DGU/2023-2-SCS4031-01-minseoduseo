package msds.homefarming.exception;

public class NoExistPlantException extends RuntimeException
{
    public NoExistPlantException()
    {
        super();
    }

    public NoExistPlantException(String message)
    {
        super(message);
    }

    public NoExistPlantException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoExistPlantException(Throwable cause)
    {
        super(cause);
    }

    protected NoExistPlantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
