package msds.homefarming.exception;

public class ChatbotTimeoutException extends RuntimeException
{
    public ChatbotTimeoutException()
    {
        super();
    }

    public ChatbotTimeoutException(String message)
    {
        super(message);
    }

    public ChatbotTimeoutException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ChatbotTimeoutException(Throwable cause)
    {
        super(cause);
    }

    protected ChatbotTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
