package com.cretlabs.roomdatabase.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cretlabs.roomdatabase.entities.School;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by Gokul on 2/18/2018.
 */

@Dao
public interface SchoolDao {

    @Query("SELECT * FROM School")
    List<School> getAllSchools();

    @Query("SELECT * FROM School WHERE  schoolId= :schoolId")
    School loadSchoolById(int schoolId);

    @Query("SELECT * FROM School where schoolName = :schoolName ")
    List<School> findSchoolByName(String schoolName);

    @Insert(onConflict = IGNORE)
    void insertSchool(School school);

    @Insert(onConflict = IGNORE)
    void insertMultipleRecord(List<School> schools);

    @Delete
    void deleteSchool(School school);

}
