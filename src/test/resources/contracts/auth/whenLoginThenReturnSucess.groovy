package contracts.auth

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType


Contract.make {
    description "when login then success"
    request {
        url "/api/auth/signin"
        method HttpMethod.POST.toString()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            accept(MediaType.APPLICATION_JSON_VALUE)
        }
        body("""
             {
                "username": "devin",
                "password": "123456"
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