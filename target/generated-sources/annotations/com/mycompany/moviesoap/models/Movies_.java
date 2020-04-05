package com.mycompany.moviesoap.models;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-04T21:26:08")
@StaticMetamodel(Movies.class)
public class Movies_ { 

    public static volatile SingularAttribute<Movies, byte[]> image;
    public static volatile SingularAttribute<Movies, String> director;
    public static volatile SingularAttribute<Movies, String> producer;
    public static volatile SingularAttribute<Movies, BigDecimal> id;
    public static volatile SingularAttribute<Movies, String> title;

}