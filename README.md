# I-O-Search

## Overview
This I/O Search program provides an interactive internface that a user can search any keywords within a CSV file which contains 65k Indiegogo Projects then saves the results into a new CSV file at user's preferred location. 

## Implementation
  * Some of the raw data occupied multiple lines and may throw index error during searches. 

   ![image](https://user-images.githubusercontent.com/84875731/149721717-f25bde03-1dd0-44cc-9f5a-4030ca228a94.png)
    
  * To ensure each sorting algorithm are getting the same set of random numbers in each increment, deep copies of random integer array were implemented throughout.

  ![image](https://user-images.githubusercontent.com/84875731/149714345-f8596480-f49d-4b20-bc86-4ebd6783c230.png)

  * The amount of time each algorithms used was measured by subtracting the ending System.currentTimeMillis() by starting System.currentTimeMillis(). As result, I am able to obtain the amount of time each sorting algorithms spent and be able to visualize each algorithms' time complexities through the graph.

## Observation

1. While insertionSort runs at O(n^2), the rest three are running at O(nlogn).
2. I have noticed that there was an unusual spike on the mergeSort during the second round(20,000). After extensive research on what might have caused the spike, I tried to put a  warmup dummy at the very beginning of the program, and that spike reduced from an average of 17 milliseconds to an average of 10 milliseconds.
