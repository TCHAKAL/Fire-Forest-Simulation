# Fire-Forest-Simulation
The objective is to implement a simple algorithm for simulating the spread of a forest fire.
 
The forest is represented by a grid of dimensions l x h.
The simulation is therefore carried out step by step.

In the initial state, one or more trees are on fire.
If a tree is on fire at step t, then at step t+1:

· The fire goes out in this tree (the tree is filled with ash and can no longer burn)

· There is a probability p that the fire will spread to each of the 4 adjacent trees

The simulation stops when there are no more trees on fire
 

The dimensions of the forest, the position of the trees initially on fire and the probability of propagation are parameters of the input program.
