package com.practice.service;

import com.practice.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogService {

    private Map<Integer, List<Theatre>> cityTheatreMap;
    private Map<Integer, List<Show>> theatreShowMap;
    private Map<Integer, List<Show>> movieShowMap;


    public List<Movie> getMovieByCity(Integer cityId){
        List<Theatre> theatreList = cityTheatreMap.get(cityId);
        Set<Movie> movieSet = new HashSet<Movie>();

        theatreList.forEach(theatre -> {
            theatreShowMap.get(theatre.getTheatreId()).forEach(show -> {
                movieSet.add(show.getMovie());
            });
        });

        return new ArrayList<>(movieSet);

    }

    public List<Show> getShowByCityAndMovie(Integer cityId, Integer movieId){
        List<Theatre> theatreList = cityTheatreMap.get(cityId);
        List<Show> showListMovieBased = movieShowMap.get(movieId);
        Set<Theatre> theatreSet = new HashSet<>(theatreList);

        return showListMovieBased.stream()
                .filter(show -> theatreSet.contains(show.getScreen().getTheatre())).distinct().collect(Collectors.toList());
    }

}
