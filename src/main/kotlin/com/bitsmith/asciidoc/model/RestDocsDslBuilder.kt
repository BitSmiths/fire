package com.bitsmith.asciidoc.model

import java.io.File
import java.lang.IllegalArgumentException
import java.net.URL


@DslMarker
annotation class RestDocDsl

fun restDocTest(restDocTest: RestDocTestDSL.() -> Unit): RestDocTestDSL = RestDocTestDSL().apply(restDocTest)
@RestDocDsl
class RestDocTestDSL {
    var testType = TestTypeEnum.REST
    val with = this
    private val authorization = Authorization()

     lateinit var url: URL

    private lateinit var requestPayLoadPlusDocumentation: RequestPayLoadPlusDocumentation
    private lateinit var responsePayLoadDocumentation: ResponsePayLoadDocumentation

    private lateinit var httpMethod: HttpMethod

    infix fun type(testType: String): RestDocTestDSL {
        this.testType = TestTypeEnum.valueOf(testType)
        return this
    }

    infix fun username (username: String): RestDocTestDSL {
        this.authorization.username = username
        return this
    }
    infix fun password(password: String): RestDocTestDSL {
        this.authorization.password = password
        return this
    }
    infix fun endpointUrl(url: String): RestDocTestDSL {
        this.url = URL(url)
        return this
    }
    infix fun httpMethod(httpMethod: String): RestDocTestDSL {
        this.httpMethod = HttpMethod.valueOf(httpMethod)
        return this
    }
    fun requestPayLoadPlusDocumentation(request: RequestPayLoadPlusDocumentation.() -> Unit) : RestDocTestDSL {
        this.requestPayLoadPlusDocumentation = RequestPayLoadPlusDocumentation()
        this.requestPayLoadPlusDocumentation.apply(request)
        return this
    }

    fun responsePayLoadDocumentation(response: ResponsePayLoadDocumentation.() -> Unit) : RestDocTestDSL {
        this.responsePayLoadDocumentation = ResponsePayLoadDocumentation()
        this.responsePayLoadDocumentation.apply(response)
        return this
    }
    override fun toString(): String {
        return "RestDocTestDSL(testType=$testType, authorization=$authorization, url=$url, requestPayLoadPlusDocumentation=$requestPayLoadPlusDocumentation, httpMethod=$httpMethod)"
    }


}

enum class TestTypeEnum {
    REST, SOAP, RABBITMQ
}

 class Authorization{
    var username: String? = null
    var password: String? = null
}


enum class HttpMethod {
    POST, PUT, GET, DELETE
}

@RestDocDsl
class RequestPayLoadPlusDocumentation{
    private lateinit var payloadRequestFile: File
    private lateinit var payloadDocumentationCsvFile: File
    public val with = this

    infix fun payloadRequestFile(file: String): RequestPayLoadPlusDocumentation {
        this.payloadRequestFile = File(file)
        if(!this.payloadRequestFile.exists()) {
            throw IllegalArgumentException("File $file does not exist at this path ${payloadRequestFile.absoluteFile}")
        }
        return this
    }

    infix fun payloadDocumentationCsvFile(file: String): RequestPayLoadPlusDocumentation {
        this.payloadDocumentationCsvFile = File(file)
        if(!this.payloadDocumentationCsvFile.exists()) {
            throw IllegalArgumentException("File $file does not exist at this path ${payloadDocumentationCsvFile.absoluteFile}")
        }
        return this
    }

}

@RestDocDsl
class ResponsePayLoadDocumentation {
    private lateinit var responsePaloadDocumentationCsvFile: File
    val with = this

    infix fun responsePaloadDocumentationCsvFile(file: String): ResponsePayLoadDocumentation {
        this.responsePaloadDocumentationCsvFile = File(file)
        if(!this.responsePaloadDocumentationCsvFile.exists()) {
            throw IllegalArgumentException("File $file does not exist at this path ${responsePaloadDocumentationCsvFile.absoluteFile}")
        }
        return this
    }

}

