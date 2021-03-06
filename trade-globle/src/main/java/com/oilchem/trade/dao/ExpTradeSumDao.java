package com.oilchem.trade.dao;

import com.oilchem.trade.dao.custom.ExpTradeSumDaoCustom;
import com.oilchem.trade.domain.sum.ExpTradeSum;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-5
 * Time: 下午4:57
 * To change this template use File | Settings | File Templates.
 */
public interface ExpTradeSumDao extends CrudRepository<ExpTradeSum,Long>,
        JpaSpecificationExecutor<ExpTradeSum>,
        ExpTradeSumDaoCustom {

    @Modifying
    @Transactional
    @Query("delete from ExpTradeSum t where t.year = :year and t.month = :month and t.sumType=:sumType")
    void delRepeatExpTradeSum(@Param("year") Integer year, @Param("month") Integer month, @Param("sumType") String sumType);

    List<ExpTradeSum> findByProductNameAndYearMonth(String productName,String yearMonth);

    List<ExpTradeSum> findByIdAndYearMonth(Long aLong, String yearMonth);

    @Query("select count(*) from ExpTradeSum where year=?1 and month=?2 and sumType=?3 ")
    Long countByYearAndMonthAndSumType(Integer year, Integer month, String sumType);
}
