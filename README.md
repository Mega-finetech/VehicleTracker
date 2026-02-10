VehicleTracker

A simple Java console application for tracking vehicles in real-time using threads.

Overview
This project simulates a fleet of vehicles (like taxis, delivery trucks, or emergency vehicles) whose locations are updated concurrently.
Multiple threads update vehicle positions while other threads read and display these locations on the console. 
The main goal is to practice Java concurrency and understand how to safely manage shared data using synchronization.

Features
Tracks multiple vehicles with unique IDs.
Allows concurrent updates of vehicle locations.
Ensures thread-safe access to shared data to prevent errors.
Provides consistent snapshots of locations even during simultaneous updates.
Prints vehicle locations to the console so you can see how threads interact.

How It Works
VehicleTracker manages a map of vehicles and their locations.
All access to the map is synchronized to ensure thread safety.
Reading methods return copies of the data so that snapshots remain consistent.
Multiple threads can update and read vehicle locations at the same time without causing errors.

Future Improvements
Replace MutablePoint with immutable objects for better performance.
Use ConcurrentHashMap for lock-free reads.
Add a simple GUI using JavaFX to visualize vehicle movement.
Simulate real GPS data or integrate with a networked system.
