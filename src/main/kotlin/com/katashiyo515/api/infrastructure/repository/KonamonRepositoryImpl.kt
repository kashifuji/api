package com.katashiyo515.api.infrastructure.repository

import com.katashiyo515.api.common.exception.ResourceLockedException
import com.katashiyo515.api.common.exception.ResourceDelicateException
import com.katashiyo515.api.common.exception.ResourceNotFoundException
import com.katashiyo515.api.domain.entity.Konamon
import com.katashiyo515.api.domain.repository.KonamonRepository
import com.katashiyo515.api.infrastructure.mapper.KonamonDbMapper
import org.slf4j.LoggerFactory
import org.springframework.dao.CannotAcquireLockException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Repository

@Repository
class KonamonRepositoryImpl(private val konamonDbMapper: KonamonDbMapper) : KonamonRepository {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    override fun findById(id : Int): Konamon {
        return konamonDbMapper.findById(id) ?: throw ResourceNotFoundException(id = id)
    }

    override fun findAll(): List<Konamon> {
        var konamons = konamonDbMapper.findAll()
        if ( konamons.isEmpty()) throw ResourceNotFoundException()
        return konamons
    }

    override fun create(konamon: Konamon): Konamon {
        try {
            konamonDbMapper.insert(konamon)
        } catch (e: DataIntegrityViolationException) {
            throw ResourceDelicateException(id = konamon.id).initCause(e)
        }
        return konamon
    }

    override fun update(konamon: Konamon): Konamon {
        try {
            val count = konamonDbMapper.update(konamon)
            if (count == 0 ) throw ResourceNotFoundException()
        } catch (e: CannotAcquireLockException) {
            throw ResourceLockedException(id = 1).initCause(e)
        }
        return konamon
    }

    override fun delete(id: Int) {
        try {
            val count = konamonDbMapper.delete(id)
            if (count == 0 ) throw ResourceNotFoundException()
        } catch (e: CannotAcquireLockException) {
            throw ResourceLockedException(id = 1).initCause(e)
        }
    }
}