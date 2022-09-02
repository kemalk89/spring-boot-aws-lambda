const AWS = require('aws-sdk');
AWS.config.region = 'eu-central-1';

const lambda = new AWS.Lambda();
const createTargetPayload = require('./create-target-payload');
const ACCOUNT_ID = '<PUT_HERE_YOUR_ID>';

exports.handler = (event, context) => {
    // here we create the payload which will be passed to the target lambda
    const payload = createTargetPayload({
        path: event.requestContext.http.path || '',
        httpMethod: event.requestContext.http.method,
        queryStringParameters: event.queryStringParameters,
        headers: event.headers,
        requestId: event.requestContext.requestId,
        requestTime: event.requestContext.time,
        requestTimeEpoch: event.requestContext.timeEpoch,
    });

    const params = {
        FunctionName: `arn:aws:lambda:eu-central-1:${ACCOUNT_ID}:function:todoApp`,
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
