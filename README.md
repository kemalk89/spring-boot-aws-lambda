# Prerequisites
On AWS Management Console login as root user and create an IAM user (we will run the aws cli commands under the name of this user).

### Creating the lambdas 

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
  - Enable Function URL
  - Click on save
  - Tab Configuration > Concurrency set value to 1 (Lambda concurrency throttling)

### Allow proxy lambda to call actual lambda
Goto IAM > Policies > Create Policy
- Service: Lambda
- Actions: InvokeFunction, InvokeAsync
- Resources > Add ARN
- Give it a proper name

Goto IAM > Roles > Create Role
- Entity Type: AWS service
- Use Case: Lambda
- Add permissions: The one created above + AWSLambdaBasicExecutionRole
- Give it a proper name

Goto Lambda > todoAppProxy > Tab Configuration > Permissions > Section Execution role > Edit > Execution role > Use an existing role > Select role created above

### Interact with the AWS CLI
Assign user to a security group and add policies (IAM > User groups > tab Permissions > dropdown Add permissions > Create inline policy)
   - ``lambda:UpdateFunctionCode``

Next install the AWS CLI on your local machine & run `aws configure` in your terminal

# Upload the code to AWS
- Run `./mvnw clean package`
- Run `aws lambda update-function-code --function-name todoApp --zip-file fileb://./target/demo-0.0.1-SNAPSHOT.jar`