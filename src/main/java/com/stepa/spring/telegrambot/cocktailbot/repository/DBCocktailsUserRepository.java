package com.stepa.spring.telegrambot.cocktailbot.repository;

import com.stepa.spring.telegrambot.cocktailbot.repository.entity.DBCocktails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DBCocktailsUserRepository extends JpaRepository<DBCocktails,Integer>{

    @Query(value="SELECT * FROM cocktails t WHERE LOWER(t.name) like ?1",nativeQuery = true)
    List<DBCocktails> findByName(String request);

    @Query(value="SELECT * FROM cocktails t WHERE (LOWER(t.comps) like ?1 and LOWER(t.comps) like ?2)",nativeQuery = true)
    List<DBCocktails> findByIngrByTwo(String ingr1,String ingr2);

    @Query(value="SELECT * FROM cocktails t WHERE (LOWER(t.comps) like ?1 and LOWER(t.comps) like ?2 and LOWER(t.comps) like ?3)",nativeQuery = true)
    List<DBCocktails> findByIngrByThree(String ingr1,String ingr2, String ingr3);

    @Query(value="SELECT * FROM cocktails t WHERE (LOWER(t.comps) like ?1 and LOWER(t.comps) like ?2 and LOWER(t.comps) like ?3 and LOWER(t.int) like ?4)",nativeQuery = true)
    List<DBCocktails> findByIngrByFour(String ingr1,String ingr2,String ingr3, String ingr4);

    @Query(value="SELECT * FROM cocktails t WHERE (LOWER(t.comps) like ?1 and LOWER(t.comps) like ?2 and LOWER(t.comps) like ?3 and LOWER(t.comps) like ?4 and LOWER(t.comps) like ?5)",nativeQuery = true)
    List<DBCocktails> findByIngrByFive(String ingr1,String ingr2,String ingr3, String ingr4, String ingr5);

    DBCocktails findById(int id);

}
