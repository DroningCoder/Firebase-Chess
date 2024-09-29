# Firebase Chess ♟️

Video demonstration of the App along with the firestore database updating its collections in real time -

![Chess Game GIF](https://github.com/DroningCoder/Firebase-Chess/blob/main/VideoDemonstration.gif?raw=true)

An Android Multiplayer Chess game written in **Kotlin**. Users can play Chess on multiple devices while online, running Google's Cloud Firestore as the synchronizing database in the backend.

### About Cloud Firestore ☁️

Cloud Firestore is a flexible, scalable database for mobile, web, and server development from Firebase and Google Cloud. It keeps data in sync across client apps through real-time listeners and offers offline support for mobile and web, allowing to build responsive apps that work regardless of network latency or Internet connectivity. Cloud Firestore seamlessly integrates with other Firebase and Google Cloud products, including Cloud Functions.

[Learn more about Google Cloud Firestore](https://firebase.google.com/docs/firestore)

## Firestore Database 📚

I have organized the database into 3 collections, all documents within a collection have the same fields. The collections include:

### 1. Users 👤
This collection contains a document for each user who signs up in the app. Each document includes:
- **Email ID**
- **Password**
- **Username**
- **Friends**: A list of strings storing the email IDs of all friends.
- **Invites**: A list of strings containing email IDs of users who invite the user to play a game.
- **gameId**: A unique ID for each game played on the app, generated in the `selectFriend` activity. 
- **player**: "player1" for the player requesting a challenge; "player2" for the player accepting the challenge.

### 2. Games ♟️
This collection includes a document for each game initialized in the app, with the following fields:
- **Black/White**: Denotes which player (player1/player2) plays as black and white.
- **gameId**: Randomly generated unique ID for the document.
- **timer1/timer2**: Contains time in milliseconds for each user based on the selected game mode.
- **increment**: Added time to each player after their move (e.g., 10,000 ms if game mode was 15 | 10).
- **fromPos/toPos**: Arrays of numbers containing initial and final coordinates (on an 8 x 8 chessboard) of a piece moved by a player.
- **drawResignDocumentId**: Firestore-generated ID for a document in the `draw_resign` collection.
- **blackCheckmate/whiteCheckmate**: Flags used to indicate game termination.
- **turn**: Stores player1/player2, indicating whose turn it is to play.

### 3. Draw/Resign 🏳️
This collection contains two variables, `draw` and `resign`, to manage scenarios for drawing or resigning from games.

## Activities 📱

An overview of the functionalities offered by each activity in the app:

### 1. Sign Up 📝
Creates a new document in the `users` collection with the credentials set by the user.

### 2. Login 🔑
Checks the `users` collection to verify the email ID and corresponding password. Launches the activity `HomeScreen` upon a successful match.

### 3. Home Screen 🏠
The main menu of the game allows users to select from various game modes: **Rapid, Blitz, and Bullet**. It also links to the `selectFriend` and `Profile` activities.

### 4. Select Friend 👥
Displays a `listView` with email IDs of the user’s friends. The `Add Friend` button allows users to add another email ID to their friends list. The `setOnItemClickListener` creates a unique document for the game in the `draw_resign` collection and initializes a new document for the game in the `games` collection, launching the `Game` activity.

### 5. Invitations 📧
Utilizes a snapshot listener to add email IDs of players who have sent a challenge in real-time. Launches the `Game` activity.

### 6. Game 🎮
Handles all the mechanics of moving pieces along with their drawables on the board. onClickListeners  for each of the 64 cells on the board display movable positions of a piece, and after a player makes a move, the initial and final coordinates are updated in the shared `games` document. Snapshot listeners (one listening to the `games` document, the other to the `draw_resign` document) monitor real-time changes and control the game flow based on the current turn. The timer logic is maintained throughout the game duration, managing start and stop actions. When the game ends, both players are directed back to the activity `HomeScreen`.

### 7. Profile 👤
Allows users to edit their account username or password, contingent on a password verification check for authenticity.

## How to Clone this Repository

1. **Copy the Repository URL**:
   - On the top of this page, click on the green **Code** button and copy the HTTPS or SSH URL.

2. **Clone the Repository**:
   - Navigate to the directory in which you want to clone the app.
   - Use the `git clone` command followed by the copied URL.

3. **Navigate into the Cloned Repository**:
   - After cloning, navigate into the newly created directory using `cd` command.

4. **Open the Project in Android Studio**:
   - Open Android Studio, click "Open an existing Android Studio project," and select the cloned repository directory.

Feel free to explore the code and contribute to the project.

---
⚠️ Code of the piece move logic is functional but contains a few bugs, I'm working to fix the bugs. If you have any suggestions or issues, please open an issue or submit a pull request.
