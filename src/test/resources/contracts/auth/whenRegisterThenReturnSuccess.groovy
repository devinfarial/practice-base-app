package contracts.auth

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "when register then success"
    request {
        url "/api/auth/signup"
        method HttpMethod.POST.toString()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            accept(MediaType.APPLICATION_JSON_VALUE)
        }
        body("""
             {
               "username": "test",
               "password": "123456",
               "email": "test@mail.com"
            }
        """)
    }
    response {
        status OK()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}