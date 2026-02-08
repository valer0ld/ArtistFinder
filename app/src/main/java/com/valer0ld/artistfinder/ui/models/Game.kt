package com.valer0ld.artistfinder.ui.models

 data class Game (
     val id: Int,
    val start: String,
    val target: String,
    val time: Int,
    val stepCounter: Int,
    val steps: List<String>
){

}