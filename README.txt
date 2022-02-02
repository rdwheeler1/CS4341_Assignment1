Running the program through the Eclipse/IntelliJ IDE:
	1. Open Main.java
	2. Click the Run setting at the top of the IDE 
	3. Select "Run Configurations..." from the drop down
	4. Move to the "Arguments" tab in the Run Configurations window
	5. Under __ write the following, with only a space separating the arguments:
	
		Maps/Map[#1].txt [#2]
		
			where 	- [#1] is replaced with a number 1-10, denoting the selected map
					- [#2] is replaced with a number 1-6, denoting the selected heuristic
					
			If the numbers are out of bounds or no arguments are inputted, the program will terminate with an error.
	6. Click "Apply" at the bottom of the Run Configurations window, then click "Run"
	
	
Running the program through terminal:
	1. Make sure you're in the most top-level of the project directory in the terminal
	2. Type:
	
		javac src/Main.java
	
	3. Press 'enter' to compile the code, then type: 
		
		java src/Main Maps/Map[#1].txt [#2]
		
			where 	- [#1] is replaced with a number 1-10, denoting the selected map
					- [#2] is replaced with a number 1-6, denoting the selected heuristic
				
			If the numbers are out of bounds or no arguments are inputted, the program will terminate with an error.
	4. Press 'enter' to run the program.