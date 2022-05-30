# Input/Output Search App

## Overview
This I/O Search App is an interactive interface where users can search keywords within a CSV file containing 65k Indiegogo Projects; then, it saves the results into a new CSV file at the user's preferred location. 
![Screen Shot 2022-05-29 at 7 11 16 PM](https://user-images.githubusercontent.com/84875731/170904685-68eefbf8-c6f8-44b8-a43f-e3d57788b940.png)

## Implementation
  ### Cleaning the Data
  While reading the CSV file, I noticed some of the raw data occupied multiple lines and threw index errors during searches. By identifying the cause of the errors, I filtered out the bad lines while iterating over the data.
  ![Screen Shot 2022-05-29 at 7 16 35 PM](https://user-images.githubusercontent.com/84875731/170905153-cfb431f1-9f29-4ea3-8f30-6436e5d7faa0.png)
  ### LinkedHashMap
  I am using the LinkedHashMap data structure to store search histories as the key is a timestamp, and values are the search's keyword. Since the key of each object is almost likely to be unique, both insertion and lookup will be O(1). LinkedHashMap also provides in-order insertion, which means that we will access the data in the order of its insertion time when iterating through, which is an essential feature when viewing the search history.
![Screen Shot 2022-05-29 at 7 32 50 PM](https://user-images.githubusercontent.com/84875731/170906650-f2207303-892a-406c-a587-4b016c1b2179.png)
  ### Search Counts
  I used a simple iterator on the LinkedHashMap to count numbers of each keywords being searched. 
![Screen Shot 2022-05-29 at 8 47 38 PM](https://user-images.githubusercontent.com/84875731/170913681-6986f4f9-60bc-492b-8a63-6671177226cc.png)
  
  Results shown below:
  ![Screen Shot 2022-05-29 at 8 45 56 PM](https://user-images.githubusercontent.com/84875731/170913526-65852ee9-f891-44c4-bfcd-a2490bcecb0f.png)
