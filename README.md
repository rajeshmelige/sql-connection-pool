# SQL CONNECTION POOL POC

This poc demonstrates the behaviour of SQL statements execution with and without connection pool.

# Installation
- docker pull mysql
- docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=connpool -e MYSQL_USER=pool -e MYSQL_PASSWORD=pool -p 3306:3306 -d mysql

Run these two methods in Main class individually
- executeWithRawDbConnection();
- executeWithConnectionPool();

**PS: Play around with the number of threads and connection pool size**