# <u>Team-4-Project-2 READ ME</u>

Created By: 
David Brumagin,
Connor Campi,
Isaiah Lee,
Brian Mokua, and
Anthony Padron 

Start [Potlukk 1.0](https://main.d3pbgde6ddw94g.amplifyapp.com/)



When launched, creates a potluck application page where the user can create and edit values within 3 categories

### <u>Users</u>
As a user you can:
* View public potlucks, even if the User is not logged in.
* Create a User Account if you are unregistered, a create account requires:
  * Username
  * Password
* Login to a User Account.
  * If the credentials are incorrect, you are provided an error and told to try again.
  * If the credentials are correct, you are granted additional options for creating potlucks and editing items within them.

 

### <u>Potlucks</u>
Created Potlucks require:
* A provided date and time that must be after the current time.
* A logged in user
* Selecting between Public/Private, a private Potluck will not be seen on the main page instead, the owner must provide a link that when clicked, takes you to the potluck item list.
  * The owner is provided a link regardless of whether the potluck is public or private.

Also Created:
- PotluckId (Primary Key)
- OwnerId (Foreign Key: User(Username))


The User can:
* Edit a Potluck Date/Time unless within an hour of an existing potluck if the User is the owner.
* Have the Ability to delete a potluck if the user is creator of said potluck.




### <u>Items</u>
Created Items require:
* Description/Name of Item
* Status of
  * Wanted 
  * Needed
  * Fulfilled
* An Existing Potluck/PotluckId

Also Created:
* ItemId (Primary Key)
* PotluckId (Foreign Key: Potluck(id))


The User Can:
* Create Items for potlucks, even if the user is not logged into an account/not the potluck creator.
* If a guest/not logged in, the user provides a name for the Supplier slot.
* If the user is logged in, the supplier slot is filled in with the user's username.
* Remove items from potlucks if the User is the owner.



