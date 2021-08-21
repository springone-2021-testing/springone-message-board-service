import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("should delete the message by user")
    request {
        url("/message/Cora")
//        url("/message?username=Cora")
        method(DELETE())
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([message: "Success", type: "Delete", parameter: "1"])
        headers {
            contentType(applicationJson())
        }

    }
}
