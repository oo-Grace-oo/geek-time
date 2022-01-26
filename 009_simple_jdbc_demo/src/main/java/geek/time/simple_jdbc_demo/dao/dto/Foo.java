package geek.time.simple_jdbc_demo.dao.dto;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/01/24
 */
@Data
@Builder
public class Foo {
    private Long id;
    private String bar;
}

