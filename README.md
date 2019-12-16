# GymMembersApplication
gym application hooray!

This application allows the user (a gym owner) to keep track of members and add new ones.
This uses a RecyclerView to display the users, extending the RecyclerView.Adapter class.
This allows for scrolling through the application without building every scroll view in
advance, like in a ListView. An interface in the RecyclerView.Adapter class is implemented
in the MainActivity and AddMembers activities to add a touch listener to the views.
