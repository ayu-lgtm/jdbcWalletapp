
# JDBC Wallet

Created a digital wallet app similar to Paytm, setting up a modern system to handle large user loads. Used Spring Boot and Kafka to build the backbone of the app, making sure data is managed effectively. Made the app able to communicate instantly and grow seamlessly with Kafka.







## Services

1. **User Service**
2. **Transaction Service**
3. **Wallet Service**
4. **Notification Service**
## API Requirements

#### User Service
- `POST /users`: Create Account
- `GET /users/{userId}`: Get Details
- `PUT /users/{userId}`: Update Account
- `DELETE /users/{userId}`: Delete Account

#### Wallet Service
- `GET /wallets/{userId}`: Get Balance
- `POST /wallets`: Create Wallet
- `PUT /wallets/{walletId}`: Update Wallet
- `PUT /wallets/{walletId}/config`: Update Configuration

#### Transaction Service
- `POST /transactions`: Initiate Transaction
- `POST /transactions/recharge`: Recharge / Bill Payment
- `GET /transactions/{transactionId}`: Get Transaction Details
- `GET /transactions/history/{userId}`: Get History

#### Notification Service
- `POST /notifications/email`: Send Mail





## Entities 

#### User
- `userId`
- `userFullName` - Not Null, Unique
- `userMobileNo` - Not Null, Unique, Validated
- `userEmailId` - Not Null, Unique, Validated
- `userAddress`
- `userPan`
- `createdDate`
- `updatedDate`

#### Wallet
- `walletId`
- `userId`
- `balance`
- `dailyLimit`
- `dailyTransactionLimit`
- `createdDate`
- `updatedDate`

#### Transaction
- `transactionId`
- `walletId`
- `senderId`
- `receiverId`
- `amount` - > 0
- `paymentStatus` - SUCCESS, PENDING, FAILED
- `remark`
- `paymentDate`

#### UserAuthentication
- `userName`
- `passWord`
- `userId`
## Tools and Technologies
- **Programming Languages**: Java
- **Frameworks**: Spring Boot
- **Message Broker**: Apache Kafka
- **Database**: MySQL
- **Build Tool**: Maven
## Servers

- **Application Server**: Apache Tomcat
- **Message Broker Server**: Kafka Server
- **Database Server**: MySQL Server
## Authors

- [Ayush Rastogi](https://github.com/ayu-lgtm)

