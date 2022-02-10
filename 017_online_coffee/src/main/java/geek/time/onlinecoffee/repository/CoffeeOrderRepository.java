package geek.time.onlinecoffee.repository;

import geek.time.onlinecoffee.dao.CoffeeOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/02/10
 */
@Repository
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {

    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_Name(String name);
}
