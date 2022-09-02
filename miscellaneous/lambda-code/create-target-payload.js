module.exports = ({path, httpMethod, requestId, requestTime, requestTimeEpoch, headers, queryStringParameters}) => {
    return {
        path,
        httpMethod,
        isBase64Encoded: true,
        queryStringParameters,
        multiValueQueryStringParameters: {},
        pathParameters: {},
        stageVariables: {},
        headers,
        multiValueHeaders: {
            "Accept": [
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"
            ],
            "Accept-Encoding": [
                "gzip, deflate, sdch"
            ],
            "Accept-Language": [
                "en-US,en;q=0.8"
            ],
            "Cache-Control": [
                "max-age=0"
            ],
            "Upgrade-Insecure-Requests": [
                "1"
            ],
            "User-Agent": [
                "Custom User Agent String"
            ],
            "X-Forwarded-For": [
                "127.0.0.1, 127.0.0.2"
            ],
            "X-Forwarded-Port": [
                "443"
            ],
            "X-Forwarded-Proto": [
                "https"
            ]
        },
        requestContext: {
            requestId,
            requestTime,
            requestTimeEpoch,
            // without identity lambda will response with http status 502, so we need this
            identity: {
                "cognitoIdentityPoolId": null,
                "accountId": null,
                "cognitoIdentityId": null,
                "caller": null,
                "accessKey": null,
                "cognitoAuthenticationType": null,
                "cognitoAuthenticationProvider": null,
                "userArn": null,
                "user": null
            },
        }
    };
}