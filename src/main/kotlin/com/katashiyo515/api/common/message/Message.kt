package com.katashiyo515.api.common.message

import java.text.MessageFormat
import java.util.Locale
import java.util.ResourceBundle

class Message {
    companion object {
        fun getMessage(code: String, vararg args: Any): String {
            return try {
                val resourceBundle = ResourceBundle.getBundle("messages", Locale.JAPANESE, ResourceBundleWithEncording())
                MessageFormat.format(resourceBundle.getString(code), *args)
            } catch (e: Exception) {
                ""
            }
        }
    }
}
