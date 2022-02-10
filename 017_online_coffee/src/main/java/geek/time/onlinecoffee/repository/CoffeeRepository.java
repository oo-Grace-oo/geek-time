package geek.time.onlinecoffee.repository;

import org.springframework.stereotype.Repository;

import geek.time.onlinecoffee.dao.Coffee;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/02/10
 */
@Repository
public interface CoffeeRepository extends BaseRepository<Coffee, Long> {
}
