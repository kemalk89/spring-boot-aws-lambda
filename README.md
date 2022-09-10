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

### Create the lambda 

We need a lambda ``todoApp``:
- Name: **todoApp**
- Runtime: **java11**
- Enable Function URL, Auth NONE
- Click on save
- Tab Configuration > Concurrency set value to 1 (Lambda concurrency throttling)
- Tab Code > Runtime settings > Edit > Set handler to [com.examples.todo.lambda.StreamLambdaHandler](src/main/java/com/examples/todo/StreamLambdaHandler.java)
- Deploy

# Upload the code to AWS
Whenever you do some changes you can run this commands to deploy your app on Lambda.
- Run `./mvnw clean package`
- Run `aws lambda update-function-code --function-name todoApp --zip-file fileb://./target/todo-api-0.0.1-SNAPSHOT.jar`