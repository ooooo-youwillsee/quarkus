package io.quarkus.it.kotser

import io.quarkus.test.junit.QuarkusTest
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import javax.ws.rs.core.MediaType

@QuarkusTest
open class ResourceTest {
    @Test
    fun testGet() {
         When {
             get("/")
         } Then {
             statusCode(200)
             body(`is`(
                 """
                    {
                      "name": "Jim Halpert",
                      "defaulted": "hi there!"
                    }""".trimIndent()
             ))
         }
    }

    @Test
    fun testSuspendGet() {
        When {
            get("/suspend")
        } Then {
            statusCode(200)
            body(`is`(
                """
                    {
                      "name": "Jim Halpert",
                      "defaulted": "hi there!"
                    }""".trimIndent()
            ))
        }
    }

    @Test
    fun testSuspendGetList() {
        When {
            get("/suspendList")
        } Then {
            statusCode(200)
            body(`is`(
                """
[
  {
    "name": "Jim Halpert",
    "defaulted": "hi there!"
  }
]
""".trimIndent()))
        }
    }

    @Test
    fun testPost() {
        Given {
            body("""{ "name": "Pam Beasley" }""")
            contentType(JSON)
        } When {
            post("/")
        } Then {
            statusCode(200)
            body(`is`("""
                {
                  "name": "Pam Halpert",
                  "defaulted": "hi there!"
                }""".trimIndent()))
        }
    }

    @Test
    fun testCreateAndFetch() {
        Given {
            log().ifValidationFails()
        } When {
            accept(MediaType.TEXT_PLAIN)
            get("/create")
        } Then {
            log().ifValidationFails()
            statusCode(200)
            body(CoreMatchers.equalTo("hello, world"))
        }
    }
}
