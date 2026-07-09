Simple Shopping System
Project Overview
The Simple Shopping System is a Java Object-Oriented Programming (OOP) project developed as part of a first-year Computer Science course. The system provides a simple desktop application where users can manage products, add items to a shopping cart, and simulate an online shopping experience.
The project demonstrates the core principles of Object-Oriented Programming together with Java Swing for the graphical user interface.
Features
Add new products
View all available products
Delete products
Search products (Coming Soon)
Update products (Coming Soon)
Add products to shopping cart (Coming Soon)
View shopping cart (Coming Soon)
Checkout and create orders (Coming Soon)
Save data using File Handling (Coming Soon)
Store and retrieve data from MySQL using JDBC (Coming Soon)
Technologies Used
Java
Java Swing
Object-Oriented Programming (OOP)
Java Collections (ArrayList)
File Handling
JDBC (MySQL)
Visual Studio Code
Git & GitHub
Project Structure
SimpleShoppingSystem
│
├── src
│   ├── model
│   │   ├── AppData.java
│   │   ├── Category.java
│   │   ├── Customer.java
│   │   ├── Person.java
│   │   ├── Product.java
│   │   ├── Store.java
│   │   └── StoreManager.java
│   │
│   ├── ui
│   │   ├── HomeFrame.java
│   │   ├── ProductFrame.java
│   │   ├── CartFrame.java
│   │   └── OrderFrame.java
│   │
│   └── Main.java
│
└── README.md
Object-Oriented Programming Concepts Demonstrated
1. Classes and Objects
The project models real-world entities using classes such as:
Product
Category
Customer
Store
Shopping Cart
Order
Objects are created from these classes during program execution.
2. Encapsulation
Class attributes are declared as private and accessed through public getter and setter methods to protect object data.
3. Constructors
The project uses:
Default constructors
Parameterized constructors
Constructor overloading
Constructor chaining using this()
4. Static Members
Static variables and methods are used to keep track of shared information such as the total number of products.
5. Inheritance
The project demonstrates inheritance using the following hierarchy:
Person
├── Customer
└── StoreManager
6. Polymorphism
Method overriding is implemented by redefining methods inherited from the Person class.
Examples include:
displayInfo()
The project also demonstrates:
Upcasting
Downcasting
Method overloading
7. Exception Handling
Exception handling is used to validate user input and prevent application crashes.
The project uses:
try
catch
finally
Custom Exceptions (planned)
8. File Handling
The application is designed to save shopping and order information using Java File I/O.
9. Database Integration (JDBC)
The project will use MySQL with JDBC to perform:
Insert
Update
Delete
Select
operations using PreparedStatement.
How to Run the Project
Clone the repository.
git clone https://github.com/yourusername/SimpleShoppingSystem.git
Open the project in Visual Studio Code.
Install the Extension Pack for Java if it is not already installed.
Open Main.java.
Click Run or execute:
javac Main.java
java Main
Future Improvements
Product search
Product update
Shopping cart functionality
Checkout process
Order history
File persistence
MySQL integration
Improved user interface
Sales reports
Author
Henon Mengistu
Computer Science Student
License
This project was developed for educational purposes as an Object-Oriented Programming course project.
