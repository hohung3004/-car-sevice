package com.notfound.carservice.repository;

import com.notfound.carservice.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandReponsitory extends JpaRepository<Brand, Integer> {

    @Query("select b from Brand b where b.brandName= :brandName")
    Brand findBrandByCreate(String brandName);

    /*
        sql: select * from brand where brand_name = 'toyota'

        Jpa sql:
            select, from, where : Thành phần cấu thành sql
                b: phần tử được alias đối tượng
                Brand: Đối đượng ánh tới database
                :branchName: Cách truyền dữ liệu vào Jpa sql (cú pháp: :parameterName)
     */

    @Query("select b from Brand b where b.id <> :id and b.brandName = :brandName")
    Brand findBrandByUpdate(String brandName, Integer id);

}
