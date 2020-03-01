package com.katashiyo515.api.common.error

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

@ApiModel(description = "エラーレスポンス", value = "Error")
class ApiError : Serializable {
    private val serialVersionUID = 1L

    @ApiModelProperty(value = "エラー詳細", position = 1)
    var error: ErrorDetail = ErrorDetail()

    @ApiModel(description = "エラー詳細", value = "ErrorDetail")
    inner class ErrorDetail {
        @ApiModelProperty(value = "コード", position = 1)
        var code: String? = null
        @ApiModelProperty(value = "メッセージ", position = 2)
        var message: String? = null
        @ApiModelProperty(value = "HTTPステータスコード", position = 2)
        var status: String? = null
    }
}
