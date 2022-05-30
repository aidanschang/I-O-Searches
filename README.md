# Input/Output Search App

## Overview
This I/O Search App is an interactive internface that a user can search any keywords within a CSV file that contains 65k Indiegogo Projects, then it saves the results into a new CSV file at user's preferred location. 
![Screen Shot 2022-05-29 at 7 11 16 PM](https://user-images.githubusercontent.com/84875731/170904685-68eefbf8-c6f8-44b8-a43f-e3d57788b940.png)

## Implementation
  ### Cleaning the Data
  While reading the CSV file, I noticed some of the raw data occupied multiple lines and throws index error during searches. By identifying the cause of   the errors, I filtered out the bad lines while iterating over the data.
  ![Screen Shot 2022-05-29 at 7 16 35 PM](https://user-images.githubusercontent.com/84875731/170905153-cfb431f1-9f29-4ea3-8f30-6436e5d7faa0.png)
  ### LinkedHashMap
  I am using the LinkedHashMap data structure to store search histories as the key is timestamp and values is the keyword of the search. Since the key of each object is almost likely to be unique, both insertion and lookup will be O(1).
![Screen Shot 2022-05-29 at 7 32 50 PM](https://user-images.githubusercontent.com/84875731/170906650-f2207303-892a-406c-a587-4b016c1b2179.png)


