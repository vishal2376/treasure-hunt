package com.vishal2376.treasurehint.models

data class Checkpoint(
   val checkpoint_id:String?,
   val checkpoint_name:String?,
   val map_link:String?,
   val hints:List<String>?,
   val bonus_riddle:String?
)