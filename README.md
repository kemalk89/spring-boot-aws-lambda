# Description
This project shows how Spring Boot API can be deployed on AWS Lambda using **AWS Lambda Function URLs**.
The code follows this Quick Start guide: https://github.com/awslabs/aws-serverless-java-container/wiki/Quick-start---Spring-Boot2.

The key is that we are **not using AWS Gateway**. We are ONLY relying on AWS Lambda. 
So with this setup you can deploy / run your Spring Boot API for free (of course AWS free tier has limitations, so don't forget this). 
**Hence, this setup is targeting private / hobby projects only.** 

# Prerequisites
### Create an IAM user
On AWS Management Console login as root user and create an IAM user (we will run the aws cli commands under the name of this user).
Assign this user to a security group and add policies (IAM > User groups > tab Permissions > dropdown Add permissions > Create inline policy):
- ``lambda:UpdateFunctionCode``

### Install & Configure AWS CLI
Next install the AWS CLI on your local machine & run `aws configure` in your terminal

### Create the lambdas 

We need two lambdas ``todoApp`` and ``todoAppProxy``. The ```todoAppProxy``` is a helper. Its job is only forwarding an event to ```todoApp```.
> The reason ```todoAppProxy``` exists is, that it was not possible to invoke ```todoApp``` directly. It was responding with an Internal server error (from the logs: com.amazonaws.serverless.exceptions.InvalidRequestEventException: The incoming event is not a valid request from Amazon API Gateway or an Application Load Balancer). 

- Now goto AWS Lambda and create:
  - Name: **todoApp**
  - Runtime: **java11**
  - Click on save
  - Tab Code > Runtime settings > Edit > Set handler to [com.example.demo.StreamLambdaHandler](./src/main/java/com/example/demo/StreamLambdaHandler.java)
- Now goto AWS Lambda and create:
  - Name: **todoAppProxy**
  - Runtime: **node**
  - Enable Function URL, Auth NONE
  - Click on save
  - Tab Configuration > Concurrency set value to 1 (Lambda concurrency throttling)
  - Copy & Paste the code into the Lambda Editor
    - [index.js](./miscellaneous/lambda-code/index.js)
    - [create-target-payload.js](./miscellaneous/lambda-code/create-target-payload.js)
  - Deploy

### Allow proxy to call actual lambda
Goto IAM > Policies > Create Policy
- Service: Lambda
- Actions: InvokeFunction, InvokeAsync
- Resources > Add ARN > arn:aws:lambda:eu-central-1:<YOUR_ID>:function:todoApp
- Give it a proper name

Goto IAM > Roles > Create Role
- Entity Type: AWS service
- Use Case: Lambda
- Add permissions: The one created above + AWSLambdaBasicExecutionRole
- Give it a proper name

Goto Lambda > todoAppProxy > Tab Configuration > Permissions > Section Execution role > Edit > Execution role > Use an existing role > Select role created above

# Upload the code to AWS
Whenever you do some changes you can run this commands to deploy your app on Lambda.
- Run `./mvnw clean package`
- Run `aws lambda update-function-code --function-name todoApp --zip-file fileb://./target/demo-0.0.1-SNAPSHOT.jar`