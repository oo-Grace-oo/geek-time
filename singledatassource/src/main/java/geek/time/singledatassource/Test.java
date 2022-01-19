package geek.time.singledatassource;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2021/12/06
 */
@RestController
@RequestMapping("/test")
public class Test {

    @PostMapping()
    public ResponseEntity<Void> getFile(@RequestBody Map<String ,MultipartFile> body) {
        return ResponseEntity.ok().build();
    }
}
