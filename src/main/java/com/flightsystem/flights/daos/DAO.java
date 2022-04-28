package com.flightsystem.flights.daos;

import com.flightsystem.flights.dtos.DTO;

import java.util.List;

public interface DAO<T extends DTO> {
    /***
     * Retrieve an object from DB by its ID.
     * @param id of object.
     * @return pulled Object.
     */
    T get(int id);
    /*---------------------------------------------------------------------------------------------------------------------*/
    /***
     * Retrieve a list of all the Objects from DB.
     * @return a List of pulled Objects.
     */
    List<T> getAll();
    /*---------------------------------------------------------------------------------------------------------------------*/
    /***
     * Adds an object to the DB.
     * @param t the Object to add into DB.
     */
    void add(T t);
    /*---------------------------------------------------------------------------------------------------------------------*/
    /***
     * Updates an existing object in DB.
     * @param t the object which will override the existing object from DB.
     */
    void update(T t);
    /*---------------------------------------------------------------------------------------------------------------------*/
    /***
     * Deletes an existing object from DB.
     * @param t the object to delete from DB.
     */
    void remove(T t);
}
