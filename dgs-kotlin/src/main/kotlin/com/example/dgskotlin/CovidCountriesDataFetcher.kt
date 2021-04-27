package com.example.dgskotlin

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class CovidCountriesDataFetcher {
    private var countries = mutableSetOf<Country>(
            Country("US", 123),
            Country("UK", 3423),
            Country("SL", 234),
            Country("SG", 5672019),
            Country("IN", 563))

    @DgsQuery
    fun countries(@InputArgument countryFilter : String?): Set<Country> {
        return if(countryFilter != null) {
            countries.filter { it.name.contains(countryFilter) }.toSet()
        } else {
            countries
        }
    }


    @DgsMutation
    fun addCountry(@InputArgument name: String, @InputArgument positiveCases: Int): Set<Country> {
        var country = countries.find { it.name.contains(name) }
        if(null!=country) {
            country.positiveCases=positiveCases
        } else {
           countries.add(Country(name,positiveCases))
        }

        return countries.toSet()
    }


    data class Country(var name: String, var positiveCases: Int)

}