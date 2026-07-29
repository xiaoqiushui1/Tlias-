package hhjvm.java.exception;

public class DeleteDeptHasEmpException extends RuntimeException {
    // 只需要一个带消息的构造方法
    public DeleteDeptHasEmpException(String message) {
        super(message);
    }

}
