package com.sacavix.users.persistence.repository;

import com.sacavix.users.persistence.entity.Person;
import com.sacavix.users.persistence.projections.PersonFullLocation;
import com.sacavix.users.persistence.projections.PersonLocation;
import com.sacavix.users.persistence.projections.PersonLocationDTO2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;


public interface PersonRepository extends JpaRepository<Person, Long> {

    // using close interface projection
    @Query(value = "SELECT " +
            " P.NAME as name," +
            " P.PHONE_NUMBER AS phoneNumber, " +
            " A.STREET AS street " +
            " FROM PERSON P " +
            " INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID " +
            " WHERE P.ID = :id", nativeQuery = true)
    public PersonLocation getPersonLocation(@Param("id") Long personId);

    // using open interface projection
    @Query(value = "SELECT " +
            " P.NAME as name," +
            " P.PHONE_NUMBER AS phoneNumber, " +
            " A.STREET AS street " +
            " FROM PERSON P " +
            " INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID " +
            " WHERE P.ID = :id", nativeQuery = true)
    public PersonFullLocation getPersonFullLocation(@Param("id") Long personId);


    // using class based
    @Query(value = "SELECT " +
            " P.NAME as name," +
            " P.PHONE_NUMBER AS phoneNumber, " +
            " A.STREET AS street " +
            " FROM PERSON P " +
            " INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID " +
            " WHERE P.ID = :id",
            nativeQuery = true)
    public Tuple getPersonLocationDTO(@Param("id") Long personId);


    // using named query
    @Query(name = "getPersonLocationDTONamingQuery", nativeQuery = true)
    public PersonLocationDTO2 getPersonLocationDTO2(@Param("id") Long personId);


    // dynamically
    @Query(value = "SELECT " +
            " P.NAME as name," +
            " P.PHONE_NUMBER AS phoneNumber, " +
            " A.STREET AS street " +
            " FROM PERSON P " +
            " INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID " +
            " WHERE P.ID = :id", nativeQuery = true)
    <T> T getPersonLocationDynamically(@Param("id") Long personId, Class<T> type);
}
