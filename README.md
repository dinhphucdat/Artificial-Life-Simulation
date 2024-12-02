# Artificial-Life-Simulation
The Population class is how we represent your virtual petri dish. It keeps track of how long the
experiment has been running and what sort of environment your digital bacteria are attempting
to live in. It also has methods for collecting statistical information about our experiment. The
Population class will hold a list of all of our organisms and at every “time point” or tick it will
loop over those organisms and allow them to perform one action, represented by an update
method.
The Organism class is how we represent a single individual organism. This project is one where
object-oriented programming is a powerful paradigm, because we will have hundreds of
instances of Organisms, all with different values for their variables. The Organism class holds
methods for determining what each instance is going to do at any given time (in our project,
cooperate or not) and instructions for reproduction if that instance has gotten enough
resources. Each organism has an “energy level” that measures how much energy that organism
has currently. When the organism has enough energy, it is able to reproduce, which uses up all
the energy it had saved. You will give each organism one new energy at each update.