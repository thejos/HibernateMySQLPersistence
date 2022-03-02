## PREREQUISITES

:black_small_square: MySQL Server&nbsp;version ***8.0 or later***<sup>&nbsp;_1_</sup> installed and running  
&emsp;&emsp;[Download](https://dev.mysql.com/downloads/mysql/) MySQL Server  
&emsp;&emsp;[Read/download](https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/) MySQL Installation Guide.

:black_small_square: A database the application communicates with  
&emsp;&emsp;To import required database into MySQL server run the [`sql\javacafe.sql`](sql/ "show the file location") SQL script.  
&emsp;&emsp;Learn how to import data/database in MySQL via [mysqlimport](https://dev.mysql.com/doc/refman/8.0/en/mysqlimport.html) client or via [MySQL Workbench](https://dev.mysql.com/doc/workbench/en/wb-admin-export-import-management.html).

:black_small_square: JDBC driver for MySQL  (MySQL Connector/J)<sup>&nbsp;_1_</sup>  
&emsp;&emsp;Download the connector from [here](http://dev.mysql.com/downloads/connector/j "MySQL homepage") or from [here](https://mvnrepository.com/artifact/mysql/mysql-connector-java "Maven repository").  
&emsp;&emsp;Learn [how to](https://www.geekinsta.com/how-to-connect-java-application-with-mysql-using-netbeans/#configuring-the-netbeans-project) add MySQL connector jar file to the current project  using NetBeans.  

:black_small_square: Hibernate ORM<sup>&nbsp;_2_</sup>  
&emsp;&emsp;Download [Hibernate-core JAR file](https://mvnrepository.com/artifact/org.hibernate) with its compile dependencies.  
&emsp;&emsp;Adding the Hibernate library to the project using NetBeans IDE [example](https://netbeans.apache.org/kb/docs/java/hibernate-java-se.html#_adding_hibernate_support_to_the_project).

<br>
<br>

&nbsp;<sup>_1_</sup> _<sub>During the application creation MySQL Community Server ***version 8.0.18*** and MySQL Connector/J ***version 8.0.19*** were used.<sub>_

&nbsp;<sup>_2_</sup> _<sub>Recommended Hibernate version is [4.3.1.Final](https://mvnrepository.com/artifact/org.hibernate/hibernate-core/4.3.1.Final). It is bundled with NetBeans IDE v 8.2.<sub>_
