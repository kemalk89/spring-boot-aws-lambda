# Prerequisites
- On AWS Management Console login as root user and create an IAM user (we will run the aws cli commands under the name of this user)
- Assign user to a security group and add policies (IAM > User groups > tab Permissions > dropdown Add permissions > Create inline policy)
    - ``lambda:UpdateFunctionCode``
- Now goto AWS Lambda service and create a new function:
    - Name: todoApp
    - Runtime: java11
    - Click on save
- Now click on the newly created lambda, find "Runtime settings" and set handler to [com.example.demo.StreamLambdaHandler](./src/main/java/com/example/demo/StreamLambdaHandler.java)
- Next install the AWS CLI on your local machine & run `aws configure` in your terminal

# Upload the code to AWS
- Run `./mvnw clean package`
- Run `aws lambda update-function-code --function-name todoApp --zip-file fileb://./target/demo-0.0.1-SNAPSHOT.jar`