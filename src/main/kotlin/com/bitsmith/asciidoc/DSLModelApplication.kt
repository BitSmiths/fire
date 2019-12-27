package com.bitsmith.asciidoc

import com.bitsmith.asciidoc.model.restDocTest



open class DSLModelApplication {
	companion object {
		@JvmStatic fun main(args: Array<String>) {
			printIt()
		}
	}
}
fun printIt() {
	val t = restDocTest {
		with type "REST"
		with username "test"
		with password "pass"
		with endpointUrl "http://localhost:8080/val"
		with httpMethod "POST"
		requestPayLoadPlusDocumentation {
			/*with payloadRequestFile ""
            with payloadDocumentationCsvFile ""*/
		}
		responsePayLoadDocumentation {
			//with responsePayloadDocumentationCsvFile ""
		}
	}
	println(t.testType)
}


