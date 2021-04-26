package com.example.dgskotlin

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class CovidCountriesDataFetcher {
    private val countries = listOf(
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

    data class Country(val name: String, val positiveCases: Int)
}