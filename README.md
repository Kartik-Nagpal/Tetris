# Tetris: A Java game I made with my friend Nathan Read.

## Downloads
For using the downloaded project files, open a Java IDE like Eclipse and go to File>Import...
Then, you need to browse to where you've downloaded this project, and select the folder you have the files in.
You should then be able to "Finish" the process, and have the project show up in your explorer.

If you have problems installing or with the program, please open an "Issue"

## The Project:
In remembrance of the great game of Tetris, I've made a full java game as an ode. I began with the design process, which allowed me to work through the process with the use of a two-dimensional integer array to show what the pieces look like and figure out the basic game logic. In this process I came across a large obstacle in the process of rotating pieces, as this required me to create a 3x3 matrix around the piece, and apply a rotation transformation matrix, so that the piece can be rotated without the having to make custom solutions for all the piece types.

I then adapted the text to a basic graphical setup from which I could draw the array-map for later matrix operation needed to translate and rotate the pieces. In the GIF below I'm testing out the program with the text version of the game on the left and the graphical framework on the right.

![GIF of the Text-Based Game and Graphical Framework](https://github.com/Kartik-Nagpal/Tetris/blob/master/Textris.gif)
Text Version(left) and Graphical Framework Interface(right)

Then I went on to fully implement the graphical interface for the user to see, and the key listeners to take in user input like the arrow keys and to react to that input. Thus, I got a working model, where once the piece got to the "bottom" it turned red, so that the user knows to now focus on the new blue piece. Since the text messages in the console for this version of the program were for troubleshooting and benchmarking processes, I only included the graphical interface, as I moved the pieces.

![GIF of the Graphical User Interface in Action](https://github.com/Kartik-Nagpal/Tetris/blob/master/Tetris.gif)

Over the course of this activity I got to go through the entire game design and production process. I came across a few pitfalls and obstacles and had to plan and build around them. Now that I'm done with the program, I have uploaded the project files onto this GitHub Repository, and included the .jar file such that you can download it and run it quickly, as well as including the above GIFs and installation instructions for the project files.