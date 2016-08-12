## DietManager
Diet Manager is an application that can be used to keep track of your daily calorie intake.

In order for application to work correctly you need to put external JAR libraries to /lib/ folder (You can get them from link below).
Also it is needed to establish database connection and in text file located at path "/data/database.txt" provide informations required to connect to that database.
Example of how file should be structured is included within this repository.
Successive lines correspond to host, database name, login and password.
If you have database with no password established, inside database.txt file last line responsible for holding password should consist of word: null

Features:
* Create product entry holding information about size of portion, amount of calories, grams of carbs, proteins and fats in that portion. Aplication then stores these numbers calculated per gram of whole product
* Edit information about product entry, or remove whole entry from database
* Browse through product database to see information you are looking for, and edit entries you want to
* Choose date, for which you want to add consumed meal entry, using spinner editor or built in calendar using JXDatePicker library
* Two buttons to set date to today or yesterday date
* Possibility to provide your daily calorie intake limit and customize how many percentage of your daily intake you want to acquire from certain nutrient
* Add meal entry, choose how many portions of product you consumed, and application will calculate amount of calories and nutrients you have consumed, and if you provided your daily limit, application will show how far or beyond this limit you are
* Delete and edit meal entries.

*You can download external jar libraries and runnable JAR file from: https://www.dropbox.com/sh/i1udtizrwe45zjq/AADSK1zeXOt3PaJa-f__A1nJa?dl=0*

*This project have been created during June and July 2016.*
