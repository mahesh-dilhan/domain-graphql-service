package com.example.dgskotlin

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class CovidCountriesDataFetcher {
    private var countries = mutableListOf<Country>(
            Country("US", 123),
            Country("UK", 3423),
            Country("SL", 234),
            Country("SG", 5672019),
            Country("IN", 563))

    @DgsQuery
    fun countries(@InputArgument countryFilter : String?): List<Country> {
        return if(countryFilter != null) {
            countries.filter { it.name.contains(countryFilter) }
        } else {
            countries
        }
    }


    @DgsMutation
    fun addCountry(@InputArgument name: String, @InputArgument positiveCases: Int): List<Country> {
       countries.add(CovidCountriesDataFetcher.Country(name,positiveCases))
       return countries
    }

    data class Country(val name: String, var positiveCases: Int)

}