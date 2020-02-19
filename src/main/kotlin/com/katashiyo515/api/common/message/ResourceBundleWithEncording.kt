package com.katashiyo515.api.common.message

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


class ResourceBundleWithEncording : ResourceBundle.Control() {
    @Throws(IllegalAccessException::class, InstantiationException::class, IOException::class)
    override fun newBundle(baseName: String, locale: Locale,
                           format: String, loader: ClassLoader, reload: Boolean): ResourceBundle {
        val bundleName = toBundleName(baseName, locale)
        val resourceName = toResourceName(bundleName, SUFFIX)
        loader.getResourceAsStream(resourceName).use { `is` -> InputStreamReader(`is`, ENCODE).use { isr -> BufferedReader(isr).use { reader -> return PropertyResourceBundle(reader) } } }
    }

    companion object {
        private const val SUFFIX = "properties"
        private const val ENCODE = "UTF-8"
    }
}