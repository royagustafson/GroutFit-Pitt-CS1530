# Milestone 3 - Groutfit
Group 5

## 1. Product Overview and Summary
This project will develop a competitive online clothing vending application. Features like security and transactions will be barebones, as we won’t be making any real sales. All other aspects, such as user interface, database searching, saving items, and building shopping carts, will act like a fully implemented website. We aim to compete with functionality and user experience, while, at the very least, laying the foundation for proper web security to be implemented in the event that our website is put into production.

Many online vendors exhibit some of the features that we will support. Things like customer logins, shopping carts, and wish lists are standard. Many websites also allow customers to save default clothing sizes. These features are unnecessary to purchase items, but can help facilitate long term consumer loyalty. There will be less overhead for a long term customer to go from “I think I want a new sweatshirt” to “A receipt and shipping number has been sent to your email.”

Furthermore, any online vendor would include features like view by category, filter by price range, or by size. Likewise, most vendors support similar features in a text based search. This allows for customers to be able to find what they want to find, no matter how specific they choose to be. It also ensures that a customer will be able to “window shop” through items that are more closely aligned with their interests. They wouldn’t have to manually click on each item to see if it was available in their size.

We would also like to include features that we haven’t seen before. Features like our virtual mannequin. This would enable very basic visualization of customer chosen outfits, without having to order items and try them on, or to see them on a model. This feature enables us to save overhead investment in both fashion model and photographer time. Because of this, we experience diminished losses if we decide to remove a clothing item from stock. This also enables more dynamic merchandising, so that we may more readily adapt to market trends.

Last, we’d like to add a basic clothing feed, in the same style as original Twitter. To the same extent that VSCO is attached to Instagram, or Imgur is to Reddit, we’d like our clothing feed to support primary social media, like Facebook, Twitter, and Instagram. It will enable posting and sharing of outfits, while still remaining simple enough that it won’t eat up development time we could put towards a more seamless user experience.

Our product will exhibit our application of computer science knowledge to the business world. There’s a large gap between being able to code Sudoku in the command line to being able to present a company to the world via the internet. Our product will demonstrate that gap isn’t so daunting as it may initially appear.
## 2. Information Description
In the following sections, we will go into detail about the user interface by providing a preliminary User Manual. The User Manual will give an overview of the product, describing what the product is for and some of its main functions. From there the manual will detail ways to get started with the product, including logging in, general help with the product, and sample runs of using the site. After this introduction, modes of operation will describe how a user may operate the system, any data input that is required of the user, and various examples of interacting with the system.
### 2.1 User Interface (A Preliminary User Manual)
**User Manual**
#### 1. Product Overview
Groutfit is an e-commerce website marketing clothing, with a focus on clothing as outfit units and specializing in outfits manufactured only in various shades of grey. This product will streamline the online clothing process allowing shoppers to customize outfit units and create profiles where they will be able to save outfits and also share them on an output feed.

This product’s main functions will be to display clothing items sorted by category, size, price etc. Along with displaying clothing items, the user will be able to create a profile and login to the site, where saved user data will be available to them. The site will have a cart and wishlist feature, where saved items and outfits will be stored in the wishlist and can be easily added to the cart to checkout when the user is ready to make a purchase. Additional features will include the ability to create specific outfits made up of individual clothing pieces and be shown to the user on a digital mannequin. Once an outfit has been created, the user will be able to save the all items to the user’s wishlist or add them to their cart for checkout. Lastly, outfits will be able to be shared on a twitter-like output feed where other user’s can view and open different outfits.
#### 2. Getting started
How to log in to the system, online help and support, and sample runs are included below.
##### 2.1 Log in
The user will be able to login to the system using a standard login prompt. The user will be required for this step to have their username and password memorized where each of these items will be required for access to their account. In order to protect against online attacks, there will be a cooldown in place so that after 5 failed attempts the user will be required to wait a certain amount of time to make another login attempt. The time will increase exponentially with each failed attempt. If a lock on an account is activated, the user will be prompted to email support to help with login trouble. Additionally to provide backend security for the login system, user passwords will not be stored in plaintext but will be hashed and salted.
##### 2.2 Help mode
A general help button will be available that will serve an html page where answers to general help questions will be displayed relating to the login screen. A support email will also be provided for customers to ask any outlying questions not covered in the provided help page or to deal with any account issues. The support email will also take bug reports or any other general problems users would like to report.
##### 2.3 Sample Runs
*Login*

To login to Groutfit, navigate to the home page and click on the button labeled “login” which will bring up a login screen. The login screen will prompt for your username and password which you will enter in the text boxes and click the button to login. If you have entered valid credentials and the login is successful, you will be brought to the home page where you can start browsing clothing. More detailed steps below:
1. Navigate to the Groutfit home page as pictured below. While on this screen, move your mouse to the upper right corner and click on the blue button labeled “login”, which is located to the right of “cart”.
2. This will bring up the login screen shown below, while on this page click into the textbox labeled “username” and type in your username. Once complete, repeat this process for the textbox labeled “password,” and type in your password that corresponds with the above username. Once this is complete click the large blue login button to complete your login.

*Purchase a Shirt*

