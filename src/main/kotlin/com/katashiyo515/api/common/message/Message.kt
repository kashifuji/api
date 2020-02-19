package com.katashiyo515.api.common.message

import com.katashiyo515.api.common.error.ApiErrorDefinition
import java.text.MessageFormat
import java.util.*

class Message {
    companion object {
        fun getMessage(code: String, vararg args: Any) : String {
            return try {
                val resourceBundle = ResourceBundle.getBundle("messages", Locale.JAPANESE, ResourceBundleWithEncording())
                MessageFormat.format(resourceBundle.getString(code), *args)
            } catch (e : Exception) {
                ""
            }
        }
    }
}