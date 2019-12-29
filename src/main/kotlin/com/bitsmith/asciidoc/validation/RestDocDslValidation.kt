package com.bitsmith.asciidoc.validation

import com.bitsmith.asciidoc.model.RestDocTestDSL

class RestDocDslValidation {
    fun validate(restDocTestDSL: RestDocTestDSL): ValidationResults {
        val validationResults = ValidationResults()

        when (restDocTestDSL.testType) {
            null -> {
                validationResults.addError(ValidationErrorResult("testType", "testType is required"))
            }
        }
        if(restDocTestDSL.url == null) {
            validationResults.addError(ValidationErrorResult("url", "url is required"))
        }
        return validationResults
    }
}

interface ValidationResult

class ValidationErrorResult(val errorField: String, val errorMessage: String) : ValidationResult

class ValidationResults {
    private val results = mutableListOf<ValidationResult>()
    fun hasError(): Boolean {
        return results.size > 0
    }
    fun addError(validationResult: ValidationResult) {
        results.add(validationResult)
    }

    fun getResults(): MutableList<ValidationResult> {
        return results
    }

}