This is a basic implementation of a flash card program.
The name is a very lazy mash up of:
Java - the computer language it is written in.
Hangugeo - the natural language I intend to be studied in this program.
Anki - the flashcard program I currently use and of whose feature list I have drawn inspiration.

I intend to later edit this Readme to be a functional one (viz. how does one use this program effectively?)
For now I shall use it as a roadmap of features to be implemented and broad design choices.

Features to implement:

A main menu that allows the user to select between :
	- studying from previously added cards
	- adding new cards
	- view data on cards
	
Studying cards:
	- By default quiz front and back.
	- On question scene have mutliple buttons (begin with don't know, and know; implement hard/okay/easy)
	- Implement spaced repetition: primitive form (ex. ask 1 day later, ask 2 days later, ask 3 days later ...)
	
	
Add cards:
	- front and back
	- allow for multiple decks?
	- deck class & card class where decks are collections of card objects
	

Data:
	- number of cards/decks
	- avg. cards added per day
	- avg. success rate
	- perhaps implement a "goals" input that offers comparisons to actual data.
	
	
Design:
- main loop
- controller to interact with user input
- Input validation class
- Card class
- Deck class
- FXML files for:
	- main scene
	- 3 other interaction scenes (study, add, data)
- a 'study session' class for efficient data computation
- I want decks, and data to be saved across sessions: these objects will be saved in files.