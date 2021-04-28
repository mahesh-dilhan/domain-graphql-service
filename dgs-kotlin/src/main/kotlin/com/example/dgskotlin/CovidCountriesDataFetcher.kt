package com.example.dgskotlin

import com.netflix.graphql.dgs.*
import org.reactivestreams.Publisher
import reactor.core.publisher.ConnectableFlux

@DgsComponent
class CovidCountriesDataFetcher {

    private var countries = mutableSetOf<Country>(
            Country("US", 123),
            Country("UK", 3423),
            Country("SL", 234),
            Country("SG", 5672019),
            Country("IN", 563))

    private var vaccines = mutableSetOf<Vaccine>(
            Vaccine("Moderna", "US", true)
            ,Vaccine("Fizer", "US", true)
            ,Vaccine("AstraZenica", "UK", true)
            ,Vaccine("Cinfarm", "CH", true)
            ,Vaccine("Sputnic", "RUS", true)
            ,Vaccine("AstraZenica", "SL", true)
  )

    @DgsQuery
    fun countries(@InputArgument countryFilter : String?): Set<Country> {
        return if(countryFilter != null) {
            countries.filter { it.name.contains(countryFilter) }.toSet()
        } else {
            countries
        }
    }


    @DgsData(parentType = "Country", field = "vaccines")
    fun vaccines(dfe :DgsDataFetchingEnvironment) : Set<Vaccine> {
        val cntry: Country = dfe.getSource()
        return if(cntry!=null){
            vaccines.filter{it.country.contains(cntry.name)}.toSet()
        } else {
            mutableSetOf<Vaccine>()
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
    data class Vaccine(var name: String, var country: String, var ordered: Boolean)
}