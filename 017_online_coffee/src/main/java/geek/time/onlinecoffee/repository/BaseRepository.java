package geek.time.onlinecoffee.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/02/10
 */
@NoRepositoryBean
public interface BaseRepository<T, Long> extends PagingAndSortingRepository<T, Long> {

    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}

