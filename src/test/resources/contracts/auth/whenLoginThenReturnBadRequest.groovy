package contracts.auth

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "when login then wrong username or password"
    request {
        url "/api/auth/signin"
        method HttpMethod.POST.toString()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            accept(MediaType.APPLICATION_JSON_VALUE)
        }
        body("""
             {
                "username": "testing_bad_request"
            }
        """)
    }
    response {
        body("""
            {
                "path": "/api/auth/signin",
                "error": "Unauthorized",
                "message": "Bad credentials",
                "status": 401
            }
        """)
        status BAD_REQUEST()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}
