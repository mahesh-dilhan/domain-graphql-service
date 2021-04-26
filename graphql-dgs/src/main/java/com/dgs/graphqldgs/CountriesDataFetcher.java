package com.dgs.graphqldgs;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class CountriesDataFetcher {

    private final List<Country> countries = List.of(
            new Country("UK", 23719),
            new Country("USA", 2731),
            new Country("SriLanka", 321),
            new Country("Singapore", 343),
            new Country("India", 1283)
    );

    @DgsQuery
    public List<Country> countries(@InputArgument String countryFilter) {
        if(countryFilter == null) {
            return countries;
        }

        return countries.stream().filter(s -> s.getName().contains(countryFilter)).collect(Collectors.toList());
    }
}