package geek.time.error_code_demo.exception;

import org.springframework.dao.DuplicateKeyException;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/01/26
 */
public class CustomDuplicatedKeyException extends DuplicateKeyException {

    /**
     * Constructor for DuplicateKeyException.
     *
     * @param msg the detail message
     */
    public CustomDuplicatedKeyException(String msg) {
        super(msg);
    }

    /**
     * Constructor for DuplicateKeyException.
     *
     * @param msg   the detail message
     * @param cause the root cause from the data access API in use
     */
    public CustomDuplicatedKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
