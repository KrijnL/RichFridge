# RichFridge
project-eventsearchengine created by GitHub Classroom

## What is it

This app allows the user to enter the ingredients they have in their fridge. It will perform a search and recommend recipes based on these ingredients

### How does it work

The user enters the ingredients in his fridge, these are stored in an SQL database on the phone.

An API is queried using the ingredients, and the results from the API are shown. When the user clicks on an item in the list of recipes, they are taken to a more detailed view of the recipe, but the API doesn't provide the entire recipe. Only a link to the webpage where the recipe can be found.
If the user is happy with a recipe, they can click on a button to visit the webpage. Recipe's can be favorited for future use.

```
We use the https://www.food2fork.com/about/api API to get recipes
```

## Built With

* [Android Studio](https://developer.android.com/studio/) - The IDE used (Java)
* [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room) - Database management


## Authors

* Krijn Luijendijk
* Thomas Desaranno
