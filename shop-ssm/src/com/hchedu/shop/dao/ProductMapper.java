package com.hchedu.shop.dao;

import com.hchedu.shop.entities.Product;
import com.hchedu.shop.entities.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

	List<Product> queryProductByHotAndNew(@Param("min")Integer min, @Param("max") Integer max);

	List<Product> selectPageByCid(@Param("cid")Integer cid, @Param("begin")Integer begin, @Param("limit")int limit);

	int countByCid(Integer cid);

	List<Product> selectPageByCsid(@Param("csid")Integer csid, @Param("begin")int begin, @Param("limit")int limit);

	int countByCsid(Integer csid);

	List<Product> seachProduct(@Param("proCategory")String proCategory,@Param("proPriceMin") Double proPriceMin, @Param("proPriceMax")Double proPriceMax, @Param("proName")String proName,@Param("begin") int begin, @Param("limit")int limit);

	
}