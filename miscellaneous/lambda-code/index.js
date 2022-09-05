const AWS = require('aws-sdk');
AWS.config.region = 'eu-central-1';

const lambda = new AWS.Lambda();
const createTargetPayload = require('./create-target-payload');

const ARN_TODO = 'arn:aws:lambda:eu-central-1:<ACCOUNT_ID>:function:todoApp';

exports.handler = (event, context) => {
    const payload = createTargetPayload({
        path: event.requestContext.http.path || '',
        httpMethod: event.requestContext.http.method,
        queryStringParameters: event.queryStringParameters,
        headers: event.headers,
        requestId: event.requestContext.requestId,
        requestTime: event.requestContext.time,
        requestTimeEpoch: event.requestContext.timeEpoch,
        contentType: event.headers['content-type'],
        body: event.body,

    });

    const params = {
        FunctionName: ARN_TODO,
        InvocationType: 'RequestResponse',
        Payload: JSON.stringify(payload)
    };

    lambda.invoke(params, (err, data) => {
        if (err) {
            context.fail(err);
        } else {
            context.succeed(data.Payload);
        }
    });
};
