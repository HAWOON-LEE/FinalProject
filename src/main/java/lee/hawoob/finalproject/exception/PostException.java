package lee.hawoob.finalproject.exception;

public class PostException extends BaseException{

    private BaseExceptionType baseExceptionType;

    public PostException(BaseExceptionType baseExceptionType){
        this.baseExceptionType = baseExceptionType;
    }

    public BaseExceptionType getExceptionType(){
        return this.baseExceptionType;
    }
}