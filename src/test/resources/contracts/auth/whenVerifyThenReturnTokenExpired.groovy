package contracts.auth

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "when verify user then return expired token"
    request {
        url "/api/auth/verify?token=bb0v0sj2-d9a2-47f1-9ff5-855dea4ceea5"
        method HttpMethod.GET.toString()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            accept(MediaType.APPLICATION_JSON_VALUE)
        }
    }
    response {
        status INTERNAL_SERVER_ERROR()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}