package com.FoodAid.Group35.repository;

import com.FoodAid.Group35.entity.Client;
import com.FoodAid.Group35.entity.Companies;
import com.FoodAid.Group35.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Companies, String> {

    //@Query("select distinct u.companyName from Companies");
    //public Companies fi;
    //SELECT DISTINCT p.name FROM People
    @Query("select distinct companyName from Companies")
    public List<String> fetchByCompanyName();

   // select * from FoodAid_app.items where companyId = (select companyID from FoodAid_app.companies where company_name = "Nandos");

   // @Query("select i from Items i where i.companies.companyID  in (select c.companyId from Companies c where c.companyName = :companyName)");
    //public List<String> fetchByWorkingCompany(@Param("companyName") String companyName);

    @Query("select i.item_name from Items i where i.companies.companyID  in (select c.companyID from Companies c where c.companyName = :companyName)")
    public List<String> fetchByWorkingCompany(@Param("companyName") String companyName);

}
