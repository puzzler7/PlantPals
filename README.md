# Plant Pals

## Inspiration
Within the last few months, a lot of us have felt the impact of social isolation in the pandemic, especially due to the virtual semester. As it turns out, loneliness and social isolation is a much larger issue, which is linked to increased risk for obesity and premature death. It also becomes more difficult to maintain friendships into adulthood due to changing responsibilities. We recently found the time to reflect on the relationships we’ve made in college, and the friends from high school that we’ve lost contact with. We realized that we neglected to check in with those people and really wanted to reconnect. 

## What it does
Our team wanted to create a tool that would encourage and remind users to stay in touch with their friends and loved ones. By helping users stay connected, we hoped to help them give and get support through friends. Maintaining these important relationships can improve one’s self confidence, coping abilities, and sense of purpose. 

To do this, the mobile app lets you grow and nurture a plant for each of your friends, and reminds you when you haven’t checked in with them for a while. You can water the plants to keep track of when you've interacted with your friends. To gamify the experience, repeated days of watering will help the plant grow to full bloom along with your friendship, while repeated weeks without water will cause the plant to wilt. You can customize the flowers to label them with your friends' names, nicknames, and even jot down any notes (e.g. reminder to ask Mary about the book she's reading)!

To help users navigate the app more easily, there is farmer NPC-type character named Bob that provides help articles and general advice in an interactive manner. He also pops up to alert with the user in certain situations, such as when they water a plant too often, want to shovel a plant, etc.


## How we built it
We built the Android app using Java in Android Studio. The user's plants are stored locally in a SQLite database to persist information across sessions. To make the app interface more user-friendly, front-end image assets like icons and backgrounds were custom designed in Photoshop and Illustrator. To create the NPC-type interactions, we wrote out multiple scripts and used a finite-state machine to represent them.


## Challenges we ran into
We ran into several challenges creating the finite state machine (FSM) to represent Bob's dialog trees, due to the many potential states and external factors affecting them. For example, the events that trigger Bob's popup are rather diverse, from clicking on various buttons to clicking on Bob himself to reaching a rate limit on an individual plant's watering per day. These all also potentially start the dialog at different parts of his dialog tree, each with different levels of access to the other parts of the tree. Then, on exiting Bob's popup, the FSM also needs to be reset appropriately. Combined, these nuances made integrating the FSM into the app challenging. We were able to work past this by thinking on a case-by-case basis.


## Accomplishments that we're proud of
We're very proud of the personable NPC-type character Bob, who is therapeutic and consistently supportive of the user. He has both dynamic and static components that help respond to specific actions, as well as advice and how-to-help when directly clicked on. We thought these interactions with Bob were especially important to our app's goal of helping the user maintain healthy relationships with friends and other loved ones, since he acts like a good friend!

## What we learned
We learned about user-centered design and how to improve the user interface to be more accessible and inviting. This included aspects such as sizing buttons to be easily clickable, designing backgrounds to provide a high enough contrast for readability, adding whitespace padding to reduce clutter, and choosing colors to help certain features stand out.

We also learned about data persistence for Android apps using SQLite. This included aspects such as creating the datatable schemas, prepopulating the data, performing database migrations when the schema changed, etc.

## What's next for Plant Pals
The app is currently fully functional. The next step of the journey is to make the app more user-friendly and gamified if possible. One specific feature we are excited about adding is animation for the plants, e.g. animating the watering of the plant with water drops and the growth of the plant from one stage to the next. Another idea we're thinking of is adding more options for types of flowers and user control over how often they would like to contact their friends before being notified, e.g. adding a cactus type for friends that are low-maintenance and don't need to be watered as often.

Also, while the team brainstormed current stages for plant growth and decay based on our own experiences, we would like to conduct long-term user interviews to update those as necessary. These interviews could also help us fine-tune user interactions, such as Bob's dialog, help messages, alert messages, etc.
