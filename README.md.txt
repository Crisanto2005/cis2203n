### What was the sabotage bug?
TextView counterDisplay was declared as null and setText() was immediately
called on it. This caused a NullPointerException because the variable was
never linked to an actual view in the layout using findViewById().

### How was it fixed?
Used findViewById(R.id.tvCounter) to properly link the variable to the
view, and used onSaveInstanceState/onCreate to preserve the counter value
across screen rotations.