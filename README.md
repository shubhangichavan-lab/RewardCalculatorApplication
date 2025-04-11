This is a demo application for reward calculation. The Reward Calculator is a user-friendly application designed to simplify reward point calculations for it's customers. It tracks purchases and calculates rewards based on custom rules, making loyalty management effortless. Features :

It exposes single endpoint (Rest service) http://localhost:port/customers/{customerId}/rewards to fetch rewards based on customerId in the form of JSON.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
It calculate the reward points earned for each customer per month for last three months and total.
if customerId is not found in database then it will show error response with status code 404 and message - customerId Customer Id is not valid.
It also has Junit 5 and Mockito test case written to test the service class.