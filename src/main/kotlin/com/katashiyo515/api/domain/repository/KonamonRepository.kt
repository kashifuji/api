package com.katashiyo515.api.domain.repository

import com.katashiyo515.api.domain.entity.Konamon

interface KonamonRepository {
    fun findById (id : Int) : Konamon
    fun findAll () : List<Konamon>
    fun create (konamon : Konamon) : Konamon
    fun update (konamon : Konamon) : Konamon
    fun delete (id : Int)
}