In order to purchase an item, navigate to the Groutfit homepage and select the category filter and select “shirts”. From this screen click on any individual shirt item where its specific info page will come up. Select a size and color and then click add to cart(coming soon). You may now click the cart button in the upper right corner to show you the current items in your cart. From the cart screen you may click “checkout” which will complete your order. More detailed steps below:
1. Navigate to the Groutfit homepage as shown below.
2. Once in this page, click on the blue down arrow on the top right of the screen next to the word “Filter.” This will bring up a drop down menu allowing you to select a category of items you want to be shown. Select “Shirts”.
3. The screen will now be showing a list of items which are all shirts. Select one of the shirts by clicking the corresponding “Add to Cart” button to the right of the pictured item.
4. Once the item is added into cart, open up the cart by selecting the light blue button in the top right labeled “Cart”. This will bring up the cart screen with the shirt item you previously added. You may now click the large blue “Checkout” button at the bottom to complete your purchase.
#### 3. Modes of Operation
The user will be interacting with the system through html and javascript laced webpages where various dialogs and buttons will be the primary form of interaction. User’s will not be entering any manual commands through any sort of command-line interface but rather logging in and navigating the site through GUI pages. Pages such as the login will require the user to type in account information into dialog boxes. When making a purchase, the user will also have to enter data for checking out. When browsing clothing items and constructing outfits, the user will be able to navigate the site through the back and forward arrows as well clicking on hyper-linked text and images.
### 2.2 High Level Data Flow Diagram
![alt text](https://github.com/royagustafson/GroutFit-Pitt-CS1530/dfd.png "Data Flow Diagram")
### 2.3 Data Structure (or object) Representation
(What data elements are in the databases or objects?)
### 2.4 Data Elements (or objects) Dictionary
(Compile all data elements into a dictionary)
## 3. Functional Description
### 3.1 Functions
*See IC Cards in folder*
### 3.2 Processing Narrative
Displaying clothing tiles allows the user to view all clothing in no particular order and without any filtering. The next function will allow the user to sort the clothing items according to some criteria that the user will decide upon. This criteria may include size, price, type, etc. Based on these selected criteria, the site will display the items only pertaining to this criteria.

When an item is added to the cart, the number of items inside the cart needs to reflect the actual number. The next function will allow this to occur. There are functions that will allow the adding and removing of an item to and from the shopping cart. Clothing items may also be added or removed from the wishlist, in the next functions.

When a user creates a profile, he or she will be able to save their sizes, and those sizes will be saved in the database. If the user is already logged in, the act of logging out will remove all their information from the current site. Logging in to the site will allow the user to enter a username and password and this function will identify the user in order to allow the user to access further features of the site. The next function will allow a new user to create a new profile, asking for creation of a username and password.

Users will be able to create outfits and a function of the site will allow the outfit to be displayed on a virtual model. When the outfit is complete, the user may share the outfit to the in-house social media feed. Outfits may be saved to the wishlist or cart, and outfits may also be removed and deleted from the cart or wishlist.
### 3.3 Design Constraints
Groutfit is a 95% complete e-commerce website, selling clothing items only manufactured in shades of grey. However, the functionality of the website stops right before allowing a user to enter a valid credit card and actually purchase the items.
### 3.4 Diagrams
(There should be a detailed data flow diagram for each function)
## 4. Performance Requirements
## 5. Exception Conditions/Exception Handling
## 6. Implementation Priorities
## 7. Foreseeable Modifications and Enhancements
## 8. Acceptance Criteria
Functional and performance tests
Documentation standards
## 9. Sources of Information
Our sources of information will come from a variety of sources to help us solve a variety of problems. I've attempted to divide these problems into categories.

### Front End Design Goals
We will use the following sales websites as black box inspiration. Not the source code, but the higher level user interface concepts and techniques. Researching websites like these will help us to create a more competitive website. 

* Amazon - https://www.amazon.com/
* ASOS - http://www.asos.com/
* Nordstrom - https://shop.nordstrom.com/


### Front End Implementation
This following will help us to achieve our design goals using Bootstrap. This will provide general guides for getting started, as well as more in depth documentation to help us implement specific user interactivity elements we notice in the above websites. 

* BootStrap Documentation - [https://getbootstrap.com/](https://getbootstrap.com/docs/4.0/getting-started/introduction/)
* w3schools Bootstrap Guide -[https://www.w3schools.com/](https://www.w3schools.com/bootstrap/default.asp)


### Spark Usage
These guides will help us to create a Rest API using Spark Framework. Baeldung will help us to get started and configured, and the spark documentation will help us to incorporate more of sparks features that we deem useful. 

* Spark Java Documentation - [http://sparkjava.com/](http://sparkjava.com/documentation)
* Baeldung Spark Tutorial - [http://www.baeldung.com/](http://www.baeldung.com/spark-framework-rest-api)


### Database Design
The documentation will help us to use the ORM to interface with our database. TutorialsPoint has excellent documentation on hibernate's features. The tutorial will help us to start thinking about what attributes of clothing we would need to store in a real scenario. 

* Hibernate ORM/Search Documentation - [http://hibernate.org/](http://hibernate.org/orm)
* TutorialsPoint Hibernate Guide - [https://www.tutorialspoint.com/](https://www.tutorialspoint.com/hibernate/index.htm)
* Clothing Database Tutorial - [http://www.vertabelo.com/](http://www.vertabelo.com/blog/technical-articles/database-model-for-an-online-store)

## 10. Revision History
