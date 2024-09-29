# Firebase Chess

![Chess Game GIF]()

An Android Multiplayer Chess game written in Kotlin. Allows users to play Chess on multiple devices while online, using Google's Cloud Firestore as the synchronizing database.

Cloud Firestore is a flexible, scalable database for mobile, web, and server development from Firebase and Google Cloud. Like Firebase Realtime Database, it keeps your data in sync across client apps through realtime listeners and offers offline support for mobile and web so you can build responsive apps that work regardless of network latency or Internet connectivity. Cloud Firestore also offers seamless integration with other Firebase and Google Cloud products, including Cloud Functions.

[More about Google Cloud Firestore](https://firebase.google.com/docs/firestore)

## Firestore Database

All documents in a collection have the same fields. The database contains the following collections - 

### 1. users
A document in this collection exists for each user signed-up in the app. Contains email id, password and username set by the user. A list of strings "friends" maintains email ids of all friends of the user. A list of strings "invites" contains email ids of all users that invite the user to play a game. "gameId" is a unique id for each game played on the app. This is generated in the "selectFriend" activity. player = "player1" for player requesting a challenge; player = "player2" for player accepting the challenge.

### 2. games
A document in this collection exists for each game initialised in the app. Fields black and white denote which player (player1/player2) plays as black and white. Field gameId contains firestore-generated unique id for the document. timer1 and timer2 contain time in milliseconds for each user on basis of the game mode selected by the player that generated the challenge. increment contains added time to each player after their move (for example 10,000ms if game mode was 15 | 10). fromPos and toPos are arrays of numbers containing initial and final coordinates (on an 8 x 8 chessboard) of a piece moved by a player. drawResignDocumentId is the firestore-generated ID for a document in the collection "drawResignDocument", which is explained further in this section. blackCheckmate and whiteCheckmate are flags used for purposes of terminating a game. turn stores player1/player2 denoting turn of the user who is to play a move.

### 3. draw_resign
Contains two variables draw and resign to control cases for drawing and resigning from games.
## Activities

Below is an overview and functionality presented by each of the activities in the app.

### 1. sign_up

Creates a new document in collection *users* with credentials set by the user.

### 2. login

Refers collection *users* to check if email ID matches with its corresponding password. Home Screen is launched if it does.

### 3. HomeScreen

Main menu of the game, contains various levels of onClickListeners, allows user to select from the game modes Rapid, Blitz and Bullet. Launches activities selectFriend and Profile.

### 4. selectFriend

Contains a listView with email IDs of friends of the user as the list items. Add friend button adds email id of another user to the friends list of the user. setOnItemClickListener of listView creates a unique document for the game in the collection draw_resign. It also creates a new document for the game in collection games and proceeds to launch the activity Game.

### 5. Invitations

Contains a snapshotListener to add in real-time email IDs of players that have sent a challenge. Launches activity Game.

### 6. Game

Contains all the mechanics of moving pieces along with their drawables on the board. onClickListeners are defined on various levels to show movable positions of a piece and to move to a particular position on the board. After a player makes a move, initial and final coordinates of the piece moved are updated in the common games document. A snapshotListener listens to these changes in real-time and controls the flow of execution based on the variable turn. Another snapshotListener listens in real-time draw requests/resign case and controls the termination of the game. Timer logic is maintained throughout the duration of the game, its starting and stopping is controlled by the snapshotListener. When a game ends, both players are directed back to the activity HomeScreen.

### 7. Profile

Allows a user to edit their account username or password, provided they pass their password check to ensure authenticity.
