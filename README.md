# Inventory

## About

A project adapted from a school project. Original project was done with 7 other team members.

The scope of this project is half the scope of the original assignment.
The frontend was rewritten using only built-in styles from the Bootstrap framework to place emphasis on the backend code.

The use cases are mostly CRUD operations.
The content is meant to be relatively simple so that the use of the Spring Framework is brought to the foreground.

## Explanation of project idea

This project shows an inventory management system for a car repair shop.
It is centered on the changes in product quantity, named as "stock transactions" in the code and user interface.

There are two types of changes in quantity - positive and negative.
Positive changes in quantity occurs when products are being restocked.
Negative changes in quantity occur when items are being used (withdrawn from the inventory) or returned to suppliers.
When the quantity of a product drops below the quantity where it needs to be restocked,
an email is sent to notify the need to restock.

Stock transactions are annotated with the time the entries are made and the user responsible for them.
The annotations are stored in a data store separate from the table of the stock transactions.

Changes in quantity due to usage are grouped based on case records.
A contrived example of a case record is where a customer comes in with his car on one occasion,
and asked for his windscreen and one of his wheels to be replaced.
In this case record, there are two stock transations, where a windscreen and wheel are withdrawn from the inventory.

Changes in product quantity uses the product ID to identify the product involved.
Details of the products are stored in a Products table.

The system is open for registered users and is not accessible by the public.
There are two types of users - mechanics and admins.
Mechanics and admins can carry out the same operations, except that only admins can create, update and delete products.

## List of technical skills showcased

- Java SE
- Spring Framework MVC
- SQL and MySQL
- JPA, Hibernate
- HTML/CSS, Thymeleaf

## Output

<center><img style="width: 600px; height: 480px; margin: 2%;" src="./media/Inventory.gif"></center>

The video recording above shows the user interface of the web application, CRUD operations on the MySQL tables involved and email notification.  
The fullpath in this repo to the video of the output is `./media/Inventory.gif`.  
You can download the MP4 version of the video at `./media/Inventory.mp4`.
