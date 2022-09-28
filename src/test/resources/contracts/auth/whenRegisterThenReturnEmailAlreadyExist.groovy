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
               "username": "test_email_already_exist",
               "password": "123456",
               "email": "dfarial17@gmail.com"
            }
        """)
    }
    response {
        status BAD_REQUEST()
        body("""
            {
                "message": "Error: Email is already in use!"
            }
        """)
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}