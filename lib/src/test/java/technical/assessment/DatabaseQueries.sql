--Q:Given the Contacts table below, write a SQL statement to get all contacts that have both email and phone values populated
select * from Contacts where isnull(email,'')!='' and isnull(phone,0)!=0

--Q:Given the Users table below, write a SQL statement to get the count of users per groupId
select count(*), groupId from Users group by groupId

--Q:Given the Customers and Orders table below, write a SQL statement to get the customerName, orderId and orderDate in a single dataset
select c.customerName, o.orderId , o.orderDate
from Customers c join Orders o
on c.id=o.customerId