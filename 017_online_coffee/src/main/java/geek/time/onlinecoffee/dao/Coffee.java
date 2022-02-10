package geek.time.onlinecoffee.dao;

import java.io.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.money.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/02/10
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@Table(name = "T_MENU")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee extends BaseEntity implements Serializable {

    private String name;

    @Column
    // @Type(type =
    // "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
    // parameters = {
    // @org.hibernate.annotations.Parameter(name = "currencyCode", value =
    // "CNY") })
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = {
            @org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY") })
    private Money price;

}
