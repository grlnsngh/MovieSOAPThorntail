/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviesoap.services;

import com.mycompany.moviesoap.controllers.MoviesJpaController;
import com.mycompany.moviesoap.models.Movies;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author grlns
 */
@WebService(serviceName = "MovieService")
@MTOM(enabled=true,threshold = 1000000)
@HandlerChain(file = "/MovieService_handler.xml")
public class MovieService {

    /**
     * This is a sample web service operation
     */
    
    @WebMethod(operationName = "addMovie")
    public Movies addMovie(@WebParam(name = "addMovie") Movies movie) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            MoviesJpaController moviesJpaController = new MoviesJpaController(entityManagerFactory);
            List<Movies> moviesList = moviesJpaController.findMoviesEntities();
            ArrayList<BigDecimal> movieIds= new ArrayList<BigDecimal>();
            for(Movies m:moviesList){
                movieIds.add(m.getId());
            }  
            movie.setId(Collections.max(movieIds).add(BigDecimal.ONE));
            moviesJpaController.create(movie);   
            return movie;
        } catch (Exception ex) {
            Logger.getLogger(MovieService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @WebMethod(operationName = "updateMovie")
    public boolean updateMovie(@WebParam(name = "updateMovie") Movies movie) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            MoviesJpaController moviesJpaController = new MoviesJpaController(entityManagerFactory);
            moviesJpaController.edit(movie);   
            return true;
        } catch (Exception ex) {
            Logger.getLogger(MovieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @WebMethod(operationName = "deleteMovie")
    public boolean deleteMovie(@WebParam(name = "deleteMovie") BigDecimal id) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            MoviesJpaController moviesJpaController = new MoviesJpaController(entityManagerFactory);
            moviesJpaController.destroy(id);   
            return true;
        } catch (Exception ex) {
            Logger.getLogger(MovieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Read
    @WebMethod(operationName = "viewMovie")
    public List<Movies> viewMovie(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        MoviesJpaController moviesJpaController = new MoviesJpaController(entityManagerFactory);
        List<Movies> allMovieEntities = moviesJpaController.findMoviesEntities();
        return allMovieEntities;
    }
    
    //Find
    @WebMethod(operationName = "findMovie")
    public Movies findMovie(BigDecimal id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        MoviesJpaController moviesJpaController = new MoviesJpaController(entityManagerFactory);
        Movies movie = moviesJpaController.findMovies(id);
        return movie;
    }
    
}
