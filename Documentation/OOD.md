Object Oriented Design: 

User:
	JavaScript Methods
		validateUser()
			/*
			Username, Password values are recieved through document.forms[form][var].value
			Validated by checking against Database
			Show alert if login is unsuccessful
			render homepage if successful
			*/
		registerUser()
			/*
			Username, Password values are recieved through document.forms[form][var].value
			Check against database for unique username
			Check that password has >8 characters
			add user to users table in database
			*/
		loginIconControl(isLoggedIn, context)
			/*
			whenever the page is refreshed, this method will choose whether to show both Sign Up and Login buttons, or neither
			this method will also control the rendiring of the login and register buttons on the login page
			*/
		renderClothingList(filterType)
			/*
			given a filter type, this method will generate a query, which will return a JSONified list of clothing items. 
			clothing items will be rendered by iterating through the list of JSON elements, and using document.createElement() and document.body.appendChild()
			Future Releases will have the capability of rendering at most X number of items at a time, and after all rendered items are scrolled through, the user can click a button to render another X number of items
			*/
		renderCart(user)
			*/
			User carts will be stored in the browser's Session Storage in JSON. 
			Cart items will be rendered by iterating through the JSON elements, using document.createElement() and document.body.appendChild()
			*/
		checkout()
			*/
			Clear User's cart
			Add order to Database table of Completed Orders
			This application will not store data about the shipping process.. When an order is added, it is immediately considered completed
			*/


Server:

Database:
