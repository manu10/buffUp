package com.manugargia010.buffsdk.model

data class Buff (    
     val timeToShow : Int,
     val author : Author,
     val question : Question,
     val answers : List<Answer>
)

class Answer (val title : String)

class Question (val title : String)

data class Author (val firstName: String,
                   val lastName: String,
                   val imageUrl: String)